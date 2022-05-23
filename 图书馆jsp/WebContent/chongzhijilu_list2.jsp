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
	<TITLE>充值记录查询</TITLE>
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
							<td colspan="17" background="images/table_header.gif">充值记录列表</td>
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
								<form action="chongzhijiluList2.do" name="myform" method="post">
									查询   学号：<input name="xuehao" type="text" id="xuehao" class="form-control2" />  姓名：<input name="xingming" type="text" id="xingming" class="form-control2" />
									<input type="submit" value="查询" style="background-color:#3c3f3c ;border-color: #E4E7ED;"/>
								</form>								</td>
						</tr></table>
					  </td>
			    </tr>
					
						<tr align="center">
						<td style="padding-left:0px; padding-right:0px;"><table width="100%" border="1" align="center" cellpadding="4" cellspacing="1" bgcolor="white" class="table table-striped table-bordered table-hover">
                          <tr>
                            <td width="30px" height="30" align="center" bgcolor="white">序号</td>
                            <td align='center' bgcolor='white'>学号</td>
    <td align='center' bgcolor='white'>姓名</td>
    <td align='center' bgcolor='white'>余额</td>
    <td align='center' bgcolor='white'>充值金额</td>
    
    
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
                             <td>${u.xuehao}</td>
    <td>${u.xingming}</td>
    <td>${u.yue}</td>
    <td>${u.chongzhijine}</td>
    
    
                              <td height="45" align="center"> ${u.addtime } </td>
                              <td height="45" align="center"> 
							  <a class="btn btn-info btn-small" href="chongzhijiluDetail.do?id=${u.id }" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;">详细</a></td>
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
              <a href="chongzhijiluList2.do?page=1" >首页</a>
             <a href="chongzhijiluList2.do?page=${page.page-1 }"> 上一页</a>             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="chongzhijiluList2.do?page=${page.page+1 }">下一页</a>
			<a href="chongzhijiluList2.do?page=${page.totalPage }">末页</a>		    </c:if>		
	</c:if>
	</p>
			
			
			
			
			
	</body>
</html>
