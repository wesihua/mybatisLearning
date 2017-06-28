<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="./header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel="stylesheet" href="<%=basePath%>/css/login.css"/>
<script>
var msg = "${msg}";
if(msg){
	alert(msg);
	reset();
}
window.onload = function(){
	init();
};

 //页面初始化方法
function init(){
	var loginBackground = "${prop['loginBackground']}";
	var loginContentBackground = "${prop['loginContentBackground']}";
	var systemNameColorOfLogin = "${prop['systemNameColorOfLogin']}";
	$("body").css("background",loginBackground);
	$(".login_content").css("background",loginContentBackground);
	$(".systemname").css("color",systemNameColorOfLogin);
}
function reset(){
	$("#username").val("");
	$("#password").val("");
}
function login(){
	$("#loginForm").submit();
}
</script>
</head>
<body>
<center>
	<div class="systemname">${prop['systemName']}</div>
	<div class="login_content">
		<form method="post" id="loginForm" action="<%=path%>/manager/login/main">
			<div class="user">
				<div class="title">用户名：<input type="text" name="username" id="username"/></div>
			</div>
			<div class="user">
				<div class="title">密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password" id="password"/></div>
			</div>
			<div class="button">
				<button onclick="login">登录</button>
				&nbsp;&nbsp;&nbsp;
				<button onclick="reset()">重置</button>
			</div>
		</form>
	</div>
</center>
</body>
</html>