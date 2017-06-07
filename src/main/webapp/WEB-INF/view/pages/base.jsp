<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><tiles:getAsString name="title"/></title>
<script src="/static/myjs/jquery.js"></script>
<script src="/static/myjs/jquery.address.js"></script>
<script src="/static/semantic/semantic.js"></script>
<script src="/static/semantic/semantic.min.js"></script>
<script src="/static/fancybox/jquery.fancybox.min.js"></script>


<link type="text/css" rel="stylesheet" href="/static/semantic/semantic.css" />
<link type="text/css" rel="stylesheet" href="/static/mystyle/header2.css">
<link type="text/css" rel="stylesheet" href="/static/mystyle/createChart.css">
<link type="text/css" rel="stylesheet" href="/static/mystyle/middleBody.css">
<link type="text/css" rel="stylesheet" href="/static/mystyle/exactContent.css">
<link rel="stylesheet" type="text/css" href="/static/fancybox/jquery.fancybox.min.css">

<link rel="shortcut icon" href="/static/img/anchor.ico">


<style type="text/css">
	html, body {
		overflow-x: hidden;
		overflow-y: auto;
		font-family: Arial, sans-serif;
	}





/**/

</style>


</head>


<body>


<div class="pageHead" id="pageHead">
	<div class="ui" id="headTitle">
		<div class="content">物流系统</div>
		<i class="huge anchor icon"></i>
	</div>
	
	<div class="ui secondary pointing menu" id="topmenu">
		<a class="item " href="/store_package" id="seg1">快递入库</a>
		<a class="item" href="/search_package" id="seg2">快递取件</a>
		<a class="item" href="/send_package" id="seg3">寄送快递</a>
		<a class="item" href="#          " id="seg4">违禁品查询</a>
		<a class="item" href="/generate_form" id="seg5">高级管理</a>
	</div>
</div>
<div class="middleBody" id="middleBody">

	<div class="leftSidePart">
		 <div class="ui card" id="leftCard">
		 <h3 class="header">登陆信息</h3>
		  <div class="image">
		    <img src="/static/temp/watchmen-horizontal.jpg">
		  </div>
		  <div class="content">
		    <div class="header">Vsooong</div>
		    <div class="description ">工号：1452693 &nbsp; &nbsp;  职位： 店员 </div>
		  </div>
		  <div class="ui bottom attached buttons">
		    <div class="ui button"><i class="mail forward icon"></i> 注销 </div>
		  </div>
		</div>
	

	
		<div id="sidemenu">
		<h3></h3>

			<tiles:insertAttribute name="body" />

		</div>
	</div>
	<script type="text/javascript">


        $(function (){
            $('.tabular .item').tab();
            $('.ui.checkbox').checkbox();
            setRCHeight();
        });
        function setRCHeight(){
            var obj=document.getElementById("middleBody");
            var rightContent=document.getElementById("rightContent");
            var style=null;
            if(window.getComputedStyle){
                style=window.getComputedStyle(obj,null);
            }
            else{
                style=obj.currentStyle;
            }
            rightContent.style.height=style.height+200;
            //alert("middleBody height:"+style.height);
        }
	</script>
</body>
</html>
		
    