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
						<select class="ui fluid dropdown" id="condition">
							<div class="default text">查询条件</div>
							<option value="id">员工号</option>
							<option value="name">员工姓名</option>
						</select>
					</div>
					<div class="eight wide field">
						<input type="text" name="queryValue" id="queryValue">
					</div>
					<div class="two wide field">
						<input class="ui blue button" type="button" id="ygCxbtn" value="查询" onclick="searchAccount()">
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
						<th>职位</th>
						<th>性别</th>
						<th>年龄</th>
						<th>电话</th>
						<th>工资(元/月)</th>
						<th>营业网点</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="accountContent">
					<tr>
						<td>JD001</td>
						<td>张三</td>
						<th>职员</th>
						<td>男</td>
						<td>33</td>
						<td>133xxxxxxxx</td>
						<td>3000</td>
						<td>A</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
					<tr>
						<td>JD002</td>
						<td>李四</td>
						<th>职员</th>
						<td>女</td>
						<td>31</td>
						<td>133xxxxxxxx</td>
						<td>2800</td>
						<td>B</td>
						<td><button class="ui yellow button">编辑</button>
							<button class="ui red button">删除</button>
						</td>
					</tr>
					<tr>
						<td>JD003</td>
						<td>王五</td>
						<th>职员</th>
						<td>女</td>
						<td>31</td>
						<td>133xxxxxxxx</td>
						<td>2900</td>
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
						<input type="text" name="name" id="name"/>
						
					</div>
				</div>
				<br />

				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>职位</label>
					</div>
					<div class="six wide field">
						<select class="form-control" id="position">
							<option value="manager">管理员</option>
							<option value="employee">职员</option>
						</select>
					</div>
				</div>
				<br/>

				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>设置密码</label>
					</div>
					<div class="six wide field">
						<input type="password" name="name" id="passwd"/>
					</div>
				</div>
				<br />

				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>确认密码</label>
					</div>
					<div class="six wide field">
						<input type="password" name="name" id="passwd2"/>
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
						<select class="form-control" id="gender">
							<option value="male">男</option>
							<option value="female">女</option>
						</select>
					</div>	
				</div>
				<br/>

				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>年龄</label>
					</div>
					<div class="six wide field">
						<input class="five wide" type="text" name="age" id="age"/><span>岁</span>
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
						<input type="text" name="phone" id="tele"/>
					</div>
				</div>
				<br/>

				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>工资</label>
					</div>
				
					<div class="three wide field">
						<input class="two wide" type="text" name="salary" id="salary"/>
					</div>
					<span class="two wide">元（月）</span>
				</div>
				<br/>

				<div class="fields inline">
					<div class="two wide field">
					</div>
					<div class="two wide field">
						<label>营业网点</label>
					</div>

					<div class="six wide field">
						<select class="form-control" id="point">
							<option value="100">同济大学</option>
						</select>
					</div>

				</div>
				<br/>

				<div class="fields inline">
					<div class="three wide field">
					</div>
					<div class="three wide field">
						<input class="ui green button"  type="button" value="添加" onclick="addAccount()"></input>
					</div>
					<div class="two wide field">
						<input class="ui red button" type="button" value="取消" onclick="cancelAdd()"></input>
					</div>
				</div>
			</form>
		</div>
	</div>

<div class="ui modal">
	<div class="header"><i class="user icon blue"></i>更新个人信息</div>
	<div class="content">
		<form class="ui form" style="margin-left: 2%; width: 96%">
			<div class="four fields">
				<div class="field">
					<label>ID：</label>
					<div class="ui disabled input">
						<input type="text" id="accountId"/>
					</div>
				</div>
				<div class="field">
					<label>用户名：</label>
					<div class="ui input">
						<input type="text" id="newName" onchange="updateName()"/>
					</div>
				</div>
				<div class="field">
					<label>工资：</label>
					<div class="ui input">
						<input type="text" id="newSalary" onchange="updateSalary()"/>
					</div>
				</div>
				<div class="field">
					<label>网点：</label>
					<div class="ui input">
						<input type="text" id="newPoint" onchange="updatePoint()"/>
					</div>
				</div>
			</div>
			<div class="ui styled accordion" style="width:100%">
				<div class="title"><i class="dropdown icon"></i> 更改密码 </div>
				<div class="content">
					<div class="ui grid">
						<div class="six wide column">
							<div class="ui input" id="newPwd1Input" style="width: 100%">
								<input type="password" placeholder="请输入新密码" id="newPwd1" oninput="removeErrorPwd1()">
							</div>
						</div>
						<div class="six wide column">
							<div class="ui input" id="newPwd2Input" style="width: 100%">
								<input type="password" placeholder="请再次输入确认新密码" id="newPwd2" oninput="removeErrorPwd2()">
							</div>
						</div>
						<div class="two wide column">
							<input type="button" class="ui button" onclick="updatePwd()" value="确认更新">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script src="/static/myjs/accountManage.js"></script>
