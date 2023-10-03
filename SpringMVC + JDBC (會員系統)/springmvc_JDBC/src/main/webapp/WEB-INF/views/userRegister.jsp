<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ page import="userSystem.model.User" %>
<%@ page import="userSystem.service.UserServiceImpl" %>          
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊頁面</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>

        function userAccount(){
            $.post({
                url:"${pageContext.request.contextPath}/registerAccount",
                data:{'account':$("#account").val()},
                success:function (data) {
                    if (data.toString()=='OK'){
                        $("#accountInfo").css("color","green");
                    }else {
                        $("#accountInfo").css("color","red");
                    }
                    $("#accountInfo").html(data);
                }
            });
        }
        function userPassword(){
            $.post({
                url:"${pageContext.request.contextPath}/registerPassword",
                data:{'password':$("#password").val()},
                success:function (data) {
                    if (data.toString()=='OK'){
                        $("#passwordInfo").css("color","green");
                    }else {
                        $("#passwordInfo").css("color","red");
                    }
                    $("#passwordInfo").html(data);
                }
            });
        }
        function userName(){
            $.post({
                url:"${pageContext.request.contextPath}/registerName",
                data:{'name':$("#name").val()},
                success:function (data) {
                    if (data.toString()=='OK'){
                        $("#nameInfo").css("color","green");
                    }else {
                        $("#nameInfo").css("color","red");
                    }
                    $("#nameInfo").html(data);
                }
            });
        }
        function userEmail(){
            $.post({
                url:"${pageContext.request.contextPath}/registerEmail",
                data:{'email':$("#email").val()},
                success:function (data) {
                    if (data.toString()=='OK'){
                        $("#emailInfo").css("color","green");
                    }else {
                        $("#emailInfo").css("color","red");
                    }
                    $("#emailInfo").html(data);
                }
            });
        } 
    </script>

<style>
span{color:red};

</style>

</head>
<body>

<center>
<h1>會員註冊頁面</h1>

<form:form id="registerForm" action="register/user" modelAttribute="userRegisterParam" method="post">
<h3>帳號密碼設定</h3>

<span>*</span>帳號：<form:input type="text" path="account" name="account" id="account" onblur="userAccount()" placeHolder="請輸入帳號"/>
<p><span id="accountInfo"></span>

<p><span>*</span>密碼：<form:input type="text" path="password" name="password" id="password" onblur="userPassword()" placeHolder="請輸入密碼"/>
<p><span id="passwordInfo"></span>

<h3>會員資料填寫</h3>

<p><span>*</span>姓名：<form:input type="text" path="name" name="name" id="name" onblur="userName()" placeHolder="請輸入姓名"/>
<p><span id="nameInfo"></span>
<p><span>*</span>信箱：<form:input type="Email" path="email" name="email" id="email" onblur="userEmail()" placeHolder="請輸入信箱"/>
<p><span id="emailInfo"></span>
<p>生日：<form:input type="date" path="birthday" name="birthday" id="birthday"/>

<p>性別：<form:select path="sex" id="sex">
<option value="" selected>請選擇性別</option>
<option value="男">男</option>
<option value="女">女</option>
</form:select>

<p>
	<font color="red">
		<%
		if(request.getParameter("message") != null){
			String message = "註冊失敗，請重新輸入資料"; 
			out.println(message);
		}
		%>
	</font>

<p><button >送出註冊資料</button>

<p><h4>
		<a href="loginProccess"><已經有帳號了？點此立即登入></a>
	</h4>
<p>	<h4>
		<a href="http://localhost:8080/springmvc_JDBC/index.jsp"><返回首頁></a>
	</h4>
</form:form>

</center>

</body>
</html>