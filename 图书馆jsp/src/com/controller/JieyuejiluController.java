package com.controller;

import java.io.File;
import java.io.IOException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Jieyuejilu;
import com.server.JieyuejiluServer;
import com.util.PageBean;
import net.sf.json.JSONObject;
import com.util.db;
import java.sql.SQLException;
import java.sql.*;
@Controller
public class JieyuejiluController {
	@Resource
	private JieyuejiluServer jieyuejiluService;


   
	@RequestMapping("addJieyuejilu.do")
	public String addJieyuejilu(HttpServletRequest request,Jieyuejilu jieyuejilu,HttpSession session) throws SQLException{
		Timestamp time=new Timestamp(System.currentTimeMillis());
		
		jieyuejilu.setAddtime(time.toString().substring(0, 19));
		jieyuejiluService.add(jieyuejilu);
		db dbo = new db();
		
		dbo.hsgexecute("update tushuxinxi set zhuangtai='已借',jieyuecishu=jieyuecishu+1 where tushubianhao='"+jieyuejilu.getTushubianhao()+"'");
		session.setAttribute("backxx", "添加成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		
		//session.setAttribute("backurl", "jieyuejiluList.do");
		
		return "redirect:postback.jsp";
		//return "redirect:jieyuejiluList.do";
		
		
		
	}
 
//	处理编辑
	@RequestMapping("doUpdateJieyuejilu.do")
	public String doUpdateJieyuejilu(int id,ModelMap map,Jieyuejilu jieyuejilu){
		jieyuejilu=jieyuejiluService.getById(id);
		map.put("jieyuejilu", jieyuejilu);
		return "jieyuejilu_updt";
	}
	
	
//	处理编辑
	@RequestMapping("doUpdateJieyuejilu2.do")
	public String doUpdateJieyuejilu2(int id,ModelMap map,Jieyuejilu jieyuejilu){
		jieyuejilu=jieyuejiluService.getById(id);
		map.put("jieyuejilu", jieyuejilu);
		return "jieyuejilu_updt2";
	}
	
	
	
//	后台详细
	@RequestMapping("jieyuejiluDetail.do")
	public String jieyuejiluDetail(int id,ModelMap map,Jieyuejilu jieyuejilu){
		jieyuejilu=jieyuejiluService.getById(id);
		map.put("jieyuejilu", jieyuejilu);
		return "jieyuejilu_detail";
	}
//	前台详细
	@RequestMapping("jyjlDetail.do")
	public String jyjlDetail(int id,ModelMap map,Jieyuejilu jieyuejilu){
		jieyuejilu=jieyuejiluService.getById(id);
		map.put("jieyuejilu", jieyuejilu);
		return "jieyuejiludetail";
	}
//	
	@RequestMapping("updateJieyuejilu.do")
	public String updateJieyuejilu(int id,ModelMap map,Jieyuejilu jieyuejilu,HttpServletRequest request,HttpSession session){
		jieyuejiluService.update(jieyuejilu);
		session.setAttribute("backxx", "修改成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		return "redirect:postback.jsp";
		//String url = request.getHeader("Referer");
		//return "redirect:"+url;
		//return "redirect:jieyuejiluList.do";
	}

//	分页查询
	@RequestMapping("jieyuejiluList.do")
	public String jieyuejiluList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_list";
	}
	
	@RequestMapping("jieyuejilu_yanben1.do")
	public String jieyuejilu_yanben1(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_yanben1";
	}
	@RequestMapping("jieyuejilu_yanben2.do")
	public String jieyuejilu_yanben2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_yanben2";
	}
	@RequestMapping("jieyuejilu_yanben3.do")
	public String jieyuejilu_yanben3(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_yanben3";
	}
	@RequestMapping("jieyuejilu_yanben4.do")
	public String jieyuejilu_yanben4(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_yanben4";
	}
	@RequestMapping("jieyuejilu_yanben5.do")
	public String jieyuejilu_yanben5(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_yanben5";
	}
	
	@RequestMapping("jieyuejiluList2.do")
	public String jieyuejiluList2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan,HttpServletRequest request){
		/*if(session.getAttribute("user")==null){
			return "login";
		}*/
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		pmap.put("jieyueren", (String)request.getSession().getAttribute("username"));
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilu_list2";
	}
	
	
	@RequestMapping("jyjlList.do")
	public String jyjlList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilulist";
	}
	@RequestMapping("jyjlListtp.do")
	public String jyjlListtp(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Jieyuejilu jieyuejilu, String tushubianhao, String mingcheng, String leibie, String zhuangtai, String jieyueren, String jieyueshijian1,String jieyueshijian2, String guihuanshijian1,String guihuanshijian2, String feiyong1,String feiyong2, String shifouguihuan){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyueren==null||jieyueren.equals("")){pmap.put("jieyueren", null);}else{pmap.put("jieyueren", jieyueren);}
		if(jieyueshijian1==null||jieyueshijian1.equals("")){pmap.put("jieyueshijian1", null);}else{pmap.put("jieyueshijian1", jieyueshijian1);}
		if(jieyueshijian2==null||jieyueshijian2.equals("")){pmap.put("jieyueshijian2", null);}else{pmap.put("jieyueshijian2", jieyueshijian2);}
		if(guihuanshijian1==null||guihuanshijian1.equals("")){pmap.put("guihuanshijian1", null);}else{pmap.put("guihuanshijian1", guihuanshijian1);}
		if(guihuanshijian2==null||guihuanshijian2.equals("")){pmap.put("guihuanshijian2", null);}else{pmap.put("guihuanshijian2", guihuanshijian2);}
		if(feiyong1==null||feiyong1.equals("")){pmap.put("feiyong1", null);}else{pmap.put("feiyong1", feiyong1);}
		if(feiyong2==null||feiyong2.equals("")){pmap.put("feiyong2", null);}else{pmap.put("feiyong2", feiyong2);}
		if(shifouguihuan==null||shifouguihuan.equals("")){pmap.put("shifouguihuan", null);}else{pmap.put("shifouguihuan", shifouguihuan);}
		
		int total=jieyuejiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Jieyuejilu> list=jieyuejiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "jieyuejilulisttp";
	}
	
	@RequestMapping("deleteJieyuejilu.do")
	public String deleteJieyuejilu(int id,HttpServletRequest request){
		jieyuejiluService.delete(id);
		String url = request.getHeader("Referer");
		return "redirect:"+url;
		//return "redirect:jieyuejiluList.do";
	}
	
	
}
