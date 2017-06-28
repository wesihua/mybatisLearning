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
		<form action="<%=path %>/manager/user/saveUser" method="post">
		<input type="hidden" name="id" id="id" value="${bean.id}"/>
		<table border="0">
			<c:if test="${prop['user_name_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['user_name']}：</td>
					<td><input type="text" name="name" value="${bean.name}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['user_password_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['user_password']}：</td>
					<td><input type="text" name="password" value="${bean.password}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['user_realname_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['user_realname']}：</td>
					<td><input type="text" name="realname" value="${bean.realname}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['user_age_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['user_age']}：</td>
					<td><input type="text" name="age" value="${bean.age}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['user_roleId_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['user_roleId']}：</td>
					<td><select id="role" name="roleId"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['user_description_show'] == 0}">
				<tr>
					<td align="right">${prop['user_description']}：</td>
					<td><textarea name="description">${bean.description}</textarea><br/></td>
				</tr>
			</c:if>
		</table>
		<input class="form_btn" type="submit" value="保存"/>
		&nbsp;&nbsp;&nbsp;
		<input class="form_btn" type="reset" value="清空"/>
		<!-- <button class="form_btn" onclick="reset()">清空</button> -->
		</form>	
	</div>
</center>
</body>
</html>