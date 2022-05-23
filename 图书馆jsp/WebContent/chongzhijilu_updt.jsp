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
		<TITLE>修改充值记录</TITLE>
	    
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">   
       
	</head>
<%
  String id="";
 
   %>
<script language="javascript">

function gows()
{
	document.location.href="chongzhijilu_add.jsp?id=<%=id%>";
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
			<form action="updateChongzhijilu.do" name="form1" method="post">
				      <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1"  style="border-collapse:collapse" bgcolor="#F2FDFF">
						<tr bgcolor="white">
							<td height="30" colspan="2" background="images/table_header.gif">修改充值记录<input type="hidden" name="id" value="${chongzhijilu.id}" /></td>
						</tr>
						<tr ><td width="200" align="center">学号：</td><td><select name='xuehao' id='xuehao' class="form-control" onChange='gows();'><%=connDbBean.hsggetoption2("xueshengxinxi","xuehao")%></select>&nbsp;*<label id='clabelxuehao' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">姓名：</td><td><input name='xingming' type='text' id='xingming'  class="form-control" >&nbsp;*<label id='clabelxingming' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">余额：</td><td><input name='yue' type='text' id='yue'  class="form-control" ></td></tr>
		<tr ><td width="200" align="center">充值金额：</td><td><input name='chongzhijine' type='text' id='chongzhijine' value='' onblur='checkform()' class="form-control" />&nbsp;<label id='clabelchongzhijine' style='margin-top:16px;' />必需数字型</td></tr>
		<tr ><td width="200" align="center">备注：</td><td><textarea name='beizhu' cols='50' rows='5' id='beizhu' onblur='' class="form-control" style="width:600px; height:80px;" ></textarea></td></tr>
		
		
						<tr align='center'   height="22">
						    <td width="25%" height="45"  align="right">&nbsp;						    </td>
						    <td width="75%"  align="left">
						       <input type="submit" name="querenzhuce" id="querenzhuce" value="提交" onClick="return checkform();" class="btn btn-info btn-small" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>
						       <input type="reset" value="重置" class="btn btn-info btn-small" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/>&nbsp;
						    </td>
						</tr>
						<script language="javascript">document.form1.xuehao.value='${chongzhijilu.xuehao}';</script>
	<script language="javascript">document.form1.xingming.value='${chongzhijilu.xingming}';</script>
	<script language="javascript">document.form1.yue.value='${chongzhijilu.yue}';</script>
	<script language="javascript">document.form1.chongzhijine.value='${chongzhijilu.chongzhijine}';</script>
	<script language="javascript">document.form1.beizhu.value='${chongzhijilu.beizhu}';</script>
	
					 </table>
			</form>
   </body>
</html>






<script language=javascript >  
 
 function checkform(){  
 
	var xuehaoobj = document.getElementById("xuehao"); if(xuehaoobj.value==""){document.getElementById("clabelxuehao").innerHTML="&nbsp;&nbsp;<font color=red>请输入学号</font>";return false;}else{document.getElementById("clabelxuehao").innerHTML="  "; } 
	var xingmingobj = document.getElementById("xingming"); if(xingmingobj.value==""){document.getElementById("clabelxingming").innerHTML="&nbsp;&nbsp;<font color=red>请输入姓名</font>";return false;}else{document.getElementById("clabelxingming").innerHTML="  "; } 
	
    


return true;   
}   
popheight=450;
</script>  
