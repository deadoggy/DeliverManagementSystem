<%--
  Created by IntelliJ IDEA.
  User: luminous
  Date: 6/10/2017
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>


<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<div class="ui link list tab segment active" data-tab="sm3">
    <a class="item" href="/send_package">寄送快递</a>
    <a class="active item" href="/send_record">寄件记录</a>

</div>
</div>
</div>
<div class="rightContent" id="rightContent">
    <div class="ui" id="qjjl">
        <div id="qjTitle">寄件记录</div>
        <div><br/></div>
        <div class="ui divider"></div>
        <div><br/></div>
        <form class="ui form">
            <div class="two fields">
                <div class="field inline">
                    <label style="font-size:15px;">货物单号: </label>
                    <input type="text" name="goodID" id="goodId""/>
                </div>
                <div class="field">
                    <input type="button" class="ui button primary " style="float:right;margin-right:30vh" onclick="querySend()" value="查询"></input>
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
                <th>寄件人</th>
                <th>寄件地址</th>
                <th>收件人</th>
                <th>收件地址</th>
                <th>重量</th>
            </tr>
            </thead>
            <tbody id="sendContent">
            </tbody>
        </table>
    </div>
    <!--2.qjjl  -->
    <script src="/static/myjs/sendRecord.js"></script>
