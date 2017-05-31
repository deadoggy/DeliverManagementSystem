<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

	   <div class="ui link list tab segment active" data-tab="sm5" >
        <a class=" active item" data-tab="scbb">生成报表</a>
        <a class="item" data-tab="zhgl">账号管理</a>
        <a class="item" data-tab="znggl">智能柜管理</a>
      </div>
		</div>
	</div>
	<div class="rightContent" id="rightContent">
		<div class="ui tab segment active" id="scbb" data-tab="scbb">
			
		</div>
	<!--scbb  -->	
		
		<!--智能柜管理  -->
	<div class="ui link list tab segment" id="znggl" data-tab="znggl">
		<div class="ui top attached tabular menu">
			<a class="active item" data-tab="zngList">柜/架列表</a>
			<a class="item" data-tab="zngAdd">新加智能柜</a>
		</div>
		<div class="ui bottom attached tab segment active" data-tab="zngList">
			<div><br/></div>
			<form class="ui form">
				<div class="two fields">
					<div class="field inline">
						<label style="font-size:18px;">柜(架)号：</label>
						<input type="text" name="zngID" />
					</div>
					<div class="field">
						<button class="ui button primary ">&nbsp;&nbsp;&nbsp;查询&nbsp;&nbsp;&nbsp;</button>
					</div>
				</div>
			</form>
			
			<div><br/></div>
			<div class="ui divider"></div>
			<div><br/><br/><br/></div>
			
			<table class="ui celled table">
				<thead>
					<tr>
						<th>货柜/货架</th>
						<th>柜/架编号</th>
						<th>容量</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>货柜</td>
						<td>G01</td>
						<td>100</td>
						<td>正常</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
					<tr>
						<td>货架</td>
						<td>G02</td>
						<td>300</td>
						<td>正常</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
					<tr>
						<td>货柜</td>
						<td>G03</td>
						<td>200</td>
						<td>故障，维修中</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="ui bottom attached tab segment" data-tab="zngAdd">
			<div><br/></div>
			<form class="ui form">
				<h3 class="ui dividing header">请完善新货柜信息！</h3>
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>添加类型</label>
					</div>
					<div class="six wide field">
						<select class="form-control">
							<option>货架</option>
							<option>货柜</option>
						</select>
					</div>
				</div>
				<br />
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>货柜名称</label>
					</div>
					<div class="six wide field">
						<input type="text" name="containerName" />
					</div>	
				</div>
				<br />
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>数量</label>
					</div>
				
					<div class="two wide field">
						<input class="two wide" type="text" name="bigCell" />
					</div>
					
	
				</div>
				
				<div class="field">
					<br /><br /><br /><br /><br />
				</div>
				<div class="fields inline">
					<div class="three wide field">
					</div>
					<div class="three wide field">
						<button class="ui green button" type="submit">添加</button>
					</div>
					<div class="two wide field">
						<button class="ui red button" type="submit">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>
<!--智能柜管理  -->

<!--账号管理 -->	
<div class="ui link list tab segment" id="zhgl" data-tab="zhgl">
		
	</div>	
<!--账号管理 -->		
	
<script type="text/javascript">
$(function (){
	$("#seg5").addClass("active");
});

</script>

