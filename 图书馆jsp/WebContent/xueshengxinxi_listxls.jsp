<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>

<%
response.setContentType("application/vnd.ms-excel");
response.addHeader("Content-Disposition", "attachment;filename=xueshengxinxi.xls");
%>
<html>
  <head>
    <title>������Ϣ</title>
  </head>

  <body >

<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1">  
  <tr>
    <td width="30" align="center">���</td>
    <td align='center'>ѧ��</td>
    <td align='center'>����</td>
    <td align='center'>����</td>
    <td align='center'>�Ա�</td>
    <td align='center'>���֤</td>
    <td align='center'>�绰</td>
    <td align='center'>Ժϵ</td>
    <td align='center'>�༶</td>
    <td align='center'>����</td>
    <td align='center'>��Ƭ</td>
    <td align='center'>���</td>
    <td align='center'>��ע</td>
    
	
    <td width="120" align="center" >���ʱ��</td>
    
  </tr>
   <%
    int i=0;
   String sql="select * from xueshengxinxi  order by id desc";
ResultSet RS_result=connDbBean.executeQuery(sql);
			while(RS_result.next())
			{
				i++;
			%>
  <tr>
    <td  align="center"><%=i %></td>
    <td><%=RS_result.getString("xuehao")%></td>
    <td><%=RS_result.getString("mima")%></td>
    <td><%=RS_result.getString("xingming")%></td>
    <td><%=RS_result.getString("xingbie")%></td>
    <td><%=RS_result.getString("shenfenzheng")%></td>
    <td><%=RS_result.getString("dianhua")%></td>
    <td><%=RS_result.getString("yuanxi")%></td>
    <td><%=RS_result.getString("banji")%></td>
    <td><%=RS_result.getString("jiguan")%></td>
    <td><%=RS_result.getString("zhaopian")%></td>
    <td><%=RS_result.getString("yue")%></td>
    <td><%=RS_result.getString("beizhu")%></td>
    
	
	<td><%=RS_result.getString("addtime")%></td>
   
  </tr>
  	<%
  }
   %>
   
</table>
<br>
  </body>
</html>

