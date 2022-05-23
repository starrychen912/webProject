package com.ordergoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordergoods.common.*;
import com.ordergoods.dto.PageDTO;
import com.ordergoods.entity.*;
import com.ordergoods.entity.SysGoods;
import com.ordergoods.service.*;
import com.ordergoods.service.SysGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 餐品表 前端控制器
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Controller
@RequestMapping({"/goods","wx/goods"})
public class SysGoodsController {
    
    private static  final Logger logger= LoggerFactory.getLogger(SysGoodsController.class);

    @Autowired
    SysCategoryService categoryService;

    @Autowired
    SysGoodsService goodsService;
    
    @Autowired
    SysFileService fileService;

    private static  final String prefix="goods";

    @Autowired
    SysOrderItemService orderItemService;


    @ModelAttribute
    private void addModelInfo(Model model){
        List<SysCategory> categoryList = categoryService.list();
        model.addAttribute("categoryList",categoryList);
    }

    /**
     * 获取推荐列表--猜你喜欢
     * @param queryParam 查询参数
     * @return
     */
    @RequestMapping("/recommend")
    @ResponseBody
    public ResponseBean getRecommendList(SysGoods queryParam,String vagueParam,HttpSession session,Long userId) {
        List<SysGoods> recommendList=new ArrayList<>();
        if(userId==null){
            recommendList=randomSix();
        }else {
            List<HashMap> hashMapList = orderItemService.groupUserGoods();
            List<String> calculateList = CollaborativeAlgorithm.calculate(userId, hashMapList);
            logger.info("获取当前用户：{}，推荐结果idlist是：{}", userId, calculateList);
            if(calculateList==null||calculateList.isEmpty()){
                recommendList=randomSix();
            }else{
                //根据推荐结果获取商品列表
                recommendList=goodsService.listByIds(calculateList);
            }
        }
        return ResultUtil.successData(recommendList);
    }

    // 从入参中获取随机六个
    private List<SysGoods> randomSix(){
        List<SysGoods> sysGoodsList = goodsService.list();
        if(sysGoodsList.size()<=6){
            return sysGoodsList;
        }
        List<SysGoods> recommendList=new ArrayList<>();
        Set<Integer> randomSet=new HashSet<>();
        for(int i=1;i<=6;i++){
            int nextInt = new Random().nextInt(sysGoodsList.size());
            if(randomSet.contains(nextInt)){
                continue;
            }
            randomSet.add(nextInt);
            recommendList.add(sysGoodsList.get(nextInt));
            if(randomSet.size()>=6){
                break;
            }
        }
        return recommendList;
    }


    /**
     * 跳到餐品添加页
     * @param model
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(Model model){
        return prefix+"/add";
    }


    /**
     * 餐品添加
     * @param goods
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseBean add(SysGoods goods, @RequestParam(name="file0",required = false) MultipartFile file0,
                            @RequestParam(name="file1",required = false) MultipartFile file1,
                            @RequestParam(name="file2",required = false) MultipartFile file2,
                            @RequestParam(name="file3",required = false) MultipartFile file3,
                            @RequestParam(name="file4",required = false) MultipartFile file4){
        logger.debug("addSysGoods:"+goods);
        if(goods==null){
            return  ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        //校验餐品名称是否已经被占用
        QueryWrapper<SysGoods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",goods.getName()).or().eq("code",goods.getCode());
        List<SysGoods> list = goodsService.list(queryWrapper);
        if(ToolsUtils.isNotEmpty(list)){
            return ResultUtil.error("餐品名称或编号已经被占用！");
        }
        SysCategory sysCategory = categoryService.getById(goods.getCategoryId());
        goods.setCategoryName(sysCategory.getName());
        goodsService.save(goods);
        //保存文件并更新saveName到main_pic
        if (file0!=null&&!file0.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_main_pic);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file0);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setMainPic(saveFile.getSaveName());
            }
        }
        if (file1!=null&&!file1.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic1);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file1);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic1(saveFile.getSaveName());
            }
        }
        if (file2!=null&&!file2.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic2);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file2);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic2(saveFile.getSaveName());
            }
        }
        if (file3!=null&&!file3.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic3);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file3);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic3(saveFile.getSaveName());
            }
        }
        if (file4!=null&&!file4.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic4);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file4);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic4(saveFile.getSaveName());
            }
        }
        boolean b = goodsService.updateById(goods);
        return  ResultUtil.success();
    }

    /**
     * 跳转到餐品修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("goods", goodsService.getById(id));
        return prefix+"/edit";
    }


    /**
     * 返回给小程序的商品明细
     * @return
     */
    @RequestMapping("wxdetail")
    @ResponseBody
    public ResponseBean wxgoodsDetail(Long id){
        SysGoods goods = goodsService.getById(id);
        return ResultUtil.successData(goods);
    }


