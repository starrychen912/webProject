<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>
<html>
  <head>
    <title>读者信息详细</title>
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">   

  </head>
  <style>
    
  </style>
  <body >

  读者信息详细:
  <br><br>
  
   <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1" bordercolor="#909399" style="border-collapse:collapse">  
   <tr>
     <td width='11%' height=44 align="center">学号：</td><td width='39%' align="center">${xueshengxinxi.xuehao}</td>
<td  rowspan=10 align=center><a href=${xueshengxinxi.zhaopian} target=_blank align="center"><img src=${xueshengxinxi.zhaopian} width=228 height=215 border=0></a></td></tr><tr>
<td width='11%' height=44 align="center">密码：</td><td width='39%' align="center">${xueshengxinxi.mima}</td>
</tr><tr>
<td width='11%' height=44 align="center">姓名：</td><td width='39%' align="center">${xueshengxinxi.xingming}</td>
</tr><tr>
<td width='11%' height=44 align="center">性别：</td><td width='39%' align="center">${xueshengxinxi.xingbie}</td>
</tr><tr>
<td width='11%' height=44 align="center">身份证：</td><td width='39%' align="center">${xueshengxinxi.shenfenzheng}</td>
</tr><tr>
<td width='11%' height=44 align="center">电话：</td><td width='39%' align="center">${xueshengxinxi.dianhua}</td>
</tr><tr>
<td width='11%' height=44 align="center">院系：</td><td width='39%' align="center">${xueshengxinxi.yuanxi}</td>
</tr><tr>
<td width='11%' height=44 align="center">班级：</td><td width='39%' align="center">${xueshengxinxi.banji}</td>
</tr><tr>
<td width='11%' height=44 align="center">籍贯：</td><td width='39%' align="center">${xueshengxinxi.jiguan}</td>
</tr><tr>

<td width='11%' height=44 align="center">余额：</td><td width='39%' align="center">${xueshengxinxi.yue}</td>
</tr><tr>

<td width='11%' height=100   align="center">备注：</td><td width='39%' colspan=2 height=100  align="center">${xueshengxinxi.beizhu}</td></tr><tr><td colspan=3 align=center><input type=button name=Submit5 value=返回 onClick="javascript:history.back()" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>&nbsp;<input type=button name=Submit5 value=打印 onClick="javascript:window.print()" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/></td></tr>
    
  </table>

  </body>
</html>

