<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員登入</title>

<style>
html, body{height:100%;}
.container { 
	height: 100%;
	position: relative;
	background-color: #008500;
}

.vertical-center {
	background-color: #DEFFDE;
	width: 400px;
	margin: 0;
	position: absolute;
	top: 50%;
	left:50%;
	transform: translate(-50%,-50%);
	text-align:center;
}
</style>

</head>
<body>

<div class="container">

<div class="vertical-center">

	<form action="LoginAction" >
			<h1>會員登入頁面</h1>
			
			帳號：<input type="text" name="loginParam.account" placeHolder="請輸入帳號" />
			<p>密碼：<input type="password" name="loginParam.password" placeHolder="請輸入密碼"/>


			<s:if test="message == 'wrong'">
			<p><font color="red">密碼輸入錯誤，請再次登入</font>
			</s:if>
			<p><button>登入</button>
	</form>
		<p>
		<h4>
			<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/RegisterAction"><尚未註冊？點此立即註冊會員></a>
		</h4>
		<p>		
		<h4>
			<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/ForgotPasswordAction"><忘記密碼？點此重設密碼></a>
		</h4>
</div>

</div>

</body>
</html>