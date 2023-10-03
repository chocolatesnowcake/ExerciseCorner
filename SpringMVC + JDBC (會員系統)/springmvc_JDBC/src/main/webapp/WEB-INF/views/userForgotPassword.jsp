<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>忘記密碼</title>
</head>
<body>

	<center>
		<form:form action="findPassword/user" modelAttribute="userForgotPasswordParam" method="post">
			<h1>會員忘記密碼頁面</h1>
			
			帳號：<form:input type="text" path="account" name="account" id="account" placeHolder="請輸入帳號" />
			
			<p>
			<h5>
			<font color="red">${message}</font>
			</h5>
			
			<p><button>點擊發送郵件</button>
			
		</form:form>
		
		<p>	<h4>
		<a href="http://localhost:8080/springmvc_JDBC/index.jsp"><返回首頁></a>
	</h4>
	</center>

</body>
</html>