<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
<!--     <meta http-equiv="charset" content="utf-8"> -->
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="framework/kendo/js/jquery.min.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <p id="p" >userAgent:</p>
    <p id="h" >userInfo:</p>
	<script type="text/javascript">
		function GetBrowserInfo (){ //用不同的正则表达式(针对不同的浏览器,正则表达式不同)分析浏览器的版本号,获取浏览器信息(动态属性保存的浏览器版本号)
	    //浏览器集合对象,动态添加Sys.ie、Sys.firefox、Sys.chrome、Sys.opera、Sys.safari属性来存储各自浏览器的版本号
	    var ua = navigator.userAgent.toLowerCase();
	    var Sys = {OS:{}};
		var OSRxs = {
			ios : /(iphone|ipad|ipod);.* os ([\d(.|_)]+)/,
		   	android : /(android).([\d(.|_)]+)/,
		   	windows : /(windows nt).([\d.]+)/
		};
		var browserRxs = {
		   	ie : /(msie).([\d.]+)/,
		   	firefox : /(firefox)\/([\d.]+)/,
		  	chrome : /(chrome)\/([\d.]+)/,
		   	opera : /(opera).([\d.]+)/,
		   	safari : /version\/([\d.]+).*(safari)/
		}
		var match;
		for(var agent in OSRxs){
			match = ua.match(OSRxs[agent]);
            if(match) {
	            Sys.OS[agent] = true;
	            Sys.OS.version = match[2];
	            Sys.OS.name = match[1];
	            Sys.OS.fullName = match[0];
            }
		}
		for(var agent in browserRxs){
			match = ua.match(browserRxs[agent]);
            if(match) {
            	Sys[agent] = true;
                Sys.version = match[agent=="safari"?1:2]
                Sys.name = match[agent=="safari"?2:1];
                Sys.fullName = match[0];
            }
		 }
	    return Sys;
  }
		
		
		$(function(){
			var ua = navigator.userAgent.toLowerCase();
			$("#p").html(ua);
			
			var sys = GetBrowserInfo(),os = sys.OS;
			
			$("#h").html(os.ios + " ==? " + os.name +" : " + os.version +" --> " + os.fullName + "   |    " 
			+ sys.name + ":" + sys.version + " --> " + sys.fullName);
		});		

	</script>
  </body>
  
</html>

