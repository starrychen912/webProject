<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page language="java" import="java.sql.*" %>
<jsp:useBean id="connDbBean" scope="page" class="com.util.db"/>

<%
response.setContentType("application/vnd.ms-excel");
response.addHeader("Content-Disposition", "attachment;filename=tushuxinxi.xls");
%>
<html>
  <head>
    <title>ͼ����Ϣ</title>
  </head>

  <body >

<table width="100%" border="1" align="center" cellpadding="3" cellspacing="1">  
  <tr>
    <td width="30" align="center">���</td>
    <td align='center'>ͼ����</td>
    <td align='center'>ISBN��</td>
    <td align='center'>����</td>
    <td align='center'>���</td>
    <td align='center'>ͼƬ</td>
    <td align='center'>�۸�</td>
    <td align='center'>������</td>
    <td align='center'>����</td>
    <td align='center'>��������</td>
    <td align='center'>�����</td>
    <td align='center'>״̬</td>
    <td align='center'>���Ĵ���</td>
    <td align='center'>ժҪ</td>
    
	
    <td width="120" align="center" >���ʱ��</td>
    
  </tr>
   <%
    int i=0;
   String sql="select * from tushuxinxi  order by id desc";
ResultSet RS_result=connDbBean.executeQuery(sql);
			while(RS_result.next())
			{
				i++;
			%>
  <tr>
    <td  align="center"><%=i %></td>
    <td><%=RS_result.getString("tushubianhao")%></td>
    <td><%=RS_result.getString("ISBNma")%></td>
    <td><%=RS_result.getString("mingcheng")%></td>
    <td><%=RS_result.getString("leibie")%></td>
    <td><%=RS_result.getString("tupian")%></td>
    <td><%=RS_result.getString("jiage")%></td>
    <td><%=RS_result.getString("chubanshe")%></td>
    <td><%=RS_result.getString("chubandi")%></td>
    <td><%=RS_result.getString("chubanriqi")%></td>
    <td><%=RS_result.getString("suoshuhao")%></td>
    <td><%=RS_result.getString("zhuangtai")%></td>
    <td><%=RS_result.getString("jieyuecishu")%></td>
    <td><%=RS_result.getString("zhaiyao")%></td>
    
	
	<td><%=RS_result.getString("addtime")%></td>
   
  </tr>
  	<%
  }
   %>
   
</table>
<br>
  </body>
</html>

