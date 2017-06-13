<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

  <div class="ui link list tab segment active" data-tab="sm1" >
        <a class="item" href="/store_package" >快递入库</a>
        <a class="active item" href="#  " >智能柜货架互转</a>
      </div>
		</div>
	</div>
	<div class="rightContent" id="rightContent">
<div class="ui section divider"></div>

  <div class="ui top attached warning icon message">
    <i class="edit icon"></i>
    <div class="content">
       <h3>要转移货物的位置</h3>
    </div>
  </div>
  <h2></h2>    
			<div >		
		
				<div style="display:flex ;justify-content:space-between;" >
				<div class="ui input focus" style="width:36em">
			  		<input type="text" placeholder="请输入货物的位置，格式为：货架号-行-列" id="fromPos">
			    </div>
				
				   <%--<div class="ui floating labeled icon dropdown button">--%>
				    <%--<i class="wrench icon"></i>--%>
					  <%--<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>--%>
					  <%--<span class="text">请选择转出位置</span>--%>
					  <%--<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>	  <div class="menu" id="menuFrom">--%>
					    <%--<div class="item">--%>
					      <%--<span class="text">智能柜</span>--%>
					    <%--</div>--%>
					    <%--<div class="item">--%>
					      <%--<span class="text">货架</span>--%>
					    <%--</div>--%>
					  <%--</div>--%>
					<%--</div>--%>
					
				<%--<div class="ui animated fade button" tabindex="0">--%>
				  <%--<div class="visible content">自动生成</div>--%>
				  <%--<div class="hidden content">已存在位置</div>--%>
				<%--</div>--%>
				</div>				
			</div>		
			<p></p>
			<h1></h1>
			<h2></h2>



  <div class="ui top attached warning icon message">
    <i class="warning icon"> &nbsp;&nbsp;</i>
    <div class="content">
       <h3>转移目标位置</h3>
    </div>
  </div>
  <h2></h2>    		
		
	<div style="display:flex ;justify-content:    space-between; ">
	<div class="ui input focus" style="width:36em">
  		<input type="text" placeholder="请输入存放位置（格式：货架号-行-列），或者系统自动生成" id="toPos">
    </div>
    
   <div class="ui floating labeled icon dropdown button" >
    <i class="wrench icon"></i>
	  <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	  <span class="text">请选择转入位置</span>
	  <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	   <div class="menu" id="menuTo">
	    <div class="item">
	      <span class="text">智能柜</span>
	    </div>
	    <div class="item">
	      <span class="text">货架</span>
	    </div>
	  </div>
	</div>
	
	<div class="ui animated fade button" tabindex="0" onclick="generateToPos()">
	  <div class="visible content">自动生成</div>
	  <div class="hidden content">空余位置 </div>
	</div>
	</div>			


	<p></p>
	<h1></h1>
	<h2></h2>
	
	<div class="ui divider"></div>
	<div class="ui bottom attached button" tabindex="0" onclick="shelfCupTransfer()">转移完成</div>
  

<script src="/static/myjs/shelfCupTransfer.js"></script>