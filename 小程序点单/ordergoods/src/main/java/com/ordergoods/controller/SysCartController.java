package com.ordergoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordergoods.common.CommonEnum;
import com.ordergoods.common.ResponseBean;
import com.ordergoods.common.ResultUtil;
import com.ordergoods.common.ToolsUtils;
import com.ordergoods.dto.PageDTO;
import com.ordergoods.entity.SysCart;
import com.ordergoods.entity.SysGoods;
import com.ordergoods.service.SysCartService;
import com.ordergoods.service.SysGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 购物车表 前端控制器
 * </p>
 * @author Janus
 * @since 2021-01-23
 */
@Controller
@RequestMapping({"/cart","wx/cart"})
public class SysCartController {

    private static  final Logger logger= LoggerFactory.getLogger(SysCartController.class);

    @Autowired
    SysCartService cartService;

    @Autowired
    SysGoodsService goodsService;

    private static  final String prefix="cart";


    //购物车添加/减少数量
    @RequestMapping("updateNum")
    @ResponseBody
    public ResponseBean updateNum(Long id,Integer number){
        SysCart sysCart = cartService.getById(id);
        Integer goodsNum = sysCart.getGoodsNum();//当前数量
        SysGoods sysGoods = goodsService.getById(sysCart.getGoodsId());
        if(number>0){//增加数量
            Integer stock = sysGoods.getStock();
            if(stock<=goodsNum){
                return ResultUtil.error("数量不足！");
            }
        }
        sysCart.setGoodsNum(goodsNum+number);
        cartService.updateById(sysCart);//更新购物车数量
        //更是tb_sys_goods数量
        sysGoods.setStock(sysGoods.getStock()-number);
        goodsService.updateById(sysGoods);
        return ResultUtil.success();
    }

    /**
     * 跳到添加页
     * @param model
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(Model model){
        return prefix+"/add";
    }


    /**
     * 添加
     * @param cart
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseBean addDept(SysCart cart){
        logger.debug("addSysCart:"+cart);
        if(cart==null){
            return  ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        Long goodsId = cart.getGoodsId();
        QueryWrapper<SysCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("goods_id",goodsId);
        List<SysCart> cartList = cartService.list(queryWrapper);
        if(ToolsUtils.isEmpty(cartList)){
            cartService.save(cart);
        }else{
            SysCart sysCart = cartList.get(0);
            sysCart.setGoodsNum(cart.getGoodsNum()+sysCart.getGoodsNum());
            cartService.updateById(sysCart);
        }
        return  ResultUtil.successData(cart);
    }

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/listPage")
    public String cartListPage() {
        return prefix+"/list";

    }

    /**
     * 统计用户的购物车商品数量
     * @param userId
     * @return
     */
    @RequestMapping("countCartNum")
    @ResponseBody
    public ResponseBean countCartNum(Long userId){
        QueryWrapper<SysCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<SysCart> cartList = cartService.list(queryWrapper);
        int sum = cartList.stream().mapToInt(SysCart::getGoodsNum).sum();
        return ResultUtil.successData(sum);
    }


    /**
     * 分页列表查询
     * @param queryParam 查询参数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseBean getList(PageDTO pageDTO, SysCart queryParam , HttpSession session) {
        logger.debug("查询列表参数："+queryParam+",pageDTO:"+pageDTO);
        QueryWrapper<SysCart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(queryParam.getUserId()!=null,"user_id",queryParam.getUserId());
        queryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        IPage<SysCart> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        IPage<SysCart> listPage = cartService.page(indexPage, queryWrapper);
        List<SysCart> records = listPage.getRecords();
        records.forEach(e->{
            Long goodsId = e.getGoodsId();
            SysGoods goods = goodsService.getById(goodsId);
            e.setGoods(goods);
        });
        logger.debug("获取的列表："+listPage);
        //获取分页信息
        Map pageInfo = ToolsUtils.wrapperPageInfo(listPage);
        return ResultUtil.successData(pageInfo);
    }

    /**
     * 删除
     * @param idList
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBean delete(@RequestParam("idList") List<Long> idList){
        if(idList==null||idList.isEmpty()){
            return ResultUtil.error("ID不合法！");
        }
       cartService.removeByIds(idList);
        return ResultUtil.success();
    }



}
