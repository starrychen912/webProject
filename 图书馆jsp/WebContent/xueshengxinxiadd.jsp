<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="code" scope="page" class="com.util.CheckCode" />
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
<title>图书馆借阅管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="images/css/bootstrap.min.css" >
<link href="images/css/style.css" rel='stylesheet' type='text/css' />
<link href="images/css/style-responsive.css" rel="stylesheet"/>
<link rel="stylesheet" href="images/css/font.css" type="text/css"/>
<link href="images/css/font-awesome.css" rel="stylesheet"> 
<script src="images/js/jquery2.0.3.min.js"></script>
<link rel="stylesheet" href="images/hsgcommon/common.css" type="text/css">
	<link rel="stylesheet" href="images/hsgcommon/div.css" type="text/css">    
</head>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js" charset="gb2312"></script>
<script type="text/javascript" src="js/popup.js"></script>
<style>
	html,body{
	font-family: 'Roboto', sans-serif;
    font-size: 100%;
    overflow-x: hidden;
    background: url(./images/bg.jpg) no-repeat 0px 0px;
	background-size:cover;
    background-color: #ffffff;
}
.w3layouts-main{
	width: 50%;
}
</style>
<body>
<div class="log-w3">
<div class="w3layouts-main">
	<h2>读者注册</h2>
	
		<form action="addXueshengxinxi.do" name="form1" method="post">
				      <table width="100%" border="1" align="center" cellpadding="3" cellspacing="1"  style="border-collapse:collapse" bgcolor="#F2FDFF">
						
						<tr ><td width="200" align="center">学号：</td><td><input name='xuehao' type='text' id='xuehao' value='' onblur='hsgcheck()' class="form-control" />&nbsp;*<label id='clabelxuehao' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">密码：</td><td><input name='mima' type='text' id='mima' value='' onblur='checkform()' class="form-control" />&nbsp;*<label id='clabelmima' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">姓名：</td><td><input name='xingming' type='text' id='xingming' value='' onblur='checkform()' class="form-control" />&nbsp;*<label id='clabelxingming' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center"><tr><td align="center">性别：</td><td><select name='xingbie' id='xingbie' class="form-control"><option value="男">男</option><option value="女">女</option></select></td></tr>
		<tr ><td width="200" align="center">身份证：</td><td><input name='shenfenzheng' type='text' id='shenfenzheng' value='' onblur='checkform()' size='50' class="form-control" style="width:200px;" />&nbsp;*<label id='clabelshenfenzheng' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">电话：</td><td><input name='dianhua' type='text' id='dianhua' value='' onblur='checkform()' class="form-control" />&nbsp;*<label id='clabeldianhua' style='margin-top:16px;' /></td></tr>
		<tr ><td width="200" align="center">院系：</td><td><input name='yuanxi' type='text' id='yuanxi' value='' onblur='' class="form-control" /></td></tr>
		<tr ><td width="200" align="center">班级：</td><td><input name='banji' type='text' id='banji' value='' onblur='' class="form-control" /></td></tr>
		<tr ><td width="200" align="center">籍贯：</td><td><input name='jiguan' type='text' id='jiguan' value='' onblur='' class="form-control" /></td></tr>
		<tr 0><td width="200" align="center">照片：</td><td><table><tr><td><input name='zhaopian' type='text' id='zhaopian' size='50' value='' onblur=''  class="form-control" style="width:200px; float:left"/></td><td><input type='button' value='上传' onClick="up('zhaopian')" style="background-color:#7e817e ;border-color: #E4E7ED;height: 50px;"/></td></tr></table></td></tr>
		<tr 1><td width="200" align="center">余额：</td><td><input name='yue' type='text' id='yue' value='0' onblur='' class="form-control" readonly="readonly" /></td></tr>
		<tr 2><td width="200" align="center">备注：</td><td><textarea name='beizhu' cols='50' rows='5' id='beizhu' onblur='' class="form-control" style="width:300px; height:80px;" ></textarea></td></tr>
		
		
						<tr align='center'   height="22">
						    <td height="45"  align="right">&nbsp;						    </td>
						    <td width="75%"  align="left">
						       <input type="submit" name="querenzhuce" id="querenzhuce" value="提交" onClick="return checkform();" class="btn btn-info btn-small"  style="background-color:#3c3f3c ;border-color: #E4E7ED;margin: 10px;float: left;"/>
						       <input type="button" value="返回" name="Submit2" class="btn btn-info btn-small" onClick="location.href='login.jsp';" style="background-color:#F2F6FC ;border-color: #E4E7ED;color: #3c3f3c;margin:10px 0;height:50px;"/>&nbsp;
						    </td>
						</tr>
					 </table>
			</form>
