<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>會員登入頁面</title>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>

        function loginAccount(){
            $.post({
                url:"${pageContext.request.contextPath}/loginAccount",
                data:{'account':$("#account").val()},
                success:function (data) {
                    if (data.toString()=='此為已註冊帳號'){
                        $("#accountInfo").css("color","green");
                    }else {
                        $("#accountInfo").css("color","red");
                    }
                    $("#accountInfo").html(data);
                }
            });
        }
        </script>

</head>
<body>
	<center>
		<form:form id="loginForm" action="login/user" modelAttribute="userLoginParam" method="post">
			<h1>會員登入頁面</h1>
			
			帳號：<form:input type="text" path="account" name="account" id="account" placeHolder="請輸入帳號" />
			<p>密碼：<form:input type="password" path="password" name="password" id="password" placeHolder="請輸入密碼"/>
			<p>
			<font color="red">
			<%
			if(request.getParameter("message") != null){
				String message = "密碼輸入錯誤，請重新輸入"; 
				out.println(message);
			}
			%>
			</font>
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
		<a href="http://localhost:8080/springmvc_JDBC/index.jsp"><返回首頁></a>
	</h4>
	</center>
</body>
</html>

<!--  AJAX 的简单使用，账号密码验证例子參考: https://blog.51cto.com/u_15057832/3905435 -->