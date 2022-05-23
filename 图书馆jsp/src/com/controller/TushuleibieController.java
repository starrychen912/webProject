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

import com.entity.Tushuleibie;
import com.server.TushuleibieServer;
import com.util.PageBean;
import net.sf.json.JSONObject;
import com.util.db;
import java.sql.SQLException;
import java.sql.*;
@Controller
public class TushuleibieController {
	@Resource
	private TushuleibieServer tushuleibieService;


   
	@RequestMapping("addTushuleibie.do")
	public String addTushuleibie(HttpServletRequest request,Tushuleibie tushuleibie,HttpSession session) throws SQLException{
		Timestamp time=new Timestamp(System.currentTimeMillis());
		
		tushuleibie.setAddtime(time.toString().substring(0, 19));
		tushuleibieService.add(tushuleibie);
		db dbo = new db();
		
		//kuabiaogaizhi
		session.setAttribute("backxx", "添加成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		
		//session.setAttribute("backurl", "tushuleibieList.do");
		
		return "redirect:postback.jsp";
		//return "redirect:tushuleibieList.do";
		
		
		
	}
 
//	处理编辑
	@RequestMapping("doUpdateTushuleibie.do")
	public String doUpdateTushuleibie(int id,ModelMap map,Tushuleibie tushuleibie){
		tushuleibie=tushuleibieService.getById(id);
		map.put("tushuleibie", tushuleibie);
		return "tushuleibie_updt";
	}
	
	
	
	
	
//	后台详细
	@RequestMapping("tushuleibieDetail.do")
	public String tushuleibieDetail(int id,ModelMap map,Tushuleibie tushuleibie){
		tushuleibie=tushuleibieService.getById(id);
		map.put("tushuleibie", tushuleibie);
		return "tushuleibie_detail";
	}
//	前台详细
	@RequestMapping("tslbDetail.do")
	public String tslbDetail(int id,ModelMap map,Tushuleibie tushuleibie){
		tushuleibie=tushuleibieService.getById(id);
		map.put("tushuleibie", tushuleibie);
		return "tushuleibiedetail";
	}
//	
	@RequestMapping("updateTushuleibie.do")
	public String updateTushuleibie(int id,ModelMap map,Tushuleibie tushuleibie,HttpServletRequest request,HttpSession session){
		tushuleibieService.update(tushuleibie);
		session.setAttribute("backxx", "修改成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		return "redirect:postback.jsp";
		//String url = request.getHeader("Referer");
		//return "redirect:"+url;
		//return "redirect:tushuleibieList.do";
	}

//	分页查询
	@RequestMapping("tushuleibieList.do")
	public String tushuleibieList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibie_list";
	}
	
	@RequestMapping("tushuleibie_yanben1.do")
	public String tushuleibie_yanben1(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibie_yanben1";
	}
	@RequestMapping("tushuleibie_yanben2.do")
	public String tushuleibie_yanben2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibie_yanben2";
	}
	@RequestMapping("tushuleibie_yanben3.do")
	public String tushuleibie_yanben3(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibie_yanben3";
	}
	@RequestMapping("tushuleibie_yanben4.do")
	public String tushuleibie_yanben4(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibie_yanben4";
	}
	@RequestMapping("tushuleibie_yanben5.do")
	public String tushuleibie_yanben5(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibie_yanben5";
	}
	
	
	
	@RequestMapping("tslbList.do")
	public String tslbList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibielist";
	}
	@RequestMapping("tslbListtp.do")
	public String tslbListtp(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuleibie tushuleibie, String leibie){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 15);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 15);
		
		
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		
		int total=tushuleibieService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuleibie> list=tushuleibieService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuleibielisttp";
	}
	
	@RequestMapping("deleteTushuleibie.do")
	public String deleteTushuleibie(int id,HttpServletRequest request){
		tushuleibieService.delete(id);
		String url = request.getHeader("Referer");
		return "redirect:"+url;
		//return "redirect:tushuleibieList.do";
	}
	
	@RequestMapping("quchongTushuleibie.do")
	public void quchongTushuleibie(Tushuleibie tushuleibie,HttpServletResponse response){
		   Map<String,Object> map=new HashMap<String,Object>();
		   map.put("leibie", tushuleibie.getLeibie());
		   System.out.println("leibie==="+tushuleibie.getLeibie());
		   System.out.println("leibie222==="+tushuleibieService.quchongTushuleibie(map));
		   JSONObject obj=new JSONObject();
		   if(tushuleibieService.quchongTushuleibie(map)!=null){
				 obj.put("info", "ng");
			   }else{
				   obj.put("info", "类别可以用！");
				  
			   }
		   response.setContentType("text/html;charset=utf-8");
		   PrintWriter out=null;
		   try {
			out=response.getWriter();
			out.print(obj);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
