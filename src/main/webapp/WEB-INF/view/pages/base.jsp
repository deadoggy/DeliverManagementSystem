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

<link rel="stylesheet" type="text/css" href="/static/mystyle/base.css">

<link rel="shortcut icon" href="/static/img/anchor.ico">

</head>


<body>
<div class="pageHead" id="pageHead" style="height: 8em">
	<div class="ui" id="headTitle">
		<div class="content">物流系统</div>
		<i class="huge anchor icon" style="margin-bottom: 10%"></i>
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
		  <div class="image" id="userImg">
			  <div class="ui bottom attached buttons" id="uploadDiv" >
				  <div class="ui button uploadBtn" onclick="showUpload()"><i class="file image outline icon"></i>上传头像</div>
			  </div>
		    <img src="/static/temp/watchmen-horizontal.jpg" id="imgContent">
		  </div>

			 <%!
				 String username = "Vsooong";
				 String id = "1452693";
				 String position = "店员";
			 %>
			 <%
				 Cookie cookie = null;
				 Cookie[] cookies = null;
				 // 获取cookies的数据,是一个数组
				 cookies = request.getCookies();
				 if( cookies != null ){
					 for (int i = 0; i < cookies.length; i++){
						 cookie = cookies[i];
						 if((cookie.getName( )).compareTo("id") == 0 ){
						     id = cookie.getValue();
						 }
						 if((cookie.getName( )).compareTo("name") == 0 ){
						     username = cookie.getValue();
						 }
						 if((cookie.getName( )).compareTo("pos") == 0 ){
						     position = cookie.getValue();
						 }
					 }
				 }
			 %>
		  <div class="content">
		    <div class="header"><%=username%> </div>
		    <div class="description ">工号：<%=id%> &nbsp; &nbsp;  职位：<%=position%> </div>
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

            var initImg = document.getElementById("imgContent");
            var id = "01";
            initImg.src = "ftp://139.129.18.35/" + id;

            document.getElementById("userImg").onmouseover = function(){
                var divFloat = document.getElementById("uploadDiv");
                divFloat.style.display="block";
                this.onmouseout = function (){
                    divFloat.style.display="none";
                }
            };
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

        function showUpload(){
            $.fancybox.open('<div><h2 class="ui header"> <i class="file image outline icon blue"></i> <div class="content">请选择头像</div> </h2>' +
					'<div class="description ">建议780*430像素，大小不超过2M，格式为bmp、png、jpeg、jpg、gif</div> <br/>'+
				'<div><form action="/upload_img" enctype="multipart/form-data" method="post"> ' +
				'<div class="ui bottom attached button fileInputBtn" id="fileInputBtn">上传文件' +
				'<input name="file" type="file" id="file"></div><br/>' +
				'<input type="submit" value="上传" class="positive fluid ui button"> </form></div></div>');

            $("#fileInputBtn").click(function () {
                $("#fileInputBtn").text("文件上传成功！");
            });
        }
	</script>
</body>
</html>
		
    