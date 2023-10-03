<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	background-color: #BD0000;
}

.vertical-center {
	background-color: #FFE8E8;
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
	<form:form id="resetPasswordForm" action="password/user" modelAttribute="forgotPasswordParam" method="post">
			<h1>忘記帳號頁面</h1>
			
			帳號：<form:input type="text" path="account" name="account" id="account" placeHolder="請輸入帳號" />

			<c:if test="${param.message != null}">
			<p><font color="red">忘記密碼信件已發送至信箱</font>
			</c:if>
			<p><button>發送信件至註冊信箱</button>
		
		</form:form>
		
		<p><h4>
			<a href="registerProccess"><尚未註冊？點此註冊會員></a>
		</h4>
		<p>	<h4>
		<a href="http://localhost:8080/SpringMVC_Hibernate/index.jsp"><返回首頁></a>
	</h4>
</div>

</div>

</body>
</html>