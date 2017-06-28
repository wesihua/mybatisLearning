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
		<form action="<%=path %>/manager/model1/saveModel" method="post">
		<input type="hidden" name="id" id="id" value="${bean.id}"/>
		<table border="0">
			<c:if test="${prop['model1_name_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name']}：</td>
					<td><input type="text" name="name" value="${bean.name}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name2_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name2']}：</td>
					<td><input type="text" name="name2" value="${bean.name2}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name3_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name3']}：</td>
					<td><input type="text" name="name3" value="${bean.name3}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name4_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name4']}：</td>
					<td><input type="text" name="name4" value="${bean.name4}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name5_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name5']}：</td>
					<td><input type="text" name="name5" value="${bean.name5}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name6_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name6']}：</td>
					<td><input type="text" name="name6" value="${bean.name6}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name7_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name7']}：</td>
					<td><input type="text" name="name7" value="${bean.name7}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name8_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name8']}：</td>
					<td><input type="text" name="name8" value="${bean.name8}"/></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_name10_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_name10']}：</td>
					<td>
						<textarea name="name10">${bean.name10}</textarea>
					</td>
				</tr>
			</c:if>
			
			<c:if test="${prop['model1_model2Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model2Id']}：</td>
					<td><select id="model2" name="model2Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model3Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model3Id']}：</td>
					<td><select id="model3" name="model3Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model4Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model4Id']}：</td>
					<td><select id="model4" name="model4Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model5Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model5Id']}：</td>
					<td><select id="model5" name="model5Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model6Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model6Id']}：</td>
					<td><select id="model6" name="model6Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model7Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model7Id']}：</td>
					<td><select id="model7" name="model7Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model8Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model8Id']}：</td>
					<td><select id="model8" name="model8Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model9Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model9Id']}：</td>
					<td><select id="model9" name="model9Id"></select></td>
				</tr>
			</c:if>
			<c:if test="${prop['model1_model10Id_show'] == 0}">
				<tr>
					<td width="40%" align="right">${prop['model1_model10Id']}：</td>
					<td><select id="model10" name="model10Id"></select></td>
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