
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<div class="ui link list tab segment active" data-tab="sm2">
	<a class="item" href="/generate_form">生成报表</a>
	<a class="item" href="/account_manage">账号管理</a>
	<a class="active item" href="/cupboard">智能柜管理</a>
</div>
</div>
</div>



<div class="rightContent" id="rightContent">

	<div class="ui top attached tabular menu">
		<a class="active item" data-tab="zngList">柜/架列表</a>
		<a class="item" data-tab="zngAdd">新加智能柜/货架</a>
	</div>
	<div class="ui bottom attached tab segment active" data-tab="zngList">
		<div><br/></div>
		<form class="ui form" style="margin-left: 5%">
			<div class="three fields">
				<div class="field">
					<label>智能柜/货架</label>
					<select class="ui fluid dropdown prov" id="pos" onchange="getAllCupShelf()">
						<option value="cup">智能柜</option>
						<option value="shelf">货架</option>
					</select>
				</div>
				<div class="field">
					<label>智能柜柜号/货架架号：</label>
					<input type="text" name="zngID" id="id"/>
				</div>
				<div class="field">
					<input type="button" class="ui button" onclick="getCupShelf()" style="align-content:center; margin-top: 23px" value="查询"></input>
				</div>
			</div>
		</form>

		<div><br/></div>
		<div class="ui divider"></div>
		<div><br/><br/><br/></div>

		<table class="ui celled table">
			<thead>
			<tr>
				<th>编号</th>
				<th>总容量</th>
				<th>空余容量</th>
			</tr>
			</thead>
			<tbody id="cupContent">
			<tr>
				<td>G01</td>
				<td>100</td>
				<td>36</td>
			</tr>
			<tr>
				<td>G02</td>
				<td>300</td>
				<td>101</td>
			</tr>
			<tr>
				<td>G03</td>
				<td>200</td>
				<td>22</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="ui bottom attached tab segment" data-tab="zngAdd">
		<div><br/></div>
		<form class="ui form" style="margin-left: 5%; width: 90%">
			<h3 class="ui dividing header">请完善新货柜信息！</h3>
			<div class="fields inline">
				<div class="two wide field">
				</div>
				<div class="two wide field">
					<label>添加类型</label>
				</div>
				<div class="six wide field">
					<select class="form-control" id="newPos">
						<option value="cup">智能柜</option>
						<option value="shelf">货架</option>
					</select>
				</div>
			</div>
			<br />
			<div class="fields inline">
				<div class="two wide field">
				</div>
				<div class="two wide field">
					<label>列数</label>
				</div>
				<div class="six wide field">
					<input type="text" name="containerName" id="column"/>
				</div>
			</div>
			<br />
			<div class="fields inline">
				<div class="two wide field">
				</div>
				<div class="two wide field">
					<label>行数</label>
				</div>

				<div class="two wide field">
					<input class="two wide" type="text" name="bigCell" id="layer"/>
				</div>


			</div>

			<div class="field">
				<br /><br /><br /><br /><br />
			</div>
			<div class="fields inline">
				<div class="three wide field">
				</div>
				<div class="three wide field">
					<input type="button" class="ui green button" onclick="addNewCupShelf()" value="添加"></input>
				</div>
				<div class="two wide field">
					<input type="button" class="ui red button" onclick="cleanAdd()" value="取消"></input>
				</div>
			</div>
		</form>
	</div>
</div>

<script src="/static/myjs/cupboard.js"></script>