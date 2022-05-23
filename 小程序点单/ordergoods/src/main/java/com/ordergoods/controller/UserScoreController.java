package com.ordergoods.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordergoods.common.ResponseBean;
import com.ordergoods.common.ResultUtil;
import com.ordergoods.common.ToolsUtils;
import com.ordergoods.dto.PageDTO;
import com.ordergoods.entity.SysUser;
import com.ordergoods.entity.UserScore;
import com.ordergoods.service.SysUserService;
import com.ordergoods.service.UserScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员积分明细表 前端控制器
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Controller
@RequestMapping("/userScore")
public class UserScoreController {

    private static  final Logger logger= LoggerFactory.getLogger(UserScoreController.class);

    @Autowired
    UserScoreService scorelService;

    @Autowired
    SysUserService userService;


    @Autowired
    UserScoreService scoreService;


    private static  final String prefix="userScore";

    @ModelAttribute
    private void addModelAttribute(Model model){
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.ne("code","admin");
        List<SysUser> userList = userService.list(queryWrapper);
        model.addAttribute("userList",userList);
    }



    /**
     * 跳转积分列表页面
     * @return
     */
    @RequestMapping("/listPage")
    public String scorelListPage() {
        return prefix+"/list";

    }


    /**
     * 分页列表查询
     * @param queryParam 查询参数
     * params[beginTime]: 2020-10-16 -map参数
     * params[endTime]: 2020-10-16   -map参数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseBean getList(PageDTO pageDTO, UserScore queryParam , HttpSession session) {
        logger.debug("查询积分列表参数："+queryParam+",pageDTO:"+pageDTO);
        QueryWrapper<UserScore> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getUserId()),"user_id",queryParam.getUserId());
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getUserName()),"user_name",queryParam.getUserName());
        queryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        IPage<UserScore> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        IPage<UserScore> listPage = scorelService.page(indexPage, queryWrapper);
        logger.debug("获取的积分列表："+listPage);
        //获取分页信息
        Map pageInfo = ToolsUtils.wrapperPageInfo(listPage);
        return ResultUtil.successData(pageInfo);
    }

    /**
     * 积分删除
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
            scorelService.removeById(id);
        }
        return ResultUtil.success();
    }

}