    /**
     * 餐品修改
     * @param goods
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean updateById( SysGoods goods, @RequestParam(name="file0",required = false) MultipartFile file0,
                                    @RequestParam(name="file1",required = false) MultipartFile file1,
                                    @RequestParam(name="file2",required = false) MultipartFile file2,
                                    @RequestParam(name="file3",required = false) MultipartFile file3,
                                    @RequestParam(name="file4",required = false) MultipartFile file4) {
        if (goods == null) {
            return ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        SysCategory sysCategory = categoryService.getById(goods.getCategoryId());
        goods.setCategoryName(sysCategory.getName());
        //保存文件并更新saveName到main_pic
        if (file0!=null&&!file0.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_main_pic);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file0);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setMainPic(saveFile.getSaveName());
            }
        }
        if (file1!=null&&!file1.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic1);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file1);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic1(saveFile.getSaveName());
            }
        }
        if (file2!=null&&!file2.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic2);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file2);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic2(saveFile.getSaveName());
            }
        }
        if (file3!=null&&!file3.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic3);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file3);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic3(saveFile.getSaveName());
            }
        }
        if (file4!=null&&!file4.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(goods.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_sub_pic4);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file4);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                goods.setSubPic4(saveFile.getSaveName());
            }
        }
        //校验餐品名称是否已经被占用
        String name = goods.getName();
        QueryWrapper<SysGoods> queryWrapper=new QueryWrapper<>();
        queryWrapper.and(e->e.eq("name",goods.getName()).or().eq("code",goods.getCode())).ne("id",goods.getId());
        List<SysGoods> list =goodsService.list(queryWrapper);
        if(ToolsUtils.isNotEmpty(list)){
            return ResultUtil.error("餐品名称或编码已经被占用！");
        }
        goods.setUpdateTime(LocalDateTime.now());
        boolean i = goodsService.updateById(goods);
        return ResultUtil.success() ;
    }

    /**
     * 跳转餐品列表页面
     * @return
     */
    @RequestMapping("/listPage")
    public String goodsListPage() {
        return prefix+"/list";

    }


    /**
     * 分页列表查询
     * @param queryParam 查询参数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseBean getList(PageDTO pageDTO, SysGoods queryParam , HttpSession session) {
        logger.debug("查询餐品列表参数："+queryParam+",pageDTO:"+pageDTO);
        // where
        QueryWrapper<SysGoods> queryWrapper=new QueryWrapper<>();
        // 如果name不为空，where name like '%XXXX%'
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getName()),"name",queryParam.getName());
        // 如果categoryId！=null  ，   category_id=XXX   eq =,like '%XX%'  ,
        queryWrapper.eq(queryParam.getCategoryId()!=null,"category_id",queryParam.getCategoryId());
        queryWrapper.eq(queryParam.getState()!=null,"state",queryParam.getState());
        // order by id desc
        queryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        // limit m,n - limit (pageNum-1)*pageSize,pageNum*pageSize , limit 0 10
        IPage<SysGoods> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        // 直接查询
        IPage<SysGoods> listPage = goodsService.page(indexPage, queryWrapper);
        logger.debug("获取的餐品列表："+listPage);
        //获取分页信息
        Map pageInfo = ToolsUtils.wrapperPageInfo(listPage);
        return ResultUtil.successData(pageInfo);
    }

    /**
     * 餐品删除
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
            goodsService.removeById(id);
        }
        return ResultUtil.success();
    }


    /**
     * 上架/下架餐品
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("updateState")
    @ResponseBody
    public ResponseBean updateState(Long id,Integer state){
        SysGoods goods = goodsService.getById(id);
        goods.setState(state);
        goods.setUpdateTime(LocalDateTime.now());
        goodsService.updateById(goods);
        return ResultUtil.successData(goods);
    }


}
