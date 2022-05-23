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

import com.entity.Tushuxinxi;
import com.server.TushuxinxiServer;
import com.util.PageBean;
import net.sf.json.JSONObject;
import com.util.db;
import java.sql.SQLException;
import java.sql.*;
@Controller
public class TushuxinxiController {
	@Resource
	private TushuxinxiServer tushuxinxiService;


   
	@RequestMapping("addTushuxinxi.do")
	public String addTushuxinxi(HttpServletRequest request,Tushuxinxi tushuxinxi,HttpSession session) throws SQLException{
		Timestamp time=new Timestamp(System.currentTimeMillis());
		
		tushuxinxi.setAddtime(time.toString().substring(0, 19));
		tushuxinxiService.add(tushuxinxi);
		db dbo = new db();
		
		//kuabiaogaizhi
		session.setAttribute("backxx", "添加成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		
		//session.setAttribute("backurl", "tushuxinxiList.do");
		
		return "redirect:postback.jsp";
		//return "redirect:tushuxinxiList.do";
		
		
		
	}
 
//	处理编辑
	@RequestMapping("doUpdateTushuxinxi.do")
	public String doUpdateTushuxinxi(int id,ModelMap map,Tushuxinxi tushuxinxi){
		tushuxinxi=tushuxinxiService.getById(id);
		map.put("tushuxinxi", tushuxinxi);
		return "tushuxinxi_updt";
	}
	
	
	
	
	
//	后台详细
	@RequestMapping("tushuxinxiDetail.do")
	public String tushuxinxiDetail(int id,ModelMap map,Tushuxinxi tushuxinxi){
		tushuxinxi=tushuxinxiService.getById(id);
		map.put("tushuxinxi", tushuxinxi);
		return "tushuxinxi_detail";
	}
//	前台详细
	@RequestMapping("tsxxDetail.do")
	public String tsxxDetail(int id,ModelMap map,Tushuxinxi tushuxinxi){
		tushuxinxi=tushuxinxiService.getById(id);
		map.put("tushuxinxi", tushuxinxi);
		return "tushuxinxidetail";
	}
//	
	@RequestMapping("updateTushuxinxi.do")
	public String updateTushuxinxi(int id,ModelMap map,Tushuxinxi tushuxinxi,HttpServletRequest request,HttpSession session){
		tushuxinxiService.update(tushuxinxi);
		session.setAttribute("backxx", "修改成功");
		session.setAttribute("backurl", request.getHeader("Referer"));
		return "redirect:postback.jsp";
		//String url = request.getHeader("Referer");
		//return "redirect:"+url;
		//return "redirect:tushuxinxiList.do";
	}

//	分页查询
	@RequestMapping("tushuxinxiList.do")
	public String tushuxinxiList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_list";
	}
	
