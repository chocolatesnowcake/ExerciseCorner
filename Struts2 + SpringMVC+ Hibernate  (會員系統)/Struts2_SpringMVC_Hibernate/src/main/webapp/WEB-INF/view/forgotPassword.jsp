<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>忘記密碼</title>

<style>
html, body{height:100%;}
.container { 
  height: 100%;
  position: relative;
}

.vertical-center {
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
	<form action="ForgotPasswordAction" >
			<h1>忘記帳號頁面</h1>
			
			帳號：<input type="text" name="account" placeHolder="請輸入帳號" />

			<s:if test="message == 'wrong'">
			<p><font color="red">輸入帳號尚未註冊過，請確認是否輸入錯誤</font>
			</s:if>
			
			<s:if test="message == 'success'">
			<p><font color="green">信件將發送至註冊信箱，收到密碼後請立即登入並修改密碼</font>
			</s:if>
			
			<p><button>發送信件至註冊信箱</button>
		
		</form>
		
		<p><h4>
			<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/RegisterAction"><尚未註冊？點此註冊會員></a>
		</h4>
		<p>	<h4>
		<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/LoginAction"><收到密碼了嗎?點此馬上登入></a>
	</h4>
</div>

</div>

</body>
</html>