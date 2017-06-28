<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎光临</title>

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
	$("td button").css("background", pagebuttonBackground).css("border","0");
	$(".query").css("border-color", borderColor);
	$(".result").css("border-color", borderColor);
	$("input[type='submit']").css("background", pagebuttonBackground).css("color","white");
}
</script>
</head>
<body>
	<div class="query">
		<form action="<%=path %>/manager/menu/list" method="post">
			&nbsp;&nbsp;
			菜单名称：<input type="text" name="name"/>
			<input type="submit" value="查询"/>
		</form>	
	</div>
	<div class="result">
		<table>
			<thead>
				<tr>
					<td>序号</td>
					<td>菜单名称</td>
					<td>上级菜单</td>
					<td>链接</td>
					<td>菜单等级</td>
					<td>描述</td>
					<td>创建时间</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${list}" varStatus="index">
					<tr>
						<td>${index.index+1}</td>
						<td>${bean.name}</td>
						<td>${bean.parentmenuname}</td>
						<td>${bean.url}</td>
						<td>${bean.levelName}</td>
						<td>${bean.description}</td>
						<td>${bean.createdatemc}</td>
						<td>
							<button onclick="editObject('<%=path%>/manager/menu/editMenu','${bean.id}')">修改</button>&nbsp;&nbsp;&nbsp;
							<button onclick="deleteObject('<%=path%>/manager/menu/deleteMenu','${bean.id}')">删除</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="page">
			<span class="page_text">当前${bean.pageNow}/${bean.pageCount}页 共${bean.rowCount}条</span>
			<button class="button_normal" onclick='queryPage(1)'>首页</button>
			<c:if test="${bean.pageNow == '1'}">
				<button class='button_gray'>上一页</button>
			</c:if>
			<c:if test="${bean.pageNow > 1}">
				<button class='button_normal' onclick="queryPage('${bean.pageNow - 1}')">上一页</button>
			</c:if>
			<c:if test="${bean.pageNow == bean.pageCount}">
				<button class='button_gray'>下一页</button>
			</c:if>
			<c:if test="${bean.pageNow < bean.pageCount}">
				<button class='button_normal' onclick="queryPage('${bean.pageNow + 1}')">下一页</button>
			</c:if>
			<button class="button_normal" onclick="queryPage(${bean.pageCount})">尾页</button>
			&nbsp;
		</div>
	</div>
</body>
</html>