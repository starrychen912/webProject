package com.ordergoods.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ordergoods.common.*;
import com.ordergoods.dto.PageDTO;
import com.ordergoods.entity.SysFile;
import com.ordergoods.entity.SysUser;
import com.ordergoods.entity.UserScore;
import com.ordergoods.service.SysFileService;
import com.ordergoods.service.SysUserService;
import com.ordergoods.service.UserScoreService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Api(tags = {"用户管理"})
@Controller
@RequestMapping({"/user","wx/user"})
public class UserController {

    private static  final Logger logger= LoggerFactory.getLogger(UserController.class);

    @Value("${com.jane.security.md5.key}")
    private String md5Key;

    @Autowired
    SysFileService fileService;

    @Autowired
    SysUserService userService;

    @Autowired
    UserScoreService scoreService;

    @Value("${com.jane.appid}")
    private String appid;
    @Value("${com.jane.secret}")
    private String secret;


    @Value("${com.jane.file.baseFilePath}")
    private String baseFilePath;

    private static  final String prefix="user";



    //跳到管理员修改界面
    @RequestMapping("adminEditPage")
    public String admineEditPage(){
        return "admin/edit";
    }


    /**
     * 跳到用户添加页
     * @param model
     * @return
     */
    @RequestMapping("addPage")
    public String addPage(Model model){
        return prefix+"/add";
    }


    /**
     * 用户添加
     * @param user
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResponseBean addUser(SysUser user, MultipartFile file){
        logger.debug("addSysUser:"+user);
        if(user==null){
            return  ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        String mobile = user.getMobile();
        if(StringUtils.isEmpty(mobile)||!RegexUtil.checkMobile(mobile)){
            logger.error("手机号为空！");
            return  ResultUtil.error("手机号不合法！");
        }

        String password= StringUtils.isEmpty(user.getPassword())?"123456":user.getPassword();
        //对密码进行MD5盐值加密，数据库保存密码的密文
        //MD5Util.md5(password,md5Key  md5key就是盐值
        user.setPassword(MD5Util.md5(password,md5Key));
        boolean add = userService.save(user);
        logger.debug("用户添加结果："+user);
        //保存文件并更新saveName到user.field1
        if (file!=null&&!file.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(user.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_user);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                user.setField1(saveFile.getSaveName());
            }
            userService.updateById(user);
        }
        return  ResultUtil.success();
    }

    /**
     * 跳转到用户修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return prefix+"/edit";
    }

    /**
     * 充值页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/rechargePage/{id}")
    public String rechargePage(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return prefix+"/recharge";
    }

    /**
     * 管理员给会员充值
     * @param id
     * @param score
     * @param session
     * @return
     */
    @RequestMapping("/recharge")
    @ResponseBody
    public ResponseBean recharge(Long id, Long score, HttpSession session){
        SysUser user = userService.getById(id);
        //给会员增加积分=fruitNum
        QueryWrapper<UserScore> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",id).orderByDesc("id");
        List<UserScore> scoreList = scoreService.list(queryWrapper);
        UserScore insUserScore=new UserScore();
        insUserScore.setOperatorId(ToolsUtils.getLoginUserId(session));
        insUserScore.setOperatorName(ToolsUtils.getLoginUserName(session));
        insUserScore.setUserName(user.getName());
        insUserScore.setUserId(user.getId());
        insUserScore.setReason(ToolsUtils.getLoginUserName(session)+"给用户"+user.getName()+"充值了"+score+"积分");
        insUserScore.setChangeScore(score.intValue());
        if(ToolsUtils.isNotEmpty(scoreList)){
            UserScore userScore = scoreList.get(0);
            Integer oldScore = userScore.getCurrentScore();
            insUserScore.setBeforeScore(oldScore);
            insUserScore.setCurrentScore(oldScore+score.intValue());
        }else{
            insUserScore.setBeforeScore(0);
            insUserScore.setCurrentScore(score.intValue());
        }
        scoreService.save(insUserScore);
        //修改用户基本信息里面积分
        user.setScore(user.getScore()+score.intValue());
        userService.updateById(user);
        return ResultUtil.success();
    }

    /**
     * 修改用户密码
     * @param id
     * @param password
     * @param oldpwd
     * @return
     */
    @RequestMapping("updatePWd")
    @ResponseBody
    public  ResponseBean updatePWd(Long id,String password,String oldpwd,HttpSession session){
        SysUser user = userService.getById(id);
        String userPassword = user.getPassword();
        if(!MD5Util.md5Verify(oldpwd,md5Key,userPassword)){
            return ResultUtil.error("原始密码不正确！");
        }
        user.setPassword(MD5Util.md5(password,md5Key));
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        session.setAttribute("user",user);
        return ResultUtil.successData(user);
    }


