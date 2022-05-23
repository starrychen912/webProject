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

import com.entity.Chongzhijilu;
import com.server.ChongzhijiluServer;
import com.util.PageBean;
import net.sf.json.JSONObject;
import com.util.db;
import java.sql.SQLException;
import java.sql.*;
@Controller
public class ChongzhijiluController {
	@Resource
	private ChongzhijiluServer chongzhijiluService;


   
	@RequestMapping("addChongzhijilu.do")
	public String addChongzhijilu(HttpServletRequest request,Chongzhijilu chongzhijilu,HttpSession session) throws SQLException{
		Timestamp time=new Timestamp(System.currentTimeMillis());
		
		chongzhijilu.setAddtime(time.toString().substring(0, 19));
		chongzhijiluService.add(chongzhijilu);
		db dbo = new db();
		dbo.hsgexecute("update xueshengxinxi set yue=yue+"+chongzhijilu.getChongzhijine()+" where xuehao='"+chongzhijilu.getXuehao()+"'");
		//kuabiaogaizhi
		session.setAttribute("backxx", "添加成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		
		//session.setAttribute("backurl", "chongzhijiluList.do");
		
		return "redirect:postback.jsp";
		//return "redirect:chongzhijiluList.do";
		
		
		
	}
 
//	处理编辑
	@RequestMapping("doUpdateChongzhijilu.do")
	public String doUpdateChongzhijilu(int id,ModelMap map,Chongzhijilu chongzhijilu){
		chongzhijilu=chongzhijiluService.getById(id);
		map.put("chongzhijilu", chongzhijilu);
		return "chongzhijilu_updt";
	}
	
	
	
	
	
//	后台详细
	@RequestMapping("chongzhijiluDetail.do")
	public String chongzhijiluDetail(int id,ModelMap map,Chongzhijilu chongzhijilu){
		chongzhijilu=chongzhijiluService.getById(id);
		map.put("chongzhijilu", chongzhijilu);
		return "chongzhijilu_detail";
	}
//	前台详细
	@RequestMapping("czjlDetail.do")
	public String czjlDetail(int id,ModelMap map,Chongzhijilu chongzhijilu){
		chongzhijilu=chongzhijiluService.getById(id);
		map.put("chongzhijilu", chongzhijilu);
		return "chongzhijiludetail";
	}
//	
	@RequestMapping("updateChongzhijilu.do")
	public String updateChongzhijilu(int id,ModelMap map,Chongzhijilu chongzhijilu,HttpServletRequest request,HttpSession session){
		chongzhijiluService.update(chongzhijilu);
		session.setAttribute("backxx", "修改成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		return "redirect:postback.jsp";
		//String url = request.getHeader("Referer");
		//return "redirect:"+url;
		//return "redirect:chongzhijiluList.do";
	}

//	分页查询
	@RequestMapping("chongzhijiluList.do")
	public String chongzhijiluList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_list";
	}
	
	@RequestMapping("chongzhijilu_yanben1.do")
	public String chongzhijilu_yanben1(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_yanben1";
	}
	@RequestMapping("chongzhijilu_yanben2.do")
	public String chongzhijilu_yanben2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_yanben2";
	}
	@RequestMapping("chongzhijilu_yanben3.do")
	public String chongzhijilu_yanben3(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_yanben3";
	}
	@RequestMapping("chongzhijilu_yanben4.do")
	public String chongzhijilu_yanben4(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_yanben4";
	}
	@RequestMapping("chongzhijilu_yanben5.do")
	public String chongzhijilu_yanben5(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_yanben5";
	}
	
	@RequestMapping("chongzhijiluList2.do")
	public String chongzhijiluList2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu,HttpServletRequest request){
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
		
		pmap.put("xuehao", (String)request.getSession().getAttribute("username"));
		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilu_list2";
	}	
	
	@RequestMapping("czjlList.do")
	public String czjlList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilulist";
	}
	@RequestMapping("czjlListtp.do")
	public String czjlListtp(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Chongzhijilu chongzhijilu, String xuehao, String xingming, String yue, String chongzhijine1,String chongzhijine2, String beizhu){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(xuehao==null||xuehao.equals("")){pmap.put("xuehao", null);}else{pmap.put("xuehao", xuehao);}		if(xingming==null||xingming.equals("")){pmap.put("xingming", null);}else{pmap.put("xingming", xingming);}		if(yue==null||yue.equals("")){pmap.put("yue", null);}else{pmap.put("yue", yue);}		if(chongzhijine1==null||chongzhijine1.equals("")){pmap.put("chongzhijine1", null);}else{pmap.put("chongzhijine1", chongzhijine1);}		if(chongzhijine2==null||chongzhijine2.equals("")){pmap.put("chongzhijine2", null);}else{pmap.put("chongzhijine2", chongzhijine2);}		if(beizhu==null||beizhu.equals("")){pmap.put("beizhu", null);}else{pmap.put("beizhu", beizhu);}		
		int total=chongzhijiluService.getCount(pmap);
		pageBean.setTotal(total);
		List<Chongzhijilu> list=chongzhijiluService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "chongzhijilulisttp";
	}
	
	@RequestMapping("deleteChongzhijilu.do")
	public String deleteChongzhijilu(int id,HttpServletRequest request){
		chongzhijiluService.delete(id);
		String url = request.getHeader("Referer");
		return "redirect:"+url;
		//return "redirect:chongzhijiluList.do";
	}
	
	
}
