<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>

<%
response.setContentType("application/vnd.ms-excel");
response.addHeader("Content-Disposition", "attachment;filename=jieyuejilu.xls");
%>
<html>
  <head>
    <title>借阅记录</title>
  </head>

  <body >

<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1">  
  <tr>
    <td width="30" align="center">序号</td>
    <td align='center'>图书编号</td>    <td align='center'>名称</td>    <td align='center'>类别</td>    <td align='center'>状态</td>    <td align='center'>借阅人</td>    <td align='center'>借阅时间</td>    <td align='center'>归还时间</td>    <td align='center'>费用</td>    <td align='center'>是否归还</td>    
	
    <td width="120" align="center" >添加时间</td>
    
  </tr>
   <%
    int i=0;
   String sql="select * from jieyuejilu  order by id desc";
ResultSet RS_result=connDbBean.executeQuery(sql);
			while(RS_result.next())
			{
				i++;
			%>
  <tr>
    <td  align="center"><%=i %></td>
    <td><%=RS_result.getString("tushubianhao")%></td>    <td><%=RS_result.getString("mingcheng")%></td>    <td><%=RS_result.getString("leibie")%></td>    <td><%=RS_result.getString("zhuangtai")%></td>    <td><%=RS_result.getString("jieyueren")%></td>    <td><%=RS_result.getString("jieyueshijian")%></td>    <td><%=RS_result.getString("guihuanshijian")%></td>    <td><%=RS_result.getString("feiyong")%></td>    <td><%=RS_result.getString("shifouguihuan")%></td>    
	
	<td><%=RS_result.getString("addtime")%></td>
   
  </tr>
  	<%
  }
   %>
   
</table>
<br>
  </body>
</html>

