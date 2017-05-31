<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <!-- Standard Meta -->
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

  <!-- Site Properties -->
  <title>Login Page</title>
 
	<script src="/static/myjs/jquery.js"></script>
	<script src="/static/semantic/semantic.js"></script>
	<link type="text/css" rel="stylesheet" href="/static/semantic/semantic.css" />
	<link type="text/css" rel="stylesheet" href="/static/mystyle/header2.css">
	

  <style type="text/css">
    body {
      background:url(/static/temp/login_bgx.gif);
    }
    body > .grid {
      height: 100%;
    }
    .image {
      margin-top: -100px;
    }
    .column {
      max-width: 400px;
      height: 600px;
      
      
    }

  </style>
  <script>
  $(document)
    .ready(function() {
      $('.ui.form')
        .form({
          fields: {
            email: {
              identifier  : 'email',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your e-mail'
                },
                {
                  type   : 'email',
                  prompt : 'Please enter a valid e-mail'
                }
              ]
            },
            password: {
              identifier  : 'password',
              rules: [
                {
                  type   : 'empty',
                  prompt : 'Please enter your password'
                },
                {
                  type   : 'length[6]',
                  prompt : 'Your password must be at least 6 characters'
                }
              ]
            }
          }
        })
      ;
    })
  ;

  function refreshImg(){
      var td = document.getElementById("img_code")
      td.innerHTML = ""
      td.innerHTML = "<img src = \"/code\" onclick='refreshImg()'>"
  }

  function subm() {



      var host = window.location.host

      var form = document.getElementById("login_form")

      var code = form.elements[2].value

      function callback(){
          console.log(request.responseText)

          if(request.responseText == "true"){
              var postForm = document.createElement("form") //表单对象
              postForm.method="post"
              postForm.action = '/login'

              var usernameInput = document.createElement("input") ; //username input
              usernameInput.setAttribute("name", "username") ;
              usernameInput.setAttribute("value", form.elements[0].value);
              postForm.appendChild(usernameInput) ;
              var pwdInput = document.createElement("input");// password input
              pwdInput.setAttribute("name","password");
              pwdInput.setAttribute("value",form.elements[1].value);
              postForm.appendChild(pwdInput);
//              $.post("/login", { username: form.elements[0].value, password: form.elements[1].value },
//              function (result) {
//                  alert(result)
//                  window.location = result
//              })

              document.body.appendChild(postForm)
              postForm.submit()
              document.body.removeChild(postForm)

          }
          else{
              var td = document.getElementById("img_code")
              td.innerHTML = ""
              td.innerHTML = "<img src = \"/code\">"
          }
      }


      var request = new XMLHttpRequest();
      request.open("GET", "http://" + host + "/checkCode?code=" + code)
      request.onreadystatechange = callback
      request.send()



  }
  </script>
</head>
<body>

<div class="pageHead" id="pageHead">
	<div class="ui" id="headTitle">
		<div class="content">物流系统</div>
		<i class="huge anchor icon"></i>
	</div>
	
	<div class="ui secondary pointing menu" id="topmenu">
		<a class=" item" href="#" data-tab="sm1">快递入库</a>
		<a class="item" href="#" data-tab="sm2">快递取件</a>
		<a class="item" href="#" data-tab="sm3">寄送快递</a>
		<a class="item" href="#" data-tab="sm4">违禁品查询</a>
		<a class="item" href="#" data-tab="sm5">高级管理</a>
	</div>
</div>

<div class="ui middle aligned center aligned grid">
  <div class="column">
    <h2 class="ui teal image header">
      <i class="huge anchor icon"></i>
      <div class="content">
        物流系统
      </div>
    </h2>
    <form class="ui large form" id="login_form">
      <div class="ui stacked segment">
        <div class="field">
          <div class="ui left icon input">
            <i class="user icon"></i>
            <input type="text" name="username" placeholder="用户名">
          </div>
        </div>
        <div class="field">
          <div class="ui left icon input">
            <i class="lock icon"></i>
            <input type="password" name="password" placeholder="密码">
          </div>
        </div>
          <div class="field">
              <div class="ui left icon input">
                  <table>
                      <tr>
                          <td id = "img_code"><img src = "/code" onclick="refreshImg()"></td>
                          <td><input name = "code" id = "code" type="text"></td>
                      </tr>
                  </table>

              </div>
          </div>

        <input class="ui fluid large teal submit button" onclick="subm()" placeholder="登录"/>
      </div>

      <div class="ui error message"></div>

    </form>

    <div class="ui message" >
      加入我们？ <a href="#">注册</a>
    </div>
  </div>
</div>

</body>

</html>
