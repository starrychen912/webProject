<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>


<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" charset="gb2312"></script>
<script type="text/javascript" src="js/popup.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

function submitForm(type){
	console.log("当前按钮类型：",type);
	document.getElementById("type").value=type;
	if(type==2){
		document.getElementsByName("myform")[0].action="tushuxinxiListKeyword.do";
	}
	return true;
}
</script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<TITLE>图书信息查询</TITLE>
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
							<td height="30" colspan="17" background="images/table_header.gif">图书信息列表</td>
						</tr>
					</table>
				</td>
			</tr>
			
			<tr valign="top">
			  <td bgcolor="#FFFFFF">
			  <table width="100%" border="1" align="center" cellpadding="4" cellspacing="1" bgcolor="white">
						
					<tr align="right" bgcolor="white">
					  <td colspan="21">
					  <table width="100%" border="0" align="center" cellpadding="4" cellspacing="1" >
						<tr align="left" >
							<td colspan="17">
								<form action="tushuxinxiListEqual.do" name="myform"  method="post">
<!-- 								<form action="tushuxinxiList.do" name="myform" id="myform" method="post"> -->
									请输入   
<!-- 									图书编号：<input name="tushubianhao" type="text" id="tushubianhao" class="form-control2" />   -->
									名称/关键字：<input name="mingcheng" type="text" id="mingcheng" class="form-control2" />
<!-- 									出版社： -->
<!-- 									<input name="chubanshe" type="text" id="chubanshe" class="form-control2" /> -->
<!-- 									作者： -->
<!-- 									<input name="chubandi" type="text" id="chubandi" class="form-control2" /> -->
									<input type="hidden" name="type" value="1" id="type"/>
									<input type="submit" onclick="submitForm(1)" value="书名查询" class="btn btn-info btn-small" style="background-color:#3c3f3c ;border-color: #E4E7ED;"/> 
									<input type="submit" onclick="submitForm(2)" value="关键字查询" class="btn btn-info btn-small" style="background-color: #7e817e;"/> 
									<input type="button" value="导出excel" onClick="javascript:location.href='tushuxinxi_listxls.jsp';" class="btn btn-info btn-small" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/>
								</form>								</td>
						</tr></table>
					  </td>
			    </tr>
					
						<tr align="center">
						<td style="padding-left:0px; padding-right:0px;"><table width="100%" border="1" align="center" cellpadding="4" cellspacing="1" bordercolor="#F3F3F3" bgcolor="white" class="table table-striped table-bordered table-hover">
                          <tr>
                            <td align="center" bgcolor="white" width="30px" height="50">序号</td>
                            <td align='center' bgcolor='white'>图书编号</td>
    <td align='center' bgcolor='white'>ISBN码</td>
    <td align='center' bgcolor='white'>名称</td>
    <td align='center' bgcolor='white'>类别</td>
    <td  width='90' align='center' bgcolor='white'>图片</td>
    <td align='center' bgcolor='white'>价格</td>
    <td align='center' bgcolor='white'>出版社</td>
    <td align='center' bgcolor='white'>作者</td>
    <td align='center' bgcolor='white'>出版日期</td>
    <td align='center' bgcolor='white'>索书号</td>
    <td align='center' bgcolor='white'>状态</td>
    <td align='center' bgcolor='white'>借阅次数</td>
    
    
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
                             <td>${u.tushubianhao}</td>
    <td>${u.ISBNma}</td>
    <td>${u.mingcheng}</td>
    <td>${u.leibie}</td>
    <td width='90' align='center' bgcolor='white'><a href='${u.tupian}' target='_blank'><img src='${u.tupian}' width=88 height=99 border=0 /></a></td>
    <td>${u.jiage}</td>
    <td>${u.chubanshe}</td>
    <td>${u.chubandi}</td>
    <td>${u.chubanriqi}</td>
    <td>${u.suoshuhao}</td>
    <td>${u.zhuangtai}</td>
    <td>${u.jieyuecishu}</td>
    
    
                              <td height="50" align="center" > ${u.addtime } </td>
                              <td height="50" align="center"><a class="btn btn-info btn-small" href="doUpdateTushuxinxi.do?id=${u.id }" style="background-color:#3c3f3c ;border-color: #E4E7ED;">编辑</a> <a class="btn btn-info btn-small" href="tushuxinxiDetail.do?id=${u.id}" style="background-color:#7e817e ;border-color: #E4E7ED;color: white;">详细</a> <a class="btn btn-info btn-small" href="deleteTushuxinxi.do?id=${u.id }"
										onclick="{if(confirm('确定要删除吗?')){return true;}return false;}" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;">删除</a> </td>
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
              <a href="tushuxinxiList.do?page=1" >首页</a>
             <a href="tushuxinxiList.do?page=${page.page-1 }"> 上一页</a>             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="tushuxinxiList.do?page=${page.page+1 }">下一页</a>
			<a href="tushuxinxiList.do?page=${page.totalPage }">末页</a>		    </c:if>		
	</c:if>
	</p>
			
			
			
			
			
	</body>
</html>
