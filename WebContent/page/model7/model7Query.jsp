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
	$("td button").css("background", pagebuttonBackground).css("border","0");
	$(".query").css("border-color", borderColor);
	$(".result").css("border-color", borderColor);
	$("input[type='submit']").css("background", pagebuttonBackground).css("color","white");
}
</script>
</head>
<body>
	<div class="query">
		<form action="<%=path %>/manager/model7/list" method="post">
			&nbsp;&nbsp;
			<c:if test="${prop['model7_id_show'] == '0'}">
				${prop['model7_id']}：<input type="text" name="id"/>&nbsp;&nbsp;
			</c:if>
			${prop['model7_name']}：<input type="text" name="name"/>
			<input type="submit" value="查询"/>
		</form>	
	</div>
	<div class="result">
		<table>
			<thead>
				<tr>
					<td>序号</td>
					<c:if test="${prop['model7_id_show'] == '0'}">
						<td>${prop['model7_id']}</td>
					</c:if>
					<c:if test="${prop['model7_name_show'] == '0'}">
						<td>${prop['model7_name']}</td>
					</c:if>
					<c:if test="${prop['model7_name2_show'] == '0'}">
						<td>${prop['model7_name2']}</td>
					</c:if>
					<c:if test="${prop['model7_name3_show'] == '0'}">
						<td>${prop['model7_name3']}</td>
					</c:if>
					<c:if test="${prop['model7_name4_show'] == '0'}">
						<td>${prop['model7_name4']}</td>
					</c:if>
					<c:if test="${prop['model7_name5_show'] == '0'}">
						<td>${prop['model7_name5']}</td>
					</c:if>
					<c:if test="${prop['model7_name6_show'] == '0'}">
						<td>${prop['model7_name6']}</td>
					</c:if>
					<c:if test="${prop['model7_name7_show'] == '0'}">
						<td>${prop['model7_name7']}</td>
					</c:if>
					<c:if test="${prop['model7_name8_show'] == '0'}">
						<td>${prop['model7_name8']}</td>
					</c:if>
					<c:if test="${prop['model7_name9_show'] == '0'}">
						<td>${prop['model7_name9']}</td>
					</c:if>
					<c:if test="${prop['model7_name10_show'] == '0'}">
						<td>${prop['model7_name10']}</td>
					</c:if>
					
					<c:if test="${prop['model7_model2Id_show'] == '0'}">
						<td>${prop['model7_model2Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model3Id_show'] == '0'}">
						<td>${prop['model7_model3Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model4Id_show'] == '0'}">
						<td>${prop['model7_model4Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model5Id_show'] == '0'}">
						<td>${prop['model7_model5Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model6Id_show'] == '0'}">
						<td>${prop['model7_model6Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model1Id_show'] == '0'}">
						<td>${prop['model7_model1Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model8Id_show'] == '0'}">
						<td>${prop['model7_model8Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model9Id_show'] == '0'}">
						<td>${prop['model7_model9Id']}</td>
					</c:if>
					<c:if test="${prop['model7_model10Id_show'] == '0'}">
						<td>${prop['model7_model10Id']}</td>
					</c:if>
					<!-- <td>操作</td> -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="bean" items="${list}" varStatus="index">
					<tr>
						<td>${index.index+1}</td>
						<c:if test="${prop['model7_id_show'] == '0'}">
							<td>${bean.id}</td>
						</c:if>
						<c:if test="${prop['model7_name_show'] == '0'}">
							<td>${bean.name}</td>
						</c:if>
						<c:if test="${prop['model7_name2_show'] == '0'}">
							<td>${bean.name2}</td>
						</c:if>
						<c:if test="${prop['model7_name3_show'] == '0'}">
							<td>${bean.name3}</td>
						</c:if>
						<c:if test="${prop['model7_name4_show'] == '0'}">
							<td>${bean.name4}</td>
						</c:if>
						<c:if test="${prop['model7_name5_show'] == '0'}">
							<td>${bean.name5}</td>
						</c:if>
						<c:if test="${prop['model7_name6_show'] == '0'}">
							<td>${bean.name6}</td>
						</c:if>
						<c:if test="${prop['model7_name7_show'] == '0'}">
							<td>${bean.name7}</td>
						</c:if>
						<c:if test="${prop['model7_name8_show'] == '0'}">
							<td>${bean.name8}</td>
						</c:if>
						<c:if test="${prop['model7_name9_show'] == '0'}">
							<td><fmt:formatDate value="${bean.name9}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</c:if>
						<c:if test="${prop['model7_name10_show'] == '0'}">
							<td>${bean.name10}</td>
						</c:if>
						
						<c:if test="${prop['model7_model2Id_show'] == '0'}">
							<td>${bean.model2.name}</td>
						</c:if>
						<c:if test="${prop['model7_model3Id_show'] == '0'}">
							<td>${bean.model3.name}</td>
						</c:if>
						<c:if test="${prop['model7_model4Id_show'] == '0'}">
							<td>${bean.model4.name}</td>
						</c:if>
						<c:if test="${prop['model7_model5Id_show'] == '0'}">
							<td>${bean.model5.name}</td>
						</c:if>
						<c:if test="${prop['model7_model6Id_show'] == '0'}">
							<td>${bean.model6.name}</td>
						</c:if>
						<c:if test="${prop['model7_model1Id_show'] == '0'}">
							<td>${bean.model1.name}</td>
						</c:if>
						<c:if test="${prop['model7_model8Id_show'] == '0'}">
							<td>${bean.model8.name}</td>
						</c:if>
						<c:if test="${prop['model7_model9Id_show'] == '0'}">
							<td>${bean.model9.name}</td>
						</c:if>
						<c:if test="${prop['model7_model10Id_show'] == '0'}">
							<td>${bean.model10.name}</td>
						</c:if>
						<%-- <td>
							<button onclick="editObject('<%=path%>/manager/model7/editModel','${bean.id}')">修改</button>&nbsp;&nbsp;&nbsp;
							<button onclick="deleteObject('<%=path%>/manager/model7/deleteModel','${bean.id}')">删除</button>
						</td> --%>
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