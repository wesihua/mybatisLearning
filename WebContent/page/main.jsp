<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="./header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>欢迎光临</title>
<link rel="stylesheet" href="<%=basePath%>/css/traditional.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/main.css"/>
<script src="<%=basePath%>/js/layout.js"></script>
<script src="<%=basePath%>/js/jquery/jquery.tree.js"></script>
<script>
	$(function(){
		$('#files').tree({
			expanded: 'li:first'
		});
		init();
	});
	/* window.onload = function() {
		init();
	}; */
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
		//var theadBackgroundColor = "${prop['theadBackgroundColor']}";
		var titleBackGround = "${prop['titleBackGround']}";
		//var titleSize = "${prop['titleSize']}";
		var titleHeight = "${prop['titleHeight']}";
		var titleradius = "${prop['titleradius']}";
		var leftBackGround = "${prop['leftBackGround']}";
		var hoverColor = "${prop['hoverColor']}";
		
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
		$(".ui-accordion-header").css("background",titleBackGround).css("color","white").css("height",titleHeight).css("border-radius",titleradius);
		
		$(".left").css("background", leftBackGround);
		$(".tree li a").css("color", titleBackGround);
		$(".tree li a.tree-parent").css("color", titleBackGround);
	}
</script>
<style>
*{margin:0;padding:0;list-style-type:none;font-size:14px;}
a,img{border:0;}
#files{margin:10px auto;width:260px;}
.tree,.tree ul,.tree li{list-style:none;margin:0;padding:0;zoom: 1;}
.tree ul{margin-left:18px;}
.tree li a{color:#555;padding:.1em 7px .1em 60px;display:block;text-decoration:none;border:0px dashed #fff;background:url('../../images/folder7/icon_file.png') 5px 50% no-repeat;text-align: left;
	height:25px;
	font-size: 14px;
	font-family: microsoft yahei;
	padding-top: 10px;
	background-position: 15% 50%;
}
.tree li a.tree-parent{background:url('../../images/folder7/folder_open.png') 5px 50% no-repeat; color: black;font-weight: bold;background-position: 15% 50%;background-color:#ccc;}
.tree li a.tree-parent-collapsed{background:url('../../images/folder7/folder_close.png') 5px 50% no-repeat;font-weight: normal;background-position: 15% 50%;}
.tree li a:hover,.tree li a.tree-parent:hover,.tree li a:focus,.tree li a.tree-parent:focus,.tree li a.tree-item-active{color:#000;border:0px solid #eee;background-color:#ccc;-moz-border-radius:4px;-webkit-border-radius:4px;border-radius:4px;}
.tree li a:focus,.tree li a.tree-parent:focus,.tree li a.tree-item-active{border:0px solid #e2f3fb;background-color:#ccc;}
.tree ul.tree-group-collapsed{display:none;}
/* .left{
	background-color: white;
} */
</style>
</head>
<body>
<div class="main">
	<div class="top">
		<div class="system_title">${prop['systemName']}</div>
		<div class="user_info">
			欢迎：${user.name} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="<%=path%>/manager/login/logout">退出</a>
		</div>
	</div>
	<div class="middle">
		<div class="left">
			<ul id="files">
				<c:forEach var="menu" items="${pages}" varStatus="menuIndex">
					<li><a href="javascript:void(0);">${menu.name}</a>
						<c:if test="${menu.children != null}">
							<ul>
							<c:forEach var="child" items="${menu.children}" varStatus="childIndex">
								<li><a href="<%=path %>/${child.url}" target="content" title="${child.name}">${child.name}</a></li>
							</c:forEach>
							</ul>
						</c:if>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="right">
			<iframe name="content" id="myframe" frameborder="0" scrolling="no" width="100%" height="100%" src="<%=basePath%>/page/welcome.jsp"></iframe>
		</div>
		<div style="clear:both;"></div>
	</div>
	<div class="bottom">${prop['copyrightName']}</div>
</div>
</body>
</html>