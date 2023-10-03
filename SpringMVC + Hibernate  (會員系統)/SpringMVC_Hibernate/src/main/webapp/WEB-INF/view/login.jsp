<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
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
	<form:form id="loginForm" action="login/user" modelAttribute="loginParam" method="post">
			<h1>會員登入頁面</h1>
			
			帳號：<form:input type="text" path="account" name="account" id="account" placeHolder="請輸入帳號" />
			<p>密碼：<form:input type="password" path="password" name="password" id="password" placeHolder="請輸入密碼"/>

			<c:if test="${param.message == 'wrong'}">
			<p><font color="red">帳號或密碼錯誤，請重新登入</font>
			</c:if>
			<c:if test="${param.message == 'success'}">
			<p><font color="red">帳號刪除成功</font>
			</c:if>
			<p><button>登入</button>
		
		</form:form>
		
		<h4>
			<a href="registerProccess"><尚未註冊？點此註冊會員></a>
		</h4>
		<p>
		<h4>
			<a href="passwordProccess"><忘記密碼？點此重設密碼></a>
		</h4>
		<p>	<h4>
		<a href="http://localhost:8080/SpringMVC_Hibernate/index.jsp"><返回首頁></a>
	</h4>
</div>

</div>

</body>
</html>