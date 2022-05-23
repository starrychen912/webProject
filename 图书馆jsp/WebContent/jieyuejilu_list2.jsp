<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>


<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" charset="gb2312"></script>
<script type="text/javascript" src="js/popup.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<TITLE>借阅记录查询</TITLE>
	<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">
	<link rel="stylesheet" href="images/bootstrap.min.css" type="text/css">
	</head>
	   <script language="javascript">
		   function zhifu(id,biao)
           {
                   window.location.href="zhifu/index.jsp?id="+id+"&biao="+biao;
           }
       </script>
	<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td>
					<table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
						<tr align="left" bgcolor="white">
							<td colspan="17" background="images/table_header.gif">借阅记录列表</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr valign="top">
			  <td bgcolor="#FFFFFF">
			  <table width="100%" border="1" align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
						
					<tr align="right" bgcolor="white">
					  <td colspan="21">
					  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" >
						<tr align="left" >
							<td colspan="17">
								<form action="jieyuejiluList2.do" name="myform" method="post">
									图书编号：<input name="tushubianhao" type="text" id="tushubianhao" class="form-control2" />  名称：<input name="mingcheng" type="text" id="mingcheng" class="form-control2" />
									<input type="submit" value="查询" style="background-color:#3c3f3c ;border-color: #E4E7ED;"/>
								</form>								</td>
						</tr></table>
					  </td>
			    </tr>
					
						<tr align="center">
						<td style="padding-left:0px; padding-right:0px;"><table width="100%" border="1" align="center" cellpadding="4" cellspacing="1" bgcolor="white" class="table table-striped table-bordered table-hover">
                          <tr>
                            <td width="30px" height="30" align="center" bgcolor="white">序号</td>
                            <td align='center' bgcolor='white'>图书编号</td>
    <td align='center' bgcolor='white'>名称</td>
    <td align='center' bgcolor='white'>类别</td>
    <td align='center' bgcolor='white'>状态</td>
    <td align='center' bgcolor='white'>借阅人</td>
    <td  width='65' align='center' bgcolor='white'>借阅时间</td>
    <td  width='65' align='center' bgcolor='white'>归还时间</td>
    <td align='center' bgcolor='white'>费用</td>
    <td align='center' bgcolor='white'>是否归还</td>
    
                            <td width="150px" height="30" align="center" bgcolor="white"> 添加时间 </td>
                            <td width="150px" height="30" align="center" bgcolor="white"> 操作 </td>
                          </tr>
                          <%
					int i=0;
				%>
                          <c:forEach items="${list }" var="u">
                            <%
					i++;
				%>
                            <tr align="center" bgcolor="#FFFFFF">
                              <td height="45" align="center"><%=i%></td>
                             <td>${u.tushubianhao}</td>
    <td>${u.mingcheng}</td>
    <td>${u.leibie}</td>
    <td>${u.zhuangtai}</td>
    <td>${u.jieyueren}</td>
    <td>${u.jieyueshijian}</td>
    <td>${u.guihuanshijian}</td>
    <td>${u.feiyong}</td>
    <td>${u.shifouguihuan}
	
	</td>
    
                              <td height="45" align="center"> ${u.addtime } </td>
                              <td height="45" align="center"> <a class="btn btn-info btn-small" href="doUpdateJieyuejilu2.do?id=${u.id }" style="background-color:#3c3f3c ;border-color: #E4E7ED;">续借</a>   <a class="btn btn-info btn-small" href="jieyuejiluDetail.do?id=${u.id }" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;">详细</a></td>
                            </tr>
                          </c:forEach>
                        </table></td>
						</tr>
					</table>
					
					
			  </td>
	  </tr>
		</table>
		
		
		
			<p align="center" class="fy"> <c:if test="${sessionScope.p==1 }">
		 <c:if test="${page.page>1}">
              <a href="jieyuejiluList2.do?page=1" >首页</a>
             <a href="jieyuejiluList2.do?page=${page.page-1 }"> 上一页</a>             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="jieyuejiluList2.do?page=${page.page+1 }">下一页</a>
			<a href="jieyuejiluList2.do?page=${page.totalPage }">末页</a>		    </c:if>		
	</c:if>
	</p>
			
			
			<%
			java.util.Date date = new java.util.Date();
java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.CHINA);

String result = format.format(date);
int tt=0;
			String sql="select id from jieyuejilu where shifouguihuan='否' and DATEDIFF(guihuanshijian,'"+result+"')<0 and jieyueren='"+request.getSession().getAttribute("username")+"'";
			//out.print(sql);
			
			ResultSet RS_result=connDbBean.executeQuery(sql);
 while(RS_result.next())
 {
 tt++;
 }
 
 if(tt>0)
 {
 	
 	out.print("<script language='javascript'>alert('未还提醒：您书借阅超时未还，将对您进行超期每天一元的处罚!');</script>");
 }
			%>
			
			
	</body>
</html>
