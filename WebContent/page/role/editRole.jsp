<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎光临</title>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/main.css" /> --%>
<script>
window.onload = function() {
	//初始化所有select
	FormUtil.initAllSelect();
	init();
};

//页面初始化方法
function init() {
	var theadBackgroundColor = "${prop['theadBackgroundColor']}";
	$(".area").css("border-color", theadBackgroundColor);
	$(".form_btn").css("background", theadBackgroundColor).css("color","white").css("border-color",theadBackgroundColor);
}
</script>
</head>
<body>
<center>
	<div class="area">
		<form action="<%=path %>/manager/role/saveRole" method="post">
		<input type="hidden" name="id" id="id" value="${bean.id}"/>
		<table border="0">
			<tr>
				<td width="40%" align="right">${prop['role_name']}：</td>
				<td><input type="text" name="name" value="${bean.name}"/></td>
			</tr>
			<tr>
				<td align="right">${prop['role_description']}：</td>
				<td><textarea name="description">${bean.description}</textarea><br/></td>
			</tr>
		</table>
		<input class="form_btn" type="submit" value="保存"/>
		&nbsp;&nbsp;&nbsp;
		<input class="form_btn" type="reset" value="清空"/>
		<!-- <button class="form_btn" onclick="reset()">清空</button> -->
		</form>	
	</div>
	<%-- <fieldset class="area">
		<legend>新增角色</legend>
		<form action="<%=path %>/manager/role/saveRole" method="post">
		<table border="0">
			<tr>
				<td width="40%" align="right">${prop['role_name']}：</td>
				<td><input type="text" name="name"/></td>
			</tr>
			<tr>
				<td align="right">${prop['role_description']}：</td>
				<td><textarea name="description"></textarea><br/></td>
			</tr>
		</table>
		<input class="form_btn" type="submit" value="保存"/>
		&nbsp;&nbsp;&nbsp;
		<input class="form_btn" type="reset" value="清空"/>
		</form>	
	</fieldset> --%>
	
</center>
</body>
</html>