<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
      	<div class="ui link list tab segment active" data-tab="sm5">
	        <a class="active item" href="/generate_form">生成报表</a>
	        <a class="item" href="/account_manage">账号管理</a>
	        <a class="item" href="/cupboard">智能柜管理</a>
      	</div>
	 </div>
	</div>
	
	
	
	<div class="rightContent" id="rightContent">
	
		<div class="createChartP1">
				<div id="bbTitle">生成报表</div>
				<div><br/></div>
				<div class="ui divider"></div>

				<h3 class="ui block header">查询条件</h3>
				<form class="ui form chaxunform">
					<br />
					<div class="fields">
						<div class="field">
							<div class="inline field">
								<label>公司名称</label>
								<input type="text" placeholder="请输入公司名称" id="company" style="width:85%;">
							</div>
							<br/>

							<div class="inline field">
								<label>日期</label>
								<input type="date" name="begintime" id="fromTime"/>
								<span>&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;</span>
								<input type="date" name="endtime" id="toTime"/>
							</div>
							<br />
							<div class="inline fields">
								<div class="field">
									<div class="ui radio checkbox">
										<input type="radio" name="formType" id="taken_sum" checked="checked"/>
										<label>取件总量</label>
									</div>
								</div>
								<div class="field">
									<div class="ui radio checkbox">
										<input type="radio" name="formType" id="post_fee"/>
										<label>邮费统计</label>
									</div>
								</div>
								<div class="field">
									<div class="ui radio checkbox">
										<input type="radio" name="formType" id="package_info"/>
										<label>包裹情况</label>
									</div>
								</div>
								<div class="field">
									<div class="ui radio checkbox">
										<input type="radio" name="formType" id="send_rec"/>
										<label>寄件记录</label>
									</div>
								</div>
							</div>
						</div>
						<div class="field">
						<input type="button" class="ui big blue button" id="bbCxbtn" value="查询" onclick="generateForm()"></input>
						</div>
					</div>
				</form>
			</div>
			<div class="ui divider"></div>
			<div><br/></div>
			
			<div class="createChartP2">
				<h3 class="ui block header">结果图表</h3>
				<div><br/></div>
				<table class="ui table" id="formContent">

				</table>
				<br /><br /><br />
				<img src="/static/temp/pie.png" alt="chart1" />
				<img src="/static/temp/bar.png" alt="chart2" />
				<%--<br /><br /><br />--%>
				<%--<div class="inline field">--%>
					<%--<button class="ui blue button" type="submit">打印报表</button>--%>
					<%--<button class="ui red button" type="submit">纠错</button>--%>
				<%--</div>--%>
			</div>
		</div>

<script src="/static/myjs/generateForm.js"></script>
