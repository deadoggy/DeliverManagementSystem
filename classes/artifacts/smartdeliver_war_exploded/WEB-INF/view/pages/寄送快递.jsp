
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



   <div class="ui link list tab segment active" data-tab="sm3" >
        <a class="active item" href="/send_package">寄送快递</a>
      
   </div>
   </div>
	</div>
<div class="rightContent" id="rightContent">


<div class="ui big breadcrumb" style="display:flex ;justify-content:space-between;">
  <a class="active section">填写订单信息</a>
  <i class="right arrow icon divider"></i>
  <a class="section">提交订单</a>
  <i class="right arrow icon divider"></i>
  <a class="section">受理审核</a>
  <i class="right arrow icon divider"></i>
  <a class="section">揽件成功</a>
  <i class="right arrow icon divider"></i>
  <a class="section">发货成功</a>
 
</div>


<div class="ui divider"></div>
<h2></h2>

  
 <form class="ui form">
   <div class="ui attached icon info message">
   <h4 class="ui header " style="color:#FBB100"><i class="users icon"></i>收货人信息</h4>
  </div>
  <h2></h2>
  <div class="two fields">
  <div class="field">
    <label>姓名</label>
      <div class="field">
        <input type="text" name="shipping_name" placeholder="收货人姓名" id="rName">
      </div>
   </div>
   <div class="field">
      <label>联系电话</label>
      <div class="field">
        <input type="text" name="shipping_phone" placeholder="收货人电话" id="rTele">
      </div>
  </div>
  </div>

  <div class="two fields" id="rPos">
      <div class="field">
      <label>省/特别行政区</label>
    <select class="ui fluid dropdown prov" id="rProvince"></select>

    </div>
    <div class="field">
      <label>城市</label>
      <select class="ui fluid dropdown city" id="rCity"></select>
    </div>

  </div>
    <div class="field">
    <label>收货人地址</label>
    <div class="fields">
      <div class="twelve wide field">
        <input type="text" name="shipping[address]" placeholder="街道信息" id="rAdderss">
      </div>
      <div class="four wide field">
        <input type="text" name="shipping[address-2]" placeholder="备注">
      </div>
    </div>
  </div>

  <h1></h1>
  <div class="ui attached icon info message">
  <h4 class="ui header " style="color:#FBB100"><i class="user icon"></i>寄件人信息</h4>
  </div>
  <h2></h2>
  <div class="two fields">
  <div class="field">
    <label>姓名</label>
      <div class="field">
        <input type="text" name="shipping_name" placeholder="寄件人姓名" id="sName">
      </div>
   </div>
   <div class="field">
      <label>联系电话</label>
      <div class="field">
        <input type="text" name="shipping_phone" placeholder="寄件人电话" id="sTele">
      </div>
  </div>
  </div>
  <div class="field">
    <label>寄件人地址</label>
    <div class="fields">
      <div class="twelve wide field">
        <input type="text" name="shipping[address]" placeholder="街道信息" id="sAdderss">
      </div>
      <div class="four wide field">
        <input type="text" name="shipping[address-2]" placeholder="备注">
      </div>
    </div>
  </div>
  <div class="two fields" id="sPos">
    <div class="field">
      <label>省/特别行政区</label>
      <select class="ui fluid dropdown prov" id="sProvince"></select>
    </div>
    <div class="field">
      <label>城市</label>
    <select class="ui fluid dropdown city" id="sCity"></select>

    </div>
  </div>
   <h1></h1>
   <div class="ui attached icon info message">
  <h4 class="ui header " style="color:#FBB100"><i class="plane icon"></i>运输信息</h4>
  </div>
  <h2></h2>
  <div class="field">
    <label>货物重量</label>
      <div class="field">
      <input type="text" placeholder="货物重量（单位：kg）" id="weight">
      </div>
  </div>
  
  <div class="fields">
    <div class="ten wide field">
      <label>银行卡号码</label>
      <input type="text" name="card[number]" maxlength="19" placeholder="卡号">
    </div>
    
    <div class="six wide field">
      <label>支付方式</label>
      <div class="two fields">
        <div class="field">
          <select class="ui fluid search dropdown" name="card[expire-month]">
            <option value="">支付宝</option>
            <option value="1">Vista</option>
            <option value="2">微信支付</option>
            <option value="3">银行卡</option>
            
          </select>
        </div>
        <div class="field">
          <input type="text" name="card[expire-year]" maxlength="4" placeholder="￥运费">
        </div>
      </div>
    </div>
  </div>
   <h1></h1>
    <div class="ui attached icon info message">
  <h4 class="ui header " style="color:#FBB100"><i class="edit icon"></i>备注</h4>
  </div>
  <h2></h2>
   <div class="field">
    <label>货物备注：</label>
    <div class="ui fluid multiple search selection dropdown">
      <input type="hidden" name="receipt">
      <i class="dropdown icon"></i>
      <div class="default text">Saved Contacts</div>
      <div class="menu">
        <div class="item" data-value="jenny" data-text="Jenny"><img class="ui mini avatar image" src="/images/avatar/small/jenny.jpg"> Jenny Hess </div>
        <div class="item" data-value="elliot" data-text="Elliot"><img class="ui mini avatar image" src="/images/avatar/small/elliot.jpg"> Elliot Fu </div>
        <div class="item" data-value="stevie" data-text="Stevie"><img class="ui mini avatar image" src="/images/avatar/small/stevie.jpg"> Stevie Feliciano </div>
        <div class="item" data-value="christian" data-text="Christian"><img class="ui mini avatar image" src="/images/avatar/small/christian.jpg"> Christian </div>
        <div class="item" data-value="matt" data-text="Matt"><img class="ui mini avatar image" src="/images/avatar/small/matt.jpg"> Matt </div>
        <div class="item" data-value="justen" data-text="Justen"><img class="ui mini avatar image" src="/images/avatar/small/justen.jpg"> Justen Kitsune </div>
      </div>
    </div>
  </div>
   <div class="ui segment">
    <div class="field">
      <div class="ui toggle checkbox">
        <input type="checkbox" name="gift" tabindex="0" class="hidden">
        <label>同意接收到货通知短信</label>
      </div>
    </div>
  </div>
  <div class="ui button" tabindex="0" style="float:right" onclick="sendPackage()">提交订单</div>
</form>


    <%--<script src="/static/myjs/city.min.js"></script>--%>
    <script src="/static/myjs/jquery.cityselect.js"></script>
<script src="/static/myjs/sendPackage.js"></script>

