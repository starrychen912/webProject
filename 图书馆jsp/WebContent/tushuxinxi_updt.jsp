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
		<TITLE>修改图书信息</TITLE>
	    
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">   
       
	</head>
<%
  String id="";
 
   %>
<script language="javascript">

function gows()
{
	document.location.href="tushuxinxi_add.jsp?id=<%=id%>";
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
			<form action="updateTushuxinxi.do" name="form1" method="post">
				      <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1"  style="border-collapse:collapse" bgcolor="#F2FDFF">
						<tr bgcolor="white">
							<td height="30" colspan="2" background="images/table_header.gif">修改图书信息<input type="hidden" name="id" value="${tushuxinxi.id}" /></td>
						</tr>
						<tr ><td width="200" align="center">图书编号：</td><td><input name='tushubianhao' type='text' id='tushubianhao' value='' onblur='checkform()' class="form-control" />&nbsp;*<label id='clabeltushubianhao' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">ISBN码：</td><td><input name='ISBNma' type='text' id='ISBNma' value='' onblur='' class="form-control" /></td></tr>
		<tr ><td width="200" align="center">名称：</td><td><input name='mingcheng' type='text' id='mingcheng' value='' onblur='checkform()' class="form-control" />&nbsp;*<label id='clabelmingcheng' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center"><tr><td align="center">类别：</td><td><select name='leibie' id='leibie' class="form-control"><%=connDbBean.hsggetoption("tushuleibie","leibie")%></select></td></tr>
		<tr ><td width="200" align="center">图片：</td><td><input name='tupian' type='text' id='tupian' size='50' value='' onblur=''  class="form-control" /><div style="margin-top:16px;">&nbsp;<input type='button' value='上传' onClick="up('tupian')" style="background-color:#7e817e ;border-color: #E4E7ED;color: #3c3f3c;"/></div></td></tr>
		<tr ><td width="200" align="center">价格：</td><td><input name='jiage' type='text' id='jiage' value='' onblur='' class="form-control" /></td></tr>
		<tr ><td width="200" align="center">出版社：</td><td><input name='chubanshe' type='text' id='chubanshe' value='' onblur='' size='50' class="form-control" style="width:600px;" /></td></tr>
		<tr ><td width="200" align="center">作者：</td><td><input name='chubandi' type='text' id='chubandi' value='' onblur='' class="form-control" /></td></tr>
		<tr ><td width="200" align="center">出版日期：</td><td><input name='chubanriqi' type='text' id='chubanriqi' value='' onblur='' class="form-control" /></td></tr>
		<tr 0><td width="200" align="center">索书号：</td><td><input name='suoshuhao' type='text' id='suoshuhao' value='' onblur='' class="form-control" /></td></tr>
		<tr 1><td width="200" align="center"><tr><td align="center">状态：</td><td><select name='zhuangtai' id='zhuangtai' class="form-control"><option value="借出">借出</option><option value="在馆">在馆</option></select></td></tr>
		<tr 2><td width="200" align="center">借阅次数：</td><td><input name='jieyuecishu' type='text' id='jieyuecishu' value='' onblur='' class="form-control" /></td></tr>
		<tr 3><td width="200" align="center">摘要：</td><td><textarea name='zhaiyao' cols='50' rows='5' id='zhaiyao' onblur='' class="form-control" style="width:600px; height:80px;" ></textarea></td></tr>
		
		
						<tr align='center'   height="22">
						    <td width="25%" height="45"  align="right">&nbsp;						    </td>
						    <td width="75%"  align="left">
						       <input type="submit" name="querenzhuce" id="querenzhuce" value="提交" onClick="return checkform();" class="btn btn-info btn-small" style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 0 10px;"/>
						       <input type="reset" value="重置" class="btn btn-info btn-small" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;"/>&nbsp;
						    </td>
						</tr>
						<script language="javascript">document.form1.tushubianhao.value='${tushuxinxi.tushubianhao}';</script>
	<script language="javascript">document.form1.ISBNma.value='${tushuxinxi.ISBNma}';</script>
	<script language="javascript">document.form1.mingcheng.value='${tushuxinxi.mingcheng}';</script>
	<script language="javascript">document.form1.leibie.value='${tushuxinxi.leibie}';</script>
	<script language="javascript">document.form1.tupian.value='${tushuxinxi.tupian}';</script>
	<script language="javascript">document.form1.jiage.value='${tushuxinxi.jiage}';</script>
	<script language="javascript">document.form1.chubanshe.value='${tushuxinxi.chubanshe}';</script>
	<script language="javascript">document.form1.chubandi.value='${tushuxinxi.chubandi}';</script>
	<script language="javascript">document.form1.chubanriqi.value='${tushuxinxi.chubanriqi}';</script>
	<script language="javascript">document.form1.suoshuhao.value='${tushuxinxi.suoshuhao}';</script>
	<script language="javascript">document.form1.zhuangtai.value='${tushuxinxi.zhuangtai}';</script>
	<script language="javascript">document.form1.jieyuecishu.value='${tushuxinxi.jieyuecishu}';</script>
	<script language="javascript">document.form1.zhaiyao.value='${tushuxinxi.zhaiyao}';</script>
	
					 </table>
			</form>
   </body>
</html>






<script language=javascript >  
 
 function checkform(){  
 
	var tushubianhaoobj = document.getElementById("tushubianhao"); if(tushubianhaoobj.value==""){document.getElementById("clabeltushubianhao").innerHTML="&nbsp;&nbsp;<font color=red>请输入图书编号</font>";return false;}else{document.getElementById("clabeltushubianhao").innerHTML="  "; } 
	var mingchengobj = document.getElementById("mingcheng"); if(mingchengobj.value==""){document.getElementById("clabelmingcheng").innerHTML="&nbsp;&nbsp;<font color=red>请输入名称</font>";return false;}else{document.getElementById("clabelmingcheng").innerHTML="  "; } 
	


return true;   
}   
popheight=450;
</script>  
