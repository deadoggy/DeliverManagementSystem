

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div class="ui link list tab segment active" data-tab="sm2">
        <a class="item" href="/search_package">货物查询</a>
        <a class="active item" href="/taken_record">取件记录</a>
        <a class="item" href="/taken_out_time">过期取件</a>
        
      </div>
		</div>
	</div>
	<div class="rightContent" id="rightContent">
		<div class="ui" id="qjjl">
			<div id="qjTitle">取件记录</div>
			<div><br/></div>
			<div class="ui divider"></div>
			<div><br/></div>
			<form class="ui form">
				<div class="two fields">
					<div class="field inline">
						<label style="font-size:15px;">货物单号: </label>
						<input type="text" name="goodID" id="goodId" oninput="pickupQuery()"/>
					</div>
					<div class="field">
						<input type="button" class="ui button primary " style="float:right;margin-right:30vh" onclick="pickupClickEvent()" value="查询"></input>
					</div>
				</div>
			</form>
			<div><br/></div>
			<div class="ui divider"></div>
			<div><br/><br/><br/></div>
			<table class="ui celled table">
				<thead>
					<tr>
						<th>货物单号</th>
						<th>位置信息</th>
						<%--<th>取件人</th>--%>
						<th>取件时间</th>
						
					</tr>
				</thead>
				<tbody id="pickupContent">
					<tr>
						<td>ID01</td>
						<td>G54</td>
						<td>未取走</td>
						<td>2017/4/17 13:00:00</td>
					</tr>
					<tr>
						<td>ID02</td>
						<td>G56</td>
						<td>客户取走</td>
						<td>2017/4/17 13:00:00</td>
					</tr>
					<tr>
						<td>ID03</td>
						<td>G57</td>
						<td>过期店员取走</td>
						<td>2017/4/17 13:00:00</td>
					</tr>
					<tr>
						<td>ID04</td>
						<td>G58</td>
						<td>客户取走</td>
						<td>2017/4/17 13:00:00</td>
					</tr>
					<tr>
						<td>ID05</td>
						<td>G60</td>
						<td>客户取走</td>
						<td>2017/4/17 13:00:00</td>
					</tr>
				</tbody>
			</table>
		</div>	
<!--2.qjjl  -->	
<script src="/static/myjs/pickupRecord.js"></script>