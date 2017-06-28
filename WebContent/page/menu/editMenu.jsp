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
	FormUtil.initTopMenuSelect('parentId');
	init();
	var id = '${bean.id}';
	if(id){
		$("#level").val('${bean.level}');
		setTimeout("$('#parentId').val('${bean.parentId}');", 50);
	}
	var type = "${type}";
	if(type){
		$("#url").attr("disabled","disabled");
		$("#parentId").attr("disabled","disabled");
	}
};


//页面初始化方法
function init() {
	var theadBackgroundColor = "${prop['theadBackgroundColor']}";
	$(".area").css("border-color", theadBackgroundColor);
	$(".form_btn").css("background", theadBackgroundColor).css("color","white").css("border-color",theadBackgroundColor);
}
function changeState(val){
	if(val){
		if(val == '1'){
			$("#url").attr("disabled","disabled");
			$("#parentId").attr("disabled","disabled");
		}
		else{
			$("#url").attr("disabled",false);
			$("#parentId").attr("disabled",false);
		}
	}
}
</script>
</head>
<body>
<center>
	<div class="area">
		<form action="<%=path %>/manager/menu/saveMenu" method="post">
		<input type="hidden" name="id" id="id" value="${bean.id}"/>
		<table border="0">
			<tr>
				<td width="40%" align="right">菜单名称：</td>
				<td><input type="text" name="name" value="${bean.name}"/></td>
			</tr>
			<tr>
				<td width="40%" align="right">菜单级别：</td>
				<td>
					<select id="level" name="level" onchange="changeState(this.value)">
						<option value=''>---请选择---</option>
						<option value='1'>一级菜单</option>
						<option value='2'>二级菜单</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="40%" align="right">链接：</td>
				<td><input type="text" name="url" id="url" value="${bean.url}"/></td>
			</tr>
			<tr>
				<td width="40%" align="right">上级菜单：</td>
				<td><select id="parentId" name="parentId"></select></td>
			</tr>
			<tr>
				<td align="right">描述：</td>
				<td><textarea name="description">${bean.description}</textarea><br/></td>
			</tr>
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