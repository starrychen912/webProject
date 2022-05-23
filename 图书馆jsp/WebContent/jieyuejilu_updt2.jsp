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
		<TITLE>修改借阅记录</TITLE>
	    
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">   
       
	</head>
<%
  String id="";
 
   %>
<script language="javascript">

function gows()
{
	document.location.href="jieyuejilu_add.jsp?id=<%=id%>";
}
function hsgxia2shxurxu(nstr,nwbk)
{
	if (eval("form1."+nwbk).value.indexOf(nstr)>=0)
	{
		eval("form1."+nwbk).value=eval("form1."+nwbk).value.replace(nstr+"；", "");
	}
	else
	{
		eval("form1."+nwbk).value=eval("form1."+nwbk).value+nstr+"；";
	}
}
</script>
	<body>
			<form action="updateJieyuejilu.do" name="form1" method="post">
				      <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1"  style="border-collapse:collapse" bgcolor="#F2FDFF">
						<tr bgcolor="white">
							<td height="30" colspan="2" background="images/table_header.gif"> 续借 
							  <input type="hidden" name="id" value="${jieyuejilu.id}" /></td>
						</tr>
		<tr ><td width="200">借阅时间：</td><td><input name='jieyueshijian' type='text' id='jieyueshijian' value='' onblur='' readonly='readonly' class="form-control" /></td></tr>
		<tr ><td width="200">归还时间：</td><td><input name='guihuanshijian' type='text' id='guihuanshijian' value='<%=connDbBean.getdate()%>' onblur='' readonly='readonly' onClick="WdatePicker({'dateFmt':'yyyy-MM-dd'})"  class="form-control" /></td></tr>
		
		
						<tr align='center'   height="22">
						    <td width="200" height="45"  align="right">&nbsp;						    </td>
						    <td width="75%"  align="left">
						       <input type="submit" name="querenzhuce" id="querenzhuce" value="提交" onClick="return checkform();" class="btn btn-info btn-small" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>
						       <input type="reset" value="重置" class="btn btn-info btn-small" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/>&nbsp;						    </td>
						</tr>
						
	<script language="javascript">document.form1.jieyueshijian.value='${jieyuejilu.jieyueshijian}';</script>
	<script language="javascript">document.form1.guihuanshijian.value='${jieyuejilu.guihuanshijian}';</script>

					 </table>
			</form>
   </body>
</html>






<script language=javascript >  
 
 function checkform(){  
 
	var d1 = new Date(document.form1.jieyueshijian.value);
var d2 = new Date(document.form1.guihuanshijian.value);
console.log(parseInt(d2 - d1));//两个时间相差的毫秒数
console.log(parseInt(d2 - d1) / 1000);//两个时间相差的秒数
console.log(parseInt(d2 - d1) / 1000 / 60);//两个时间相差的分钟数
console.log(parseInt(d2 - d1) / 1000 / 60 /60);//两个时间相差的小时数
    var s=parseInt(d2 - d1) / 1000 / 60 /60/24;
	//alert(s);
if(s>30)
{
	alert("续借总时长不得超过30天");
	return false;
}



}   
popheight=450;
</script>  
