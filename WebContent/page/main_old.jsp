<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="./header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎光临</title>
<link rel="stylesheet" href="<%=basePath%>/css/main.css" />
<script>
	window.onload = function() {
		init();
	};
	$(function(){
		$( "#accordion" ).accordion({
	      heightStyle: "content"
	    });
	});
	//页面初始化方法
	function init() {
		var topBackGroundColor = "${prop['topBackGroundColor']}";
		var topSystemNameSize = "${prop['topSystemNameSize']}";
		var topSystemNameColor = "${prop['topSystemNameColor']}";
		var topSystemNameShadowColor = "${prop['topSystemNameShadowColor']}";
		var topuserInfoColor = "${prop['topuserInfoColor']}";
		var bottomBackgroundColor = "${prop['bottomBackgroundColor']}";
		var bottomColor = "${prop['bottomColor']}";
		var leftMenuColor = "${prop['leftMenuColor']}";
		//var x = "${prop['systemNameColorOfLogin']}";
		//var x = "${prop['systemNameColorOfLogin']}";
		$(".top").css("background", topBackGroundColor);
		$(".system_title").css("font-size", topSystemNameSize);
		$(".system_title").css("color", topSystemNameColor);
		$(".system_title").css("text-shadow", "2px 1px 1px " + topSystemNameShadowColor);
		$(".user_info").css("color", topuserInfoColor);
		$(".user_info a").css("color", topuserInfoColor);
		$(".bottom").css("background", bottomBackgroundColor);
		$(".bottom").css("color", bottomColor);
		$("a[name='m']").css("color", leftMenuColor);
	}
</script>
</head>
<body>
	<div class="main">
		<div class="top">
			<div class="system_title">${prop['systemName']}</div>
			<div class="user_info">
				欢迎：xxxxx &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="#">退出</a>
			</div>
		</div>
		<div class="left">
			<%-- <a href="<%=path %>/manager/el" target="content">sdfsdfsd</a> --%>
			<div id="accordion">
				<c:forEach var="map" items="${pages}" varStatus="index">
					<c:if test="${map.key != null}">
						<!-- 模块名称 -->
						<h3>${map.key}</h3>
						<c:if test="${map.value != null}">
							<div>
							<!-- 遍历该模块下的字菜单 -->
	      					<c:forEach var="menu" items="${map.value}" varStatus="menuIndex">
	      						<p><a href="<%=path %>/${menu.url}" target="content" name="m">${menu.name}</a></p>
	      					</c:forEach>
	      					</div>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div class="right">
			<iframe name="content" id="myframe"
				src="<%=basePath%>/page/welcome.jsp" frameborder="1"></iframe>
		</div>
		<div class="bottom">${prop['copyrightName']}</div>
	</div>
</body>
</html>