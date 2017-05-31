<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      	<div class="ui link list tab segment active" data-tab="sm2">
	        <a class="item" href="/generate_form">生成报表</a>
	        <a class="active item" href="/account_manage">账号管理</a>
	        <a class="item" href="/cupboard">智能柜管理</a>
      	</div>
	 </div>
	</div>
	
	
	
	<div class="rightContent" id="rightContent">
	
		<div class="ui top attached tabular menu">
			<a class="active item" data-tab="ygList">员工列表</a>
			<a class="item" data-tab="ygAdd">新建员工账号</a>
		</div>
		<div class="ui bottom attached tab segment active" data-tab="ygList">
			<div><br/></div>
			<form class="ui form"  style="margin-left: 5%; width: 90%; height: 20%; margin-top: 2%">
				<div class="fields inline">
					<div class="four wide field">
						<select class="ui fluid dropdown">
							<div class="default text">查询条件</div>
							<option value="1">员工号</option>
							<option value="0">员工姓名</option>
						</select>
					</div>
					<div class="eight wide field">
						<input type="text" name="queryValue">
					</div>
					<div class="two wide field">
						<input class="ui blue button" type="button" id="ygCxbtn" value="查询">
					</div>
				</div>
				
			</form>
			
			<div></div>
			<div class="ui divider"></div>
			<div>
			</div>
			
			<table class="ui celled table">
				<thead>
					<tr>
						<th>员工号</th>
						<th>员工姓名</th>
						<th>性别</th>
						<th>年龄</th>
						<th>电话</th>
						<th>工资(元/月)</th>
						<th>营业网点</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>JD001</td>
						<td>张三</td>
						<td>男</td>
						<td>33</td>
						<td>3000</td>
						<td>133xxxxxxxx</td>
						<td>A</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
					<tr>
						<td>JD002</td>
						<td>李四</td>
						<td>女</td>
						<td>31</td>
						<td>2800</td>
						<td>133xxxxxxxx</td>
						<td>B</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
					<tr>
						<td>JD003</td>
						<td>王五</td>
						<td>女</td>
						<td>31</td>
						<td>2900</td>
						<td>133xxxxxxxx</td>
						<td>B</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="ui bottom attached tab segment" data-tab="ygAdd">
			<div><br/></div>
			<form class="ui form" style="margin-left: 5%; width: 90%">
				<h3 class="ui dividing header">请填写相关信息！</h3>
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>员工姓名</label>
					</div>
					<div class="six wide field">
						<input type="text" name="name" />
						
					</div>
				</div>
				<br />
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>性别</label>
					</div>
					<div class="six wide field">
						<select class="form-control">
							<option>男</option>
							<option>女</option>
						</select>
					</div>	
				</div>
				<br />
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>年龄</label>
					</div>
				
					<div class="two wide field">
						<input class="two wide" type="text" name="age" /><span>&nbsp;&nbsp;&nbsp;岁</span>
					</div>
				</div>
				<br />
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>电话</label>
					</div>
				
					<div class="six wide field">
						<input type="text" name="phone" />
					</div>
				</div>
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>工资</label>
					</div>
				
					<div class="three wide field">
						<input class="two wide" type="text" name="salary" /> 
					</div>
					<span class="two wide">元（月）</span>
				</div>
				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>营业网点</label>
					</div>
				
					<div class="six wide field">
						<input type="text" name="position" />
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
<script src="/static/myjs/accountManage.js"></script>
