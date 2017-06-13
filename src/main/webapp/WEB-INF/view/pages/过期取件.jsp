<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	
         <div class="ui link list tab segment active" data-tab="sm2">
	        <a class="item" href="/search_package">货物查询</a>
	        <a class="item" href="/taken_record">取件记录</a>
	        <a class="active item" href="/taken_out_time">过期取件</a>
      	</div>
	  </div>
	</div>
	
	
	
	<div class="rightContent" id="rightContent">
	
<!--2.gqqj  guoqiqujian-->	
		<div class="ui" id="gqqj">
			<div id="gqTitle">过期快递</div>
			<div><br/></div>
			<div class="ui divider"></div>
			<div><br/></div>
			<table class="ui celled table">
				<thead>
					<tr>
						<th>货物单号</th>
						<th>位置信息</th>
						<th>入库时间</th>
						<th>滞留时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="expiredContent">
					<tr>
						<td>ID01</td>
						<td>G54</td>
						<td>2017/4/17 13:00:00</td>
						<td>48h</td>
						<td><input type="button" class="ui yellow button" value="强制开柜" onclick="forcedOpen('100-2-2')"></input></td>
					</tr>
					<tr>
						<td>ID03</td>
						<td>G56</td>
						<td>2017/4/17 13:00:00</td>
						<td>48h</td>
						<td><button class="ui yellow button">强制开柜</button></td>
					</tr>
					<tr>
						<td>ID07</td>
						<td>G34</td>
						<td>2017/4/17 13:00:00</td>
						<td>48h</td>
						<td><button class="ui yellow button">强制开柜</button></td>
					</tr>
					<tr>
						<td>ID09</td>
						<td>G04</td>
						<td>2017/4/17 13:00:00</td>
						<td>48h</td>
						<td><button class="ui yellow button">强制开柜</button></td>
					</tr>
					<tr>
						<td>ID06</td>
						<td>G26</td>
						<td>2017/4/17 13:00:00</td>
						<td>48h</td>
						<td><button class="ui yellow button">强制开柜</button></td>
					</tr>
				</tbody>
			</table>
		</div>	
<!-- gqqj -->
<script src="/static/myjs/expiredPickup.js"></script>


