<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath(); 
	//获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
	//将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
	pageContext.setAttribute("basePath",basePath); 
%>

<script src="<%=basePath%>/js/jquery/jquery.min.js"></script>
<script src="<%=basePath%>/js/jquery/jquery-ui.js"></script>
<script src="<%=basePath%>/js/commonUtil.js"></script>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/jquery/${prop['leftMenuStyle']}"/> --%>
<link rel="stylesheet" href="<%=basePath%>/css/jquery/jquery-ui.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/jquery/jquery-ui.min.css"/>
<link rel="stylesheet" href="<%=basePath%>/css/jquery/theme.css"/>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/table.css" /> --%>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/table1.css" /> --%>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/table2.css" /> --%>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/table3.css" /> --%>
<%-- <link rel="stylesheet" href="<%=basePath%>/css/table4.css" /> --%>
<link rel="stylesheet" href="<%=basePath%>/css/table5.css" />


<div id="dialog" title="弹窗" style="display: none;overflow: hidden;padding: 0;">
	<iframe id="dialog_content" style="width:100%;height:100%;border: 0;"></iframe>
</div>
