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
	init();
};

//页面初始化方法
function init() {
	var rightBackGroundColor = "${prop['rightBackGroundColor']}";
	var theadBackgroundColor = "${prop['theadBackgroundColor']}";
	var pagebuttonBackground = "${prop['pagebuttonBackground']}";
	var borderColor = "${prop['borderColor']}";
	
	$("body").css("background", rightBackGroundColor);
	$("thead").css("background", theadBackgroundColor);
	$(".button_normal").css("background", pagebuttonBackground);
	$("input[type='submit']").css("background", pagebuttonBackground).css("color","white");
	$("td button").css("background", pagebuttonBackground).css("border","0");
	$(".query").css("border-color", borderColor);
	$(".result").css("border-color", borderColor);
}
</script>
</head>
<body>
	<div class="query">
		<form action="<%=path %>/manager/user/list" method="post">
			&nbsp;&nbsp;
			<c:if test="${prop['user_id_show'] == '0'}">
				${prop['user_id']}：<input type="text" name="id"/>&nbsp;&nbsp;
			</c:if>
			${prop['user_name']}：<input type="text" name="name"/>
			<input type="submit" value="查询"/>
		</form>	
	</div>
	<div class="result">
		<table>
			<thead>
				<tr>
					<td>序号</td>
					<c:if test="${prop['user_id_show'] == '0'}">
						<td>${prop['user_id']}</td>
					</c:if>
					<c:if test="${prop['user_name_show'] == '0'}">
						<td>${prop['user_name']}</td>
					</c:if>
					<c:if test="${prop['user_password_show'] == '0'}">
						<td>${prop['user_password']}</td>
					</c:if>
					<c:if test="${prop['user_realname_show'] == '0'}">
						<td>${prop['user_realname']}</td>
					</c:if>
					<c:if test="${prop['user_age_show'] == '0'}">
						<td>${prop['user_age']}</td>
					</c:if>
					<c:if test="${prop['user_roleId_show'] == '0'}">
						<td>${prop['user_roleId']}</td>
					</c:if>
					<c:if test="${prop['user_description_show'] == '0'}">
						<td>${prop['user_description']}</td>
					</c:if>
					<td>创建时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${list}" varStatus="index">
					<tr>
						<td>${index.index+1}</td>
						<c:if test="${prop['user_id_show'] == '0'}">
							<td>${bean.id}</td>
						</c:if>
						<c:if test="${prop['user_name_show'] == '0'}">
							<td>${bean.name}</td>
						</c:if>
						<c:if test="${prop['user_password_show'] == '0'}">
							<td>${bean.password}</td>
						</c:if>
						<c:if test="${prop['user_realname_show'] == '0'}">
							<td>${bean.realname}</td>
						</c:if>
						<c:if test="${prop['user_age_show'] == '0'}">
							<td>${bean.age}</td>
						</c:if>
						<c:if test="${prop['user_roleId_show'] == '0'}">
							<td>${bean.role.name}</td>
						</c:if>
						<c:if test="${prop['user_description_show'] == '0'}">
							<td>${bean.description}</td>
						</c:if>
						<td>${bean.createdatemc}</td>
						<td>
							<button onclick="editObject('<%=path%>/manager/user/editUser','${bean.id}')">修改</button>&nbsp;&nbsp;&nbsp;
							<button onclick="deleteObject('<%=path%>/manager/user/deleteUser','${bean.id}')">删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page">
			<span class="page_text">当前${list[0].pageNow}/${list[0].pageCount}页 共${list[0].rowCount}条</span>
			<button class="button_normal" onclick='queryPage(1)'>首页</button>
			<c:if test="${list[0].pageNow == '1'}">
				<button class='button_gray'>上一页</button>
			</c:if>
			<c:if test="${list[0].pageNow > 1}">
				<button class='button_normal' onclick="queryPage('${list[0].pageNow - 1}')">上一页</button>
			</c:if>
			<c:if test="${list[0].pageNow == list[0].pageCount}">
				<button class='button_gray'>下一页</button>
			</c:if>
			<c:if test="${list[0].pageNow < list[0].pageCount}">
				<button class='button_normal' onclick="queryPage('${list[0].pageNow + 1}')">下一页</button>
			</c:if>
			<button class="button_normal" onclick="queryPage(${list[0].pageCount})">尾页</button>
			&nbsp;
		</div>
	</div>
</body>
</html>