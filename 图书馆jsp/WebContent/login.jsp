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
</head>
<script type="text/javascript">
	function hsgchecklogin() {
	if(document.f11.username.value=="" || document.f11.pwd.value=="" || document.f11.pagerandom.value=="")
	{
		alert("请输入完整");
		return false;
	}
	if (document.f11.pagerandom.value != document.f11.yzm.value) {
			alert("验证码错误！");
			document.f11.pagerandom.focus();
			return false;
	}
	
	}
	</script> 
<style>
	html,body{
	font-family: 'Roboto', sans-serif;
    font-size: 100%;
    overflow-x: hidden;
    background: url(./images/bg.jpg) no-repeat 0px 0px;
	background-size:cover;
    background-color: #ffffff;
}
	.code{
	width: 100%;
    padding: 10px 0px 10px 10px;
    border: 2px solid #eee9e9;
    outline: none;
    font-size: 14px;
    color: rgb(0, 0, 0);
    margin: 14px 0px;
	background: none;
	}
</style>
<body>
<div class="log-w3">
<div class="w3layouts-main">
	<h2>图书馆借阅管理系统</h2>
	
		 <form name="f11"  method="post" action="hsgloginyanzheng.jsp" >
			<input type="text" class="ggg"     name="username"  id="username"      placeholder="账号" required="">
			<input type="password" class="ggg" name="pwd" id="pwd" placeholder="密码" required="">
			<select name="cx" id="cx" class="hsgselect">
            <option value="管理员">管理员</option>
<option value="读者">读者</option>

            </select>
            
			<input type="text" class="code"    name="pagerandom" id="pagerandom"       placeholder="验证码" required="" style="width:120px;"> 
			<%
								String yzm=code.getCheckCode();
								%>  <input type="hidden" name="yzm" value="<%=yzm %>" >
			 <%=yzm %> 
				<div class="clearfix"></div>
				<input type="submit" value="登   录"  onClick="return hsgchecklogin();" style="float:left">
		        <input name="submit" type="button"  onClick="location.href='xueshengxinxiadd.jsp';" value="注  册">
		 </form>
</div>
</div>
</body>

</html>