    /**
     * 初始化用户密码
     * @param id
     * @return
     */
    @RequestMapping("initPwd")
    @ResponseBody
    public ResponseBean initPwd(Long id){
        if(id==null||id<1){
            return ResultUtil.error(CommonEnum.BAD_REQUEST);
        }
        SysUser sysUser = userService.getById(id);
        if(sysUser!=null){
            sysUser.setPassword(MD5Util.md5("123456",md5Key));
            sysUser.setUpdateTime(LocalDateTime.now());
            userService.updateById(sysUser);
            return ResultUtil.success();
        }
        return ResultUtil.error(CommonEnum.NO_USER_EXIST);
    }

    /**
     * 用户修改
     * @param user
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public ResponseBean updateById(MultipartFile file, SysUser user,HttpSession session) {
        if (user == null) {
            return ResultUtil.error(CommonEnum.BAD_PARAM);
        }
        String mobile = user.getMobile();


        if(StringUtils.isEmpty(mobile)){
            return ResultUtil.error("手机号为空！");
        }
        if(StringUtils.isEmpty(user.getName())){
            return ResultUtil.error("用户name为空！");
        }
        if(!StringUtils.isEmpty(mobile)&&!RegexUtil.checkMobile(mobile)){
            return ResultUtil.error("手机号不合法！");
        }

        //处理密码
        String password = user.getPassword();
        if(!StringUtils.isEmpty(password)){
            user.setPassword(MD5Util.md5(password,md5Key));
        }else{
            user.setPassword(null);
        }
        if (file!=null&&!file.isEmpty()) {
            SysFile sysFile=new SysFile();
            sysFile.setObjectId(user.getId());
            sysFile.setCategoryCode(ComCodeEnum.category_code_user);
            SysFile saveFile=fileService.saveOrUpdateFile(sysFile,file);
            if(saveFile!=null&&!StringUtils.isEmpty(saveFile.getSaveName())){
                user.setField1(saveFile.getSaveName());
            }
        }
        user.setUpdateTime(LocalDateTime.now());
        boolean i = userService.updateById(user);
        SysUser loginUser = ToolsUtils.getLoginUser(session);
        //如果修改的是当前自己，则更新session
        if(loginUser!=null){
            if(user.getId().intValue()==loginUser.getId().intValue()){
                session.setAttribute("user",userService.getById(user.getId()));
            }
        }

        return ResultUtil.successData(userService.getById(user.getId())) ;
    }

    /**
     * 跳转用户列表页面
     * @return
     */
    @RequestMapping("/listPage")
    public String userListPage() {
        return prefix+"/list";

    }


    /**
     * 分页列表查询
     * @param queryParam 查询参数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseBean getList(PageDTO pageDTO, SysUser queryParam , HttpSession session) {
        logger.debug("查询用户列表参数："+queryParam+",pageDTO:"+pageDTO);
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getName()),"name",queryParam.getName());
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getMobile()),"mobile",queryParam.getMobile());
        queryWrapper.like(!StringUtils.isEmpty(queryParam.getCode()),"code",queryParam.getCode());
        queryWrapper.ne("code","admin");//用户管理里面超级管理员admin除外
        queryWrapper.orderBy(true,pageDTO.getIsAsc().equals("asc"), ToolsUtils.camelToUnderline(pageDTO.getOrderByColumn()));
        IPage<SysUser> indexPage = new Page<>(pageDTO.getPageNum(),pageDTO.getPageSize());
        IPage<SysUser> listPage = userService.page(indexPage, queryWrapper);
        logger.debug("获取的用户列表："+listPage);
        //获取分页信息
        Map pageInfo = ToolsUtils.wrapperPageInfo(listPage);
        return ResultUtil.successData(pageInfo);
    }

    /**
     * 用户删除
     * @param idList
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResponseBean delete(@RequestParam("idList") List<Long> idList){
        if(idList==null||idList.isEmpty()){
            return ResultUtil.error("ID不合法！");
        }
        idList.forEach(e->userService.removeById(e));
        return ResultUtil.success();
    }


    /**
     * 启用/禁用用户
     * @param id
     * @param state
     * @return
     */
    @RequestMapping("updateState")
    @ResponseBody
    public ResponseBean updateState(Long id,Integer state){
        SysUser user = userService.getById(id);
        user.setState(state);
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return ResultUtil.successData(user);
    }

