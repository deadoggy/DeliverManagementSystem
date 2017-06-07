<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	   <div class="ui link list tab segment active" data-tab="sm1" >
        <a class="active item" href="#" >快递入库</a>
        <a class="item" href="/shelf_cup_transfer" >智能柜货架互转</a>
      </div>
		</div>
	</div>
	<div class="rightContent" id="rightContent">
	
		 <div >   
			<div >		
				<h2 class="ui header">			     
				     <i class="checked calendar icon blue"></i>
				     <div class="content">
				       货物单号
				     </div>
				</h2>
				<div class="ui divider"></div>
				<div style="display:flex ;justify-content:space-between;" >
				<div class="ui input focus" style="width:36em">
			  		<input type="text" placeholder="请输入货物单号，或者使用扫码器扫描" id="goodId" required>
			    </div>
				
				<div class="ui animated fade button" tabindex="0">
				  <div class="visible content">扫描条形码</div>
				  <div class="hidden content">请对准条形码 </div>
				</div>
				</div>				
			</div>		
			<p></p>
			<h1></h1>
			<h2></h2>
			<div >
			
				<h2 class="ui header">
				     
				     <i class="marker icon blue"></i>
				     <div class="content">
				       存放位置
				     </div>
				</h2>
				<div class="ui divider"></div>
				<div style="display:flex ;justify-content:    space-between; ">
				<div class="ui input focus" style="width:36em">
			  		<input type="text" placeholder="请输入存放位置(格式:行列)，或者系统随机生成" id="storageId" required>
			    </div>
				
				<div>
				  <div class="ui floating labeled icon dropdown button" id="dropdownBtn">
				    <i class="wrench icon"></i>
					  <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					  <span class="text">选择存放位置</span>
					  <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					  <div class="menu" id="menu">
					    <div class="item">
					      <span class="description">212剩余</span>
					      <span class="text">智能柜</span>
					    </div>
					    <div class="item">
					      <span class="description">10剩余</span>
					      <span class="text">货架</span>
					    </div>
					  </div>
					</div>
				</div>
				
				<div class="ui animated fade button" tabindex="0" onclick="generatePos()">
				  <div class="visible content">自动生成</div>
				  <div class="hidden content">自动生成 </div>
				</div>
				</div>			
			</div>
			<p></p>
			<h1></h1>
			<h2></h2>
			<div >
			
				<h2 class="ui header">
				     
				    <i class="warning sign blue icon"></i>
				     <div class="content">
				         通过安检
				     </div>
				</h2>
				<div class="ui divider"></div>
				<div >
					<div class="ui form" >
					  <div class="inline fields">
					    <label>货物是否以及通过安检？</label>
					    <div class="field">
					      <div class="ui radio checkbox">
					        <input type="radio" name="frequency" checked="checked" id="passCheckTrue">
					        <label>是，我确认货物通过了安检</label>
					      </div>
					    </div>
				
					    <div class="field">
					      <div class="ui radio checkbox">
					        <input type="radio" name="frequency" id="passCheckFalse">
					        <label>否，货物没有通过安检</label>
					      </div>
					    </div>
					  </div>
					</div>
				</div>			
			</div>
			<p></p>
			<h1></h1>
			<h2></h2>
			<button class="positive fluid ui button" onclick="store()"> 确认入库</button>
		
	    </div>

	<!--scbb  -->	
		
	


<script src="/static/myjs/storePackage.js"></script>
