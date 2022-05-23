<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>
<html>
  <head>
    <title>图书信息详细</title>
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">   

  </head>

  <body >

  图书信息详细:
  <br><br>
  
   <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#909399" style="border-collapse:collapse">  
   <tr>
     <td width='11%' height=44 align="center">图书编号：</td><td width='39%' align="center">${tushuxinxi.tushubianhao}</td>
<td  rowspan=11 align=center><a href=${tushuxinxi.tupian} target=_blank><img src=${tushuxinxi.tupian} width=228 height=215 border=0></a></td></tr><tr>
<td width='11%' height=44 align="center">ISBN码：</td><td width='39%' align="center">${tushuxinxi.ISBNma}</td>
</tr><tr>
<td width='11%' height=44 align="center">名称：</td><td width='39%' align="center">${tushuxinxi.mingcheng}</td>
</tr><tr>
<td width='11%' height=44 align="center">类别：</td><td width='39%' align="center">${tushuxinxi.leibie}</td>
</tr><tr>

<td width='11%' height=44 align="center">价格：</td><td width='39%' align="center">${tushuxinxi.jiage}</td>
</tr><tr>
<td width='11%' height=44 align="center">出版社：</td><td width='39%' align="center">${tushuxinxi.chubanshe}</td>
</tr><tr>
<td width='11%' height=44 align="center">作者：</td><td width='39%' align="center">${tushuxinxi.chubandi}</td>
</tr><tr>
<td width='11%' height=44 align="center">出版日期：</td><td width='39%' align="center">${tushuxinxi.chubanriqi}</td>
</tr><tr>
<td width='11%' height=44 align="center">索书号：</td><td width='39%' align="center">${tushuxinxi.suoshuhao}</td>
</tr><tr>
<td width='11%' height=44 align="center">状态：</td><td width='39%' align="center">${tushuxinxi.zhuangtai}</td>
</tr><tr>
<td width='11%' height=44 align="center">借阅次数：</td><td width='39%' align="center">${tushuxinxi.jieyuecishu}</td>
</tr><tr>

<td width='11%' height=100  align="center" >摘要：</td><td width='39%' colspan=2 height=100  align="center">${tushuxinxi.zhaiyao}</td></tr><tr><td colspan=3 align=center><input type=button name=Submit5 value=返回 onClick="javascript:history.back()" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>&nbsp;<input type=button name=Submit5 value=打印 onClick="javascript:window.print()" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/></td></tr>
    
  </table>

  </body>
</html>

