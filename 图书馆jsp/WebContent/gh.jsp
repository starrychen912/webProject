<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>
<%@ page isELIgnored="false" %> 


<%
String yuan=request.getParameter("yuan");
String id=request.getParameter("id");
String tablename=request.getParameter("tablename");
String sql="";

	sql="update jieyuejilu set shifouguihuan='是' where id="+request.getParameter("id");


connDbBean.hsgexecute(sql);

sql="update tushuxinxi set zhuangtai='空闲' where tushubianhao="+request.getParameter("tushubianhao");


connDbBean.hsgexecute(sql);


java.util.Date date = new java.util.Date();
java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);

String result = format.format(date);
int tt=0;
			 sql="select DATEDIFF('"+result+"',guihuanshijian) as ss from jieyuejilu where id="+id;
			//out.print(sql);
			
			ResultSet RS_result=connDbBean.executeQuery(sql);
 while(RS_result.next())
 {
 tt=RS_result.getInt("ss");
 }
 
 if(tt>0)
 {
 	sql="update xueshengxinxi set yue=yue-"+tt+" where xuehao='"+request.getParameter("jyr")+"'";	
	
connDbBean.hsgexecute(sql);
 	out.print("<script language='javascript'>alert('未还提醒：您书借阅超时"+tt+"天未还，将对您进行"+tt+"元的处罚!');</script>");
 }
 

out.print("<script>javascript:alert('操作成功！');location.href='jieyuejiluList.do';</script>");


%>



