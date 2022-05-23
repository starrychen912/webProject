package com.ordergoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordergoods.common.ResponseBean;
import com.ordergoods.common.ResultUtil;
import com.ordergoods.common.ToolsUtils;
import com.ordergoods.dto.PageDTO;
import com.ordergoods.entity.SysCategory;
import com.ordergoods.entity.SysDiscount;
import com.ordergoods.entity.SysGoods;
import com.ordergoods.service.SysDiscountService;
import com.ordergoods.service.SysGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 打折活动表 前端控制器
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Controller
@RequestMapping({"/discount","wx/discount"})
public class SysDiscountController {

    private static  final Logger logger= LoggerFactory.getLogger(SysDiscountController.class);

    @Autowired
    SysGoodsService goodsService;
    
    @Autowired
    SysDiscountService discountService;

    private static  final String prefix="discount";

    /**
     * 跳到折扣添加页
     * @param model
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(Model model,String idList){
        model.addAttribute("idList",idList);
        return prefix+"/add";
    }


    /**
     * 折扣添加
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseBean add(String idList, SysDiscount discount){
        LocalDateTime beginDate = discount.getBeginDate();
        LocalDateTime endDate = discount.getEndDate();

        if(beginDate.compareTo(endDate)>0){
            return ResultUtil.error("开始时间不能大于结束时间！");
        }
        if(LocalDateTime.now().compareTo(endDate)>0){
            return ResultUtil.error("结束时间不能小于当前时间！");
        }
        logger.debug("将要设置的折扣活动：{}",discount);
        String[] split = idList.split(",");
        for(int i=0;i<split.length;i++){
            String goodsId = split[i];
            SysGoods goods = goodsService.getById(Long.parseLong(goodsId));
            discount.setGoodsId(goods.getId());
            discount.setGoodsName(goods.getName());
            discountService.save(discount);
            goods.setField1(discount.getDiscount().toString());
            if(beginDate.compareTo(LocalDateTime.now())<=0){
                goodsService.updateById(goods);
            }
        }
        return  ResultUtil.success();
    }

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/listPage")
    public String categoryListPage() {
        return prefix+"/list";

    }


    /**
     * 分页列表查询
     * @param queryParam 查询参数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseBean getList(PageDTO pageDTO, SysDiscount queryParam , HttpSession session) {
        logger.debug("查询折扣列表参数："+queryParam+",pageDTO:"+pageDTO);
        QueryWrapper<SysDiscount> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getName()),"name",queryParam.getName());
        queryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        IPage<SysDiscount> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        IPage<SysDiscount> listPage = discountService.page(indexPage, queryWrapper);
        logger.debug("获取的折扣列表："+listPage);
        //获取分页信息
        Map pageInfo = ToolsUtils.wrapperPageInfo(listPage);
        return ResultUtil.successData(pageInfo);
    }

    /**
     * 返回给小程序首页的促销商品列表
     * @param session
     * @return
     */
    @RequestMapping("/goods")
    @ResponseBody
    public ResponseBean goods(HttpSession session,PageDTO pageDTO){
        QueryWrapper<SysDiscount> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("end_date",LocalDateTime.now());
        queryWrapper.lt("begin_date",LocalDateTime.now());
        List<SysDiscount> discountList = discountService.list(queryWrapper);
        List<Long> goodsIdList = discountList.stream().map(e -> e.getGoodsId()).collect(Collectors.toList());

        // 查询 tb_sys_goods
        QueryWrapper<SysGoods> goodsQueryWrapper=new QueryWrapper<>();
        goodsQueryWrapper.in("id",goodsIdList);
        goodsQueryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        IPage<SysGoods> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        IPage<SysGoods> listPage = goodsService.page(indexPage, goodsQueryWrapper);

        return  ResultUtil.successData(listPage.getRecords());

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
        for(Long id:idList){
            discountService.removeById(id);
        }
        return ResultUtil.success();
    }


}
