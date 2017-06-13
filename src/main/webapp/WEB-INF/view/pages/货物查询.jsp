<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>

  <div class="ui link list tab segment active" data-tab="sm2">
        <a class="active item" href="/search_package">货物查询</a>
        <a class="item" href="/taken_record">取件记录</a>
        <a class="item" href="/taken_out_time">过期取件</a>
        
      </div>
		</div>
	</div>
	<div class="rightContent" id="rightContent">
<!--2.hwcx  huowuchaxun-->	
		<div class="ui" id="hwcx">
			<div id="hwTitle">货物查询</div>
			<div><br/></div>
			<div class="ui divider"></div>
			
			<div class="ui form" >
			    <div class="three fields">
			      <div class="field">	 				
				        <label>手机号：</label>
				        <input type="text" name="kdNumber" placeholder="手机号" id="tel"/>
				  </div>
			      <div class="field">
				        <label>货物单号：</label>
				        <input type="text" name="kdNumber" placeholder="货物单号" id="goodId"/>
				  </div>
				  <div class="field">
				  <h2></h2>
				  <h4></h4>
				  <button class="ui button" id="hwCxbtn" onclick="goodQuery()">查询</button>
				  
				  </div>
			    </div>
			  </div>

			
			
			<div class="ui divider"></div>
			<div><br/><br/><br/></div>
			<table class="ui celled table">
				<thead>
					<tr>
						<th>货物单号</th>
						<th>位置信息</th>
						<th>入库时间</th>
						<th>滞留时间</th>
						<th>收件人</th>
						<th>取件操作</th>
					</tr>
				</thead>
				<tbody id="goodContent">

				</tbody>
			</table>
		</div>	
<!--2.hwcx  -->
<script src="/static/myjs/goodQuery.js"></script>
