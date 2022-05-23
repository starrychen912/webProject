<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>
<html>
  <head>
    <title>借阅记录详细</title>
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">   

  </head>

  <body >

  借阅记录详细:
  <br><br>
  
   <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#909399" style="border-collapse:collapse">  
   <tr>
     <td width='11%' align="center">图书编号：</td><td width='39%' align="center">${jieyuejilu.tushubianhao}</td>
     <td width='11%' align="center">名称：</td><td width='39%' align="center">${jieyuejilu.mingcheng}</td></tr><tr>
     <td width='11%'  align="center">类别：</td><td width='39%' align="center">${jieyuejilu.leibie}</td>
     <td width='11%' align="center">状态：</td><td width='39%' align="center">${jieyuejilu.zhuangtai}</td></tr><tr>
     <td width='11%' align="center">借阅人：</td><td width='39%' align="center">${jieyuejilu.jieyueren}</td>
     <td width='11%' align="center">借阅时间：</td><td width='39%' align="center">${jieyuejilu.jieyueshijian}</td></tr><tr>
     <td width='11%' align="center">归还时间：</td><td width='39%' align="center">${jieyuejilu.guihuanshijian}</td>
     <td width='11%' align="center">费用：</td><td width='39%' align="center">${jieyuejilu.feiyong}</td></tr><tr>
     <td width='11%' align="center">是否归还：</td><td width='39%' align="center">${jieyuejilu.shifouguihuan}</td>
     <td>&nbsp;</td><td>&nbsp;</td></tr><tr><td colspan=4 align=center><input type=button name=Submit5 value=返回 onClick="javascript:history.back()" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>&nbsp;<input type=button name=Submit5 value=打印 onClick="javascript:window.print()" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/></td></tr>
    
  </table>

  </body>
</html>