    /**
     * 后台用户登录
     * @param user
     * @param session
     * @param verifyCode
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ResponseBean checkLogin(SysUser user, HttpSession session, @RequestParam String verifyCode) {
        //首先验证验证码是否存在
        String trueVerifyCode = (String) session.getAttribute("verifyCode");
        if (trueVerifyCode == null) {
            return ResultUtil.error(CommonEnum.REFRESH_VERIFYCODE);
        }

        //判断验证码是否输入正确（验证码忽略大小写）
        if (!trueVerifyCode.equalsIgnoreCase(verifyCode)) {
            return ResultUtil.error(CommonEnum.ERROR_VERIFYCODE);
        }

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("code",user.getCode());
        List<SysUser> list = userService.list(queryWrapper);
        //判断是否存在当前用户
        if (ToolsUtils.isEmpty(list)) {
            return new ResponseBean(false, CommonEnum.NO_USER_EXIST);
        }
        SysUser sysUser = list.get(0);
        if(sysUser.getState()!=1){
            return ResultUtil.error("用户已经被禁用，请联系管理员！");
        }
        if(sysUser.getType()==2){
            return ResultUtil.error("小程序用户不可 登录管理后台！");
        }
        //判断密码是否正确
        if (!MD5Util.md5Verify(user.getPassword(), this.md5Key, sysUser.getPassword())) {
            return new ResponseBean(false, CommonEnum.INVALID_PASSWORD);
        }
        //通过所有验证
        session.setAttribute("user", sysUser);
        session.setAttribute("type", sysUser.getType());
        return ResultUtil.success( "登录成功！");
    }

    /**
     * 微信注册并登陆
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/wxlogin")
    @ResponseBody
    public ResponseBean wxlogin(SysUser user, HttpSession session, String appcode,String userInfo) {
        logger.debug("userInfo:{}",userInfo);
        //
        JSONObject wxObject = WXServerApiUtil.code2Session(appid, secret, appcode);
        logger.debug("获取的用户wxObject:{}",wxObject);
        // {"openid":"onven4oiNlogGv2T6sPMiQ3WtGkc","session_key":"1+E3K393QCnVQHSfT6bdYA=="}  json
        String openid = wxObject.getString("openid");
        JSONObject userInfoObj = JSONObject.parseObject(userInfo);
        // select * from tb_sys_user where wx_openid=XXX
        // 查询包装语句 queryWrapper   ====   where
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        // where wx_openid=XXX
        queryWrapper.eq("wx_openid",openid);
        // list查询  controller - service --mapper--数据库
        List<SysUser> userList = userService.list(queryWrapper);
        if(ToolsUtils.isNotEmpty(userList)){//是否为第一次登录
            user=userList.get(0);
            if(user.getState()!=1){
                return ResultUtil.error("用户已经被禁用，请联系管理员！");
            }
            user.setWxAvatarurl(userInfoObj.getString("avatarUrl"));
            user.setWxNick(userInfoObj.getString("nickName"));
            user.setSex(userInfoObj.getString("gender").equals("0")?"1":"2");
            if(StringUtils.isEmpty(user.getCode())&&!StringUtils.isEmpty(user.getMobile())){
                user.setCode(user.getMobile());
            }
            if(StringUtils.isEmpty(user.getCode())){
                user.setCode(user.getWxOpenid());
            }
            // 更新用户
            userService.updateById(user);
            return ResultUtil.successData(user);
        }else{
            user.setWxAvatarurl(userInfoObj.getString("avatarUrl"));
            user.setWxNick(userInfoObj.getString("nickName"));
            user.setSex(userInfoObj.getString("gender").equals("0")?"1":"2");// 1 男 2 女
            user.setWxOpenid(openid);
            if(StringUtils.isEmpty(user.getCode())&&!StringUtils.isEmpty(user.getMobile())){
                user.setCode(user.getMobile());
            }
            // 如果code为空，就把wxopenid赋予code
            if(StringUtils.isEmpty(user.getCode())){
                user.setCode(user.getWxOpenid());
            }

            if(StringUtils.isEmpty(user.getName())){
                user.setName(user.getWxNick());
            }
            // save  add  insert - 保存到数据库
            userService.save(user);
        }
        return ResultUtil.successData(user);
    }

}
