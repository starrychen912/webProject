package com.ordergoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordergoods.common.CommonEnum;
import com.ordergoods.common.ResponseBean;
import com.ordergoods.common.ResultUtil;
import com.ordergoods.common.ToolsUtils;
import com.ordergoods.dto.PageDTO;
import com.ordergoods.entity.SysCategory;
import com.ordergoods.entity.SysGoods;
import com.ordergoods.service.SysCategoryService;
import com.ordergoods.service.SysGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类表 前端控制器
 * </p>
 *
 * @author Janus
 */
@Controller
@RequestMapping({"/category","wx/category"})
public class SysCategoryController {

    private static  final Logger logger= LoggerFactory.getLogger(SysCategoryController.class);

    @Autowired
    SysCategoryService categoryService;

    @Autowired
    SysGoodsService goodsService;

    private static  final String prefix="category";

    /**
     * 跳到分类添加页
     * @param model
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(Model model){
        return prefix+"/add";
    }


    /**
     * 分类添加
     * @param category
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseBean addDept(SysCategory category){
        logger.debug("addSysCategory:"+category);
        if(category==null){
            return  ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        //校验分类名称是否已经被占用
        QueryWrapper<SysCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",category.getName()).or().eq("code",category.getCode());
        List<SysCategory> list = categoryService.list(queryWrapper);
        if(ToolsUtils.isNotEmpty(list)){
            return ResultUtil.error("分类名称或编号已经被占用！");
        }
        categoryService.save(category);
        return  ResultUtil.success();
    }

    /**
     * 跳转到分类修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        return prefix+"/edit";
    }



    /**
     * 分类修改
     * @param category
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean updateById( SysCategory category) {
        if (category == null) {
            return ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        //校验分类名称是否已经被占用
        String name = category.getName();
        QueryWrapper<SysCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.and(e->e.eq("name",category.getName()).or().eq("code",category.getCode())).ne("id",category.getId());
        List<SysCategory> list =categoryService.list(queryWrapper);
        if(ToolsUtils.isNotEmpty(list)){
            return ResultUtil.error("分类名称或编码已经被占用！");
        }
        category.setUpdateTime(LocalDateTime.now());
        boolean i = categoryService.updateById(category);
        return ResultUtil.success() ;
    }

    /**
     * 跳转分类列表页面
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
    public ResponseBean getList(PageDTO pageDTO, SysCategory queryParam , HttpSession session) {
        logger.debug("查询分类列表参数："+queryParam+",pageDTO:"+pageDTO);
        QueryWrapper<SysCategory> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getName()),"name",queryParam.getName());
        queryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        IPage<SysCategory> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        IPage<SysCategory> listPage = categoryService.page(indexPage, queryWrapper);
        logger.debug("获取的分类列表："+listPage);
        //获取分页信息
        Map pageInfo = ToolsUtils.wrapperPageInfo(listPage);
        return ResultUtil.successData(pageInfo);
    }

    /**
     * 分类删除
     * @param idList
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBean delete(@RequestParam("idList") List<Long> idList){
        if(idList==null||idList.isEmpty()){
            return ResultUtil.error("ID不合法！");
        }
        QueryWrapper<SysGoods> queryWrapper=new QueryWrapper<>();
        for(Long id:idList){
            queryWrapper.eq("category_id",id);
            List<SysGoods> list = goodsService.list(queryWrapper);
            if(ToolsUtils.isNotEmpty(list)){
                return ResultUtil.error("分类下有产品，不可删除！");
            }
            categoryService.removeById(id);
        }
        return ResultUtil.success();
    }


}
