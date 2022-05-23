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
	<TITLE>读者信息查询</TITLE>
	<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">
	<link rel="stylesheet" href="images/bootstrap.min.css" type="text/css">
	</head>
	<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td>
					<table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" bgcolor="#aec3de">
						<tr align="left" bgcolor="white">
							<td height="30" colspan="17" background="images/table_header.gif">读者信息列表</td>
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
								<form action="xueshengxinxiList.do" name="myform" method="post">
									   学号：<input name="xuehao" type="text" id="xuehao" class="form-control2" />  姓名：<input name="xingming" type="text" id="xingming" class="form-control2" /> 性别：<select name='xingbie' id='xingbie' class="form-control2"><option value="">所有</option><option value="男">男</option><option value="女">女</option></select>  身份证：<input name="shenfenzheng" type="text" id="shenfenzheng" class="form-control2" />  电话：<input name="dianhua" type="text" id="dianhua" class="form-control2" />  院系：<input name="yuanxi" type="text" id="yuanxi" class="form-control2" />  班级：<input name="banji" type="text" id="banji" class="form-control2" />  籍贯：<input name="jiguan" type="text" id="jiguan" class="form-control2" />
									<input type="submit" value="查询" class="btn btn-info btn-small" style="background-color:#3c3f3c ;"/> <input type="button" value="导出excel" onClick="javascript:location.href='xueshengxinxi_listxls.jsp';" class="btn btn-info btn-small" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;" />
								</form>								</td>
						</tr></table>
					  </td>
			    </tr>
					
						<tr align="center">
						<td style="padding-left:0px; padding-right:0px;"><table width="100%" border="1" align="center" cellpadding="4" cellspacing="1" bordercolor="#F3F3F3" bgcolor="white" class="table table-striped table-bordered table-hover">
                          <tr>
                            <td align="center" bgcolor="white" width="30px" height="50">序号</td>
                            <td align='center' bgcolor='white'>学号</td>
    <td align='center' bgcolor='white'>密码</td>
    <td align='center' bgcolor='white'>姓名</td>
    <td  width='40' align='center' bgcolor='white'>性别</td>
    <td align='center' bgcolor='white'>身份证</td>
    <td align='center' bgcolor='white'>电话</td>
    <td align='center' bgcolor='white'>院系</td>
    <td align='center' bgcolor='white'>班级</td>
    <td align='center' bgcolor='white'>籍贯</td>
    <td  width='90' align='center' bgcolor='white'>照片</td>
    <td align='center' bgcolor='white'>余额</td>
    
    
                            <td width="150px" height="50" align="center" bgcolor="white"> 添加时间 </td>
                            <td width="150px" height="50" align="center" bgcolor="white"> 操作 </td>
                          </tr>
                          <%
					int i=0;
				%>
                          <c:forEach items="${list }" var="u">
                            <%
					i++;
				%>
                            <tr align="center">
                              <td height="50" align="center"><%=i%></td>
                             <td>${u.xuehao}</td>
    <td>${u.mima}</td>
    <td>${u.xingming}</td>
    <td align='center' bgcolor='white'>${u.xingbie}</td>
    <td>${u.shenfenzheng}</td>
    <td>${u.dianhua}</td>
    <td>${u.yuanxi}</td>
    <td>${u.banji}</td>
    <td>${u.jiguan}</td>
    <td width='90' align='center' bgcolor='white'><a href='${u.zhaopian}' target='_blank'><img src='${u.zhaopian}' width=88 height=99 border=0 /></a></td>
    <td>${u.yue}</td>
    
    
                              <td height="50" align="center" > ${u.addtime } </td>
                              <td height="50" align="center"><a class="btn btn-info btn-small" href="doUpdateXueshengxinxi.do?id=${u.id }" style="background-color:#3c3f3c ;border-color: white ;">编辑</a> <a class="btn btn-info btn-small" href="xueshengxinxiDetail.do?id=${u.id}" style="background-color:#7e817e ;border-color: #E4E7ED;color: white;">详细</a> <a class="btn btn-info btn-small" href="deleteXueshengxinxi.do?id=${u.id }"
										onclick="{if(confirm('确定要删除吗?')){return true;}return false;} " style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;">删除</a> </td>
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
              <a href="xueshengxinxiList.do?page=1" >首页</a>
             <a href="xueshengxinxiList.do?page=${page.page-1 }"> 上一页</a>             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="xueshengxinxiList.do?page=${page.page+1 }" style="border:1px solid white ;color: black;">下一页</a>
			<a href="xueshengxinxiList.do?page=${page.totalPage }" style="border:1px solid white ;color: black;">末页</a>		    </c:if>		
	</c:if>
	</p>
			
			
			
			
			
	</body>
</html>