    //	分页查询
	@RequestMapping("tushuxinxiListEqual.do")
	public String tushuxinxiListEqual(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}
		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}
		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}
		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}
		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}
		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}
		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}
		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}
		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}
		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}
		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}
		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}
		
		int total=tushuxinxiService.getCountEqual(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPageEqual(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_list";
	}
	
	 //	关键字查询
		@RequestMapping("tushuxinxiListKeyword.do")
		public String tushuxinxiListKeyword(@RequestParam(value="page",required=false)String page,
				ModelMap map,HttpSession session,Tushuxinxi tushuxinxi,  String mingcheng){
			if(page==null||page.equals("")){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
			Map<String, Object> pmap=new HashMap<String,Object>();
			pmap.put("pageno", pageBean.getStart());
			pmap.put("pageSize", 8);
			
			
			if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}
			
			int total=tushuxinxiService.getCountKeyword(pmap);
			pageBean.setTotal(total);
			List<Tushuxinxi> list=tushuxinxiService.getByPageKeyword(pmap);
			map.put("page", pageBean);
			map.put("list", list);
			session.setAttribute("p", 1);
			return "tushuxinxi_list";
		}
	
	
	@RequestMapping("tushuxinxi_yanben1.do")
	public String tushuxinxi_yanben1(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_yanben1";
	}
	@RequestMapping("tushuxinxi_yanben2.do")
	public String tushuxinxi_yanben2(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_yanben2";
	}
	@RequestMapping("tushuxinxi_yanben3.do")
	public String tushuxinxi_yanben3(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_yanben3";
	}
	@RequestMapping("tushuxinxi_yanben4.do")
	public String tushuxinxi_yanben4(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_yanben4";
	}
	@RequestMapping("tushuxinxi_yanben5.do")
	public String tushuxinxi_yanben5(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxi_yanben5";
	}
	
	
	
	@RequestMapping("tsxxList.do")
	public String tsxxList(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxilist";
	}
	@RequestMapping("tsxxListtp.do")
	public String tsxxListtp(@RequestParam(value="page",required=false)String page,
			ModelMap map,HttpSession session,Tushuxinxi tushuxinxi, String tushubianhao, String ISBNma, String mingcheng, String leibie, String tupian, String jiage, String chubanshe, String chubandi, String chubanriqi, String suoshuhao, String zhuangtai, String jieyuecishu, String zhaiyao){
		if(page==null||page.equals("")){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page), 8);
		Map<String, Object> pmap=new HashMap<String,Object>();
		pmap.put("pageno", pageBean.getStart());
		pmap.put("pageSize", 8);
		
		
		if(tushubianhao==null||tushubianhao.equals("")){pmap.put("tushubianhao", null);}else{pmap.put("tushubianhao", tushubianhao);}		if(ISBNma==null||ISBNma.equals("")){pmap.put("ISBNma", null);}else{pmap.put("ISBNma", ISBNma);}		if(mingcheng==null||mingcheng.equals("")){pmap.put("mingcheng", null);}else{pmap.put("mingcheng", mingcheng);}		if(leibie==null||leibie.equals("")){pmap.put("leibie", null);}else{pmap.put("leibie", leibie);}		if(tupian==null||tupian.equals("")){pmap.put("tupian", null);}else{pmap.put("tupian", tupian);}		if(jiage==null||jiage.equals("")){pmap.put("jiage", null);}else{pmap.put("jiage", jiage);}		if(chubanshe==null||chubanshe.equals("")){pmap.put("chubanshe", null);}else{pmap.put("chubanshe", chubanshe);}		if(chubandi==null||chubandi.equals("")){pmap.put("chubandi", null);}else{pmap.put("chubandi", chubandi);}		if(chubanriqi==null||chubanriqi.equals("")){pmap.put("chubanriqi", null);}else{pmap.put("chubanriqi", chubanriqi);}		if(suoshuhao==null||suoshuhao.equals("")){pmap.put("suoshuhao", null);}else{pmap.put("suoshuhao", suoshuhao);}		if(zhuangtai==null||zhuangtai.equals("")){pmap.put("zhuangtai", null);}else{pmap.put("zhuangtai", zhuangtai);}		if(jieyuecishu==null||jieyuecishu.equals("")){pmap.put("jieyuecishu", null);}else{pmap.put("jieyuecishu", jieyuecishu);}		if(zhaiyao==null||zhaiyao.equals("")){pmap.put("zhaiyao", null);}else{pmap.put("zhaiyao", zhaiyao);}		
		int total=tushuxinxiService.getCount(pmap);
		pageBean.setTotal(total);
		List<Tushuxinxi> list=tushuxinxiService.getByPage(pmap);
		map.put("page", pageBean);
		map.put("list", list);
		session.setAttribute("p", 1);
		return "tushuxinxilisttp";
	}
	
	@RequestMapping("deleteTushuxinxi.do")
	public String deleteTushuxinxi(int id,HttpServletRequest request){
		tushuxinxiService.delete(id);
		String url = request.getHeader("Referer");
		return "redirect:"+url;
		//return "redirect:tushuxinxiList.do";
	}
	
	
}
