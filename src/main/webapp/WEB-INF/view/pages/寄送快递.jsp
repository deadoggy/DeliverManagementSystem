
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



   <div class="ui link list tab segment active" data-tab="sm3" >
        <a class="active item" href="寄送快递.jsp">寄送快递</a>
      
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
        <input type="text" name="shipping_name" placeholder="收货人姓名">
      </div>
   </div>
   <div class="field">
      <label>联系电话</label>
      <div class="field">
        <input type="text" name="shipping_phone" placeholder="收货人电话">
      </div>
  </div>
  </div>

  <div class="two fields">
      <div class="field">
      <label>省/特别行政区</label>
    <select class="ui fluid dropdown">
    <option value="VT">上海</option>
    <option value="VT">Vermont</option>
    <option value="VA">Virginia</option>
    <option value="WA">Washington</option>
    <option value="WV">West Virginia</option>
    <option value="WI">Wisconsin</option>
    <option value="WY">Wyoming</option>
    </select>

    </div>
    <div class="field">
      <label>城市</label>
      <select class="ui fluid dropdown">
      <option value="">上海</option>
    <option value="AL">Alabama</option>
    <option value="AK">Alaska</option>
    <option value="AZ">Arizona</option>
    <option value="AR">Arkansas</option>
    <option value="CA">California</option>
    <option value="CO">Colorado</option>
    <option value="CT">Connecticut</option>
    <option value="DE">Delaware</option>
    <option value="DC">District Of Columbia</option>
    <option value="FL">Florida</option>
    <option value="GA">Georgia</option>
    <option value="HI">Hawaii</option>
    <option value="ID">Idaho</option>
    <option value="IL">Illinois</option>
    <option value="IN">Indiana</option>
    <option value="IA">Iowa</option>
    <option value="KS">Kansas</option>
    <option value="KY">Kentucky</option>
    <option value="LA">Louisiana</option>
    <option value="ME">Maine</option>
    <option value="MD">Maryland</option>
    <option value="MA">Massachusetts</option>
    <option value="MI">Michigan</option>
    <option value="MN">Minnesota</option>
    <option value="MS">Mississippi</option>
    <option value="MO">Missouri</option>
    <option value="MT">Montana</option>
    <option value="NE">Nebraska</option>
    <option value="NV">Nevada</option>
    <option value="NH">New Hampshire</option>
    <option value="NJ">New Jersey</option>
    <option value="NM">New Mexico</option>
    <option value="NY">New York</option>
    <option value="NC">North Carolina</option>
    <option value="ND">North Dakota</option>
    <option value="OH">Ohio</option>
    <option value="OK">Oklahoma</option>
    <option value="OR">Oregon</option>
    <option value="PA">Pennsylvania</option>
    <option value="RI">Rhode Island</option>
    <option value="SC">South Carolina</option>
    <option value="SD">South Dakota</option>
    <option value="TN">Tennessee</option>
    <option value="TX">Texas</option>
    <option value="UT">Utah</option>
    <option value="VT">Vermont</option>
    <option value="VA">Virginia</option>
    <option value="WA">Washington</option>
    <option value="WV">West Virginia</option>
    <option value="WI">Wisconsin</option>
    <option value="WY">Wyoming</option>
      </select>
    </div>

  </div>
    <div class="field">
    <label>收货人地址</label>
    <div class="fields">
      <div class="twelve wide field">
        <input type="text" name="shipping[address]" placeholder="街道信息">
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
        <input type="text" name="shipping_name" placeholder="寄件人姓名">
      </div>
   </div>
   <div class="field">
      <label>联系电话</label>
      <div class="field">
        <input type="text" name="shipping_phone" placeholder="寄件人电话">
      </div>
  </div>
  </div>
  <div class="field">
    <label>寄件人地址</label>
    <div class="fields">
      <div class="twelve wide field">
        <input type="text" name="shipping[address]" placeholder="街道信息">
      </div>
      <div class="four wide field">
        <input type="text" name="shipping[address-2]" placeholder="备注">
      </div>
    </div>
  </div>
  <div class="two fields">
    <div class="field">
      <label>城市</label>
      <select class="ui fluid dropdown">
      <option value="">上海</option>
    <option value="AL">Alabama</option>
    <option value="AK">Alaska</option>
    <option value="AZ">Arizona</option>
    <option value="AR">Arkansas</option>
    <option value="CA">California</option>
    <option value="CO">Colorado</option>
    <option value="CT">Connecticut</option>
    <option value="DE">Delaware</option>
    <option value="DC">District Of Columbia</option>
    <option value="FL">Florida</option>
    <option value="GA">Georgia</option>
    <option value="HI">Hawaii</option>
    <option value="ID">Idaho</option>
    <option value="IL">Illinois</option>
    <option value="IN">Indiana</option>
    <option value="IA">Iowa</option>
    <option value="KS">Kansas</option>
    <option value="KY">Kentucky</option>
    <option value="LA">Louisiana</option>
    <option value="ME">Maine</option>
    <option value="MD">Maryland</option>
    <option value="MA">Massachusetts</option>
    <option value="MI">Michigan</option>
    <option value="MN">Minnesota</option>
    <option value="MS">Mississippi</option>
    <option value="MO">Missouri</option>
    <option value="MT">Montana</option>
    <option value="NE">Nebraska</option>
    <option value="NV">Nevada</option>
    <option value="NH">New Hampshire</option>
    <option value="NJ">New Jersey</option>
    <option value="NM">New Mexico</option>
    <option value="NY">New York</option>
    <option value="NC">North Carolina</option>
    <option value="ND">North Dakota</option>
    <option value="OH">Ohio</option>
    <option value="OK">Oklahoma</option>
    <option value="OR">Oregon</option>
    <option value="PA">Pennsylvania</option>
    <option value="RI">Rhode Island</option>
    <option value="SC">South Carolina</option>
    <option value="SD">South Dakota</option>
    <option value="TN">Tennessee</option>
    <option value="TX">Texas</option>
    <option value="UT">Utah</option>
    <option value="VT">Vermont</option>
    <option value="VA">Virginia</option>
    <option value="WA">Washington</option>
    <option value="WV">West Virginia</option>
    <option value="WI">Wisconsin</option>
    <option value="WY">Wyoming</option>
      </select>
    </div>
    <div class="field">
      <label>省/特别行政区</label>
    <select class="ui fluid dropdown">
    <option value="VT">上海</option>
    <option value="VT">Vermont</option>
    <option value="VA">Virginia</option>
    <option value="WA">Washington</option>
    <option value="WV">West Virginia</option>
    <option value="WI">Wisconsin</option>
    <option value="WY">Wyoming</option>
    </select>

    </div>
  </div>
   <h1></h1>
   <div class="ui attached icon info message">
  <h4 class="ui header " style="color:#FBB100"><i class="plane icon"></i>运输信息</h4>
  </div>
  <h2></h2>
  <div class="field">
    <label>货物重量级别</label>
    <div class="ui selection dropdown">
      <input type="hidden" name="card[type]">
      <div class="default text">类型</div>
      <i class="dropdown icon"></i>
      <div class="menu">
        <div class="item" data-value="visa"><i class="visa icon"></i> Visa </div>
        <div class="item" data-value="amex"><i class="amex icon"></i> American Express </div>
        <div class="item" data-value="discover"><i class="discover icon"></i> Discover </div>
      </div>
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
  <div class="ui button" tabindex="0" style="float:right">提交订单</div>
</form>

<script type="text/javascript">
$(function (){
	$("#seg3").addClass("active");
})
</script>

