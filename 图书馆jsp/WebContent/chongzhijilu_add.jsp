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
		<TITLE>添加充值记录</TITLE>
	    
 	<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">    
	</head>
<%
  String id="";
  id=request.getParameter("id");
   %>

<script language="javascript">

function gows()
{
	document.location.href="chongzhijilu_add.jsp?id=<%=id%>&xuehao="+document.form1.xuehao.value;
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
			<form action="addChongzhijilu.do" name="form1" method="post">
				      <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1"  style="border-collapse:collapse" bgcolor="#F2FDFF">
						<tr bgcolor="white">
							<td height="30" colspan="2" background="images/table_header.gif">添加充值记录</td>
						</tr>
						<tr ><td width="200" align="center">学号：</td>
						  <td>&nbsp;
						    <input name='xuehao' type='text' id='xuehao'  class="form-control">
						    *
					      <label id='clabelxuehao' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">姓名：</td><td><input name='xingming' type='text' id='xingming'  class="form-control" >&nbsp;*<label id='clabelxingming' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">余额：</td><td><input name='yue' type='text' id='yue'  class="form-control" ></td></tr>
		<tr ><td width="200" align="center">充值金额：</td><td><input name='chongzhijine' type='text' id='chongzhijine' value='' onblur='checkform()' class="form-control" />&nbsp;<label id='clabelchongzhijine' style='margin-top:16px;' />必需数字型</td></tr>
		<tr ><td width="200" align="center">备注：</td><td><textarea name='beizhu' cols='50' rows='5' id='beizhu' onblur='' class="form-control" style="width:600px; height:80px;" ></textarea></td></tr>
		
		
						<tr align='center'   height="22">
						    <td height="45"  align="right">&nbsp;						    </td>
						    <td width="75%"  align="left">
						       <input type="submit" name="querenzhuce" id="querenzhuce" value="提交" onClick="return checkform();" class="btn btn-info btn-small" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>
						       <input type="reset" value="重置" name="Submit2" class="btn btn-info btn-small" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>

<%
if(request.getParameter("xuehao")==null || request.getParameter("xuehao").equals("")){}else{
%>
<script language="javascript">
document.form1.xuehao.value="<%=connDbBean.readzd("xueshengxinxi","xuehao","xuehao",request.getParameter("xuehao"))%>";
document.form1.xingming.value="<%=connDbBean.readzd("xueshengxinxi","xingming","xuehao",request.getParameter("xuehao"))%>";
document.form1.yue.value="<%=connDbBean.readzd("xueshengxinxi","yue","xuehao",request.getParameter("xuehao"))%>";


</script>
<%
}
%>





<script language=javascript >  
 
 function checkform(){  
 
	var xuehaoobj = document.getElementById("xuehao"); if(xuehaoobj.value==""){document.getElementById("clabelxuehao").innerHTML="&nbsp;&nbsp;<font color=red>请输入学号</font>";return false;}else{document.getElementById("clabelxuehao").innerHTML="  "; } 
	var xingmingobj = document.getElementById("xingming"); if(xingmingobj.value==""){document.getElementById("clabelxingming").innerHTML="&nbsp;&nbsp;<font color=red>请输入姓名</font>";return false;}else{document.getElementById("clabelxingming").innerHTML="  "; } 
	var chongzhijineobj = document.getElementById("chongzhijine"); if(chongzhijineobj.value!=""){ if(/^[0-9]+.?[0-9]*$/.test(chongzhijineobj.value)){document.getElementById("clabelchongzhijine").innerHTML=""; }else{document.getElementById("clabelchongzhijine").innerHTML="&nbsp;&nbsp;<font color=red>必需数字型</font>"; return false;}}  
    


return true;   
}   
popheight=450;
</script>  
<script src="images/hsgcommon/jq.js"></script>
<script src="images/hsgcommon/common.js"></script>
<script src="images/hsgcommon/bootbox.js"></script>