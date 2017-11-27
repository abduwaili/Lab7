<!DOCTYPE HTML>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生登录</title>

<link href="css/stlogin_css.css" rel="stylesheet" type="text/css">
<style type="text/css">
body {
	background-image: url(images/stlogin_bg1.jpg);
	background-repeat: repeat;
}
</style>

<script type="text/javascript" src="js/login.js"></script>

</head>


<body>

<div >
    <div class="maindiv" align="center" >
    
      <form name="form1" method="post" action="" class="from_main">
                <div align="left" class="xueshengdenglu">学生登录</div>              
                <input name="textfield" type="text" class="sousuokuang" placeholder="输入用户名" >
                <input name="textfield2" type="text" class="sousuokuang" placeholder="输入密码">
                <div align="right" class="jiaoshirukou"><a href="tclogin.jsp">教师入口</a></div>
                <input name="button" type="submit" class="loginbutton" value="登录">
                <div align="right" class="jiaoshirukou"><a href="stregister.jsp">账号注册</a></div>
      </form>
    </div>
</div>

</body>



</html>