<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>简单的博客</title>
<script type="text/javascript" src="./jq.js"></script>
<script type="text/javascript">

$(function(){
	const frame = $('#main-content')
	$('.lileft').click(function(){
		frame[0].src=$(this).text()+'.htm';
	})
})
</script>
<style type="text/css">
html,body{width:100%;height:100%;margin:0px}
iframe{border-width:0px;width:100%;height:100%;float:right}
#nvg{width:250px;height:100%;margin:0px;float:left}
#content{float:right;height:100%;left:251px;right:0px;position:absolute}
.ulc{list-style-type:none;padding-left:10px}
.lic-ato{display:inline;width:100%}
.lileft{float:left;width:75%;height:100%;line-height:20px;margin:auto}
.liright{float:right;width:25%;height:100%;text-align:end;margin:auto}
.uldiv{width:100%;height:100%;display:flex}
.timestamp{color:#b7b7b7;font-size:5px}
</style>
</head>
<body>
<div id='nvg' onload='reqContent()'><div style='text-align:center' id='con'><span style='font-size:32px'>目录</span></div><ul class='ulc'>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
HashMap<File, Long> cvt = (HashMap<File, Long>)request.getAttribute("converted");
for(Entry<File, Long> e: cvt.entrySet()) {
	
%>
<li class='uldiv'>
<div class='lic-ato lileft'><%=e.getKey().getName().substring(0, e.getKey().getName().lastIndexOf(".")) %></div>
<div class='lic-ato liright'><span class='timestamp'><%=format.format(e.getValue()) %></span></div>
</li>
<hr/>
<%
}
%>
</ul></div>
<div id='content'><iframe id='main-content' src=''></iframe></div>
</body>
</html>