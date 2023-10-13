<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊會員</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>

        function userAccount(){
        	$.ajax({
        		type:"post",
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
        	$.ajax({
        		type:"post",
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
    </script>
    <!-- 按鈕發送控制參考: https://blog.51cto.com/u_3664660/3213878 -->


<style>
span{color:red;}
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
	<h1>會員註冊頁面</h1>

	<form:form id="registerForm" action="register/user" modelAttribute="registerParam" method="post">
	<h3>帳號密碼設定</h3>
	
	<span>*</span>帳號：<form:input type="text" path="account" name="account" id="account" onblur="userAccount()" placeHolder="請輸入帳號"/> 
	<p><span id="accountInfo"></span>
	
	<p><span>*</span>密碼：<form:input type="text" path="password" name="password" id="password" onblur="userPassword()" placeHolder="請輸入密碼"/> 
	<p><span id="passwordInfo"></span>
	
	<h3>會員資料填寫</h3>
	
	<p>姓名：<form:input type="text" path="name" name="name" id="name" placeHolder="請輸入姓名"/>
	<p>信箱：<form:input type="Email" path="email" name="email" id="email" placeHolder="請輸入信箱"/>
	<p>生日：<form:input type="date" path="birthday" name="birthday" id="birthday"/>
	
	<p>性別：<form:select path="sex" id="sex">
	<option value="" selected>請選擇性別</option>
	<option value="男">男</option>
	<option value="女">女</option>
	</form:select>
	
	<c:if test="${param.message != null}">
	<p><font color="red"><c:out value="註冊失敗，請重新註冊"></font></c:out>
	</c:if>
	
	<p><input type="submit" value="送出註冊資料" >
	
	<p><h4>
			<a href="loginProccess"><已經有帳號了？點此立即登入></a>
		</h4>
	<p>	<h4>
			<a href="http://localhost:8080/SpringMVC_Hibernate/index.jsp"><返回首頁></a>
		</h4>
	</form:form>
</div>

</div>

</body>
</html>