</div>
</div>
</body>
</html>


<script language=javascript >  
function hsgcheck() {
		var xuehao = $("#xuehao").val();
		if(xuehao==""||(xuehao.length<3||xuehao.length>12)){
			 $("#clabelxuehao").html("<font color=red>学号不能为空且长度在3～12位之间！</font>");
			 $("input[id=xuehao]").focus();
			 $("#querenzhuce").attr("disabled","disabled");
			 return false;
		} 
		else
		{
			$("#clabelxuehao").html("");
			$.ajax({
				url : "quchongXueshengxinxi.do",
				type : "post",
				data : "xuehao=" + xuehao,
				dataType : "json",
				success:function(result){
				if(result.info=="ng"){
					$("#clabelxuehao").html("<font color=red>学号已存在，请更换！</font>");
					$("input[id=xuehao]").focus();
					$("#querenzhuce").attr("disabled","disabled");
					return false;
				}
				else
				{
					$("#querenzhuce").removeAttr("disabled");
				}
				},
				error:function(){
					$("#clabelxuehao").html("系统异常，请检查错误！");
					$("input[id=xuehao]").focus();
					$("#querenzhuce").attr("disabled","disabled");
					return false;
				}
			});
		}
	}
</script>


<script language=javascript >  
 
 function checkform(){  
 
	var xuehaoobj = document.getElementById("xuehao"); if(xuehaoobj.value==""){document.getElementById("clabelxuehao").innerHTML="&nbsp;&nbsp;<font color=red>请输入学号</font>";return false;}else{document.getElementById("clabelxuehao").innerHTML="  "; } 
	var mimaobj = document.getElementById("mima"); if(mimaobj.value==""){document.getElementById("clabelmima").innerHTML="&nbsp;&nbsp;<font color=red>请输入密码</font>";return false;}else{document.getElementById("clabelmima").innerHTML="  "; } 
	var xingmingobj = document.getElementById("xingming"); if(xingmingobj.value==""){document.getElementById("clabelxingming").innerHTML="&nbsp;&nbsp;<font color=red>请输入姓名</font>";return false;}else{document.getElementById("clabelxingming").innerHTML="  "; } 
	var shenfenzhengobj = document.getElementById("shenfenzheng"); if(shenfenzhengobj.value==""){document.getElementById("clabelshenfenzheng").innerHTML="&nbsp;&nbsp;<font color=red>请输入身份证</font>";return false;}else{document.getElementById("clabelshenfenzheng").innerHTML="  "; } 
	var shenfenzhengobj = document.getElementById("shenfenzheng"); if(shenfenzhengobj.value!=""){ if(/^\d{15}$|^\d{18}$|^\d{17}[xX]$/.test(shenfenzhengobj.value)){document.getElementById("clabelshenfenzheng").innerHTML=""; }else{document.getElementById("clabelshenfenzheng").innerHTML="&nbsp;&nbsp;<font color=red>必需身份证格式</font>"; return false;}}  
    var dianhuaobj = document.getElementById("dianhua"); if(dianhuaobj.value==""){document.getElementById("clabeldianhua").innerHTML="&nbsp;&nbsp;<font color=red>请输入电话</font>";return false;}else{document.getElementById("clabeldianhua").innerHTML="  "; } 
	var dianhuaobj = document.getElementById("dianhua"); if(dianhuaobj.value!=""){ if(/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(dianhuaobj.value) || /^1[3|4|5|6|7|8|9][0-9]\d{8,8}$/.test(dianhuaobj.value)){document.getElementById("clabeldianhua").innerHTML=""; }else{document.getElementById("clabeldianhua").innerHTML="&nbsp;&nbsp;<font color=red>必需电话格式[7或8位电话，或11位手机]</font>"; return false;}}  
    


return true;   
}   
popheight=450;
</script>  
<script src="images/hsgcommon/jq.js"></script>
<script src="images/hsgcommon/common.js"></script>
<script src="images/hsgcommon/bootbox.js"></script>