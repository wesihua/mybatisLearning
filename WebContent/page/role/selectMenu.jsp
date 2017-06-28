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
	//init();
};

//页面初始化方法
function init() {

}
var roleId = "${roleId}";
function saveMenuRole(){
	var values = $("input[name='check']:checked");
	var content = "";
	if(values.length > 0){
		for(var i=0; i<values.length; i++){
			content+=$(values[i]).val();
			if(i<values.length-1){
				content+=",";
			}
		}
	}
	if(!content){
		alert('请选择菜单');
		return;
	}
	$.ajax({
		url:'<%=path%>/manager/role/saveRoleMenu',
		data:{ids:content,roleId:roleId},
		dataType:"json",
		success:function(data){
			if(data.flag == '0'){
				alert("分配菜单成功！");
			}
			else{
				alert("分配菜单失败！");
			}
			closeWin();
		}
	});
}
</script>
</head>
<body>
<center>
<div class="menus">
	<table width="70%">
	<tr>
		<c:forEach var="menus" items="${map}" varStatus="index">
			<c:if test="${index.index % 2 == 0 && index.index != 0}">
				</tr><tr>
			</c:if>
			<td>
				<h4>${index.index+1}.${menus.key}</h4>
				<c:if test="${empty menus.value}">
					" "<br/>" "
				</c:if>
				<c:if test="${not empty menus.value}">
					<c:forEach var="subMenus" items="${menus.value}">
						<div class="sub_menus">
						<input type="checkbox" name="check" value="${subMenus.id}" <c:if test="${subMenus.roleId == roleId}">checked="checked"</c:if>/>
						 ${subMenus.name}
						</div>
					</c:forEach>
				</c:if>
				
			</td>	
		</c:forEach>
	</table>
</div>
<div style="width:100%;height:50px;"></div>
<div class="dialog_button">
	<button onclick="saveMenuRole()">确定</button>
	<button onclick="closeWin()">取消</button>
</div>
</center>
</body>
</html>