<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>註冊會員</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script >

        function userAccount(){
            $.post({
                url:"${pageContext.request.contextPath}/CheckAccountAction",
                data:{'account':$("#account").val()},
                success:function (data) {
                    if (data.message=='OK'){
                        $("#accountInfo").css("color","green");
                    }else {
                        $("#accountInfo").css("color","red");
                    }
                    $("#accountInfo").html(data.message);
                }
            });
        }
        
        function userPassword(){
        	var pwd = document.getElementById("password").value;
        	if((pwd == null) || (pwd == "")){
        		$("#password").css("color","red");
        	}
        	$("#passwordInfo").html("密碼為必填欄位，請輸入密碼");
        }
	<!-- 密碼欄位判斷參考: https://cloud.tencent.com/developer/article/1837911 -->
    </script>

<style>
span{color:red;}
html, body{height:100%;}
.container { 
	height: 100%;
	position: relative;
	background-color: #EB9900;
}
.vertical-center {
	background-color: #FFDEA1;
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
	<h1>會員註冊頁面</h1>

	<form action="RegisterAction" >
	<h3>帳號密碼設定</h3>
	
	<span>*</span>帳號：<input type="text" name="registerParam.account" id="account" onblur="userAccount()" placeHolder="請輸入帳號"/> 
	<p><span id="accountInfo"></span>
	
	<p><span>*</span>密碼：<input type="text" name="registerParam.password" id="password" onblur="userPassword()" placeHolder="請輸入密碼"/> 
	<p><span id="passwordInfo"></span>
	
	<h3>會員資料填寫</h3>
	
	<p>姓名：<input type="text" name="registerParam.name" placeHolder="請輸入姓名"/>
	<p>信箱：<input type="Email" name="registerParam.email" placeHolder="請輸入信箱"/>
	<p>生日：<input type="date" name="registerParam.birthday" />
	
	<p>性別：<select name="registerParam.sex">
	<option value="" selected>請選擇性別</option>
	<option value="男">男</option>
	<option value="女">女</option>
	</select>
	
	<s:if test="message == 'wrong'">
		<p><font color="red">帳號註冊失敗，請再次輸入資料</font>
	</s:if>
	
	<p><input type="submit" value="送出註冊資料" >

	</form>
	
		<p><h4>
			<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/LoginAction"><已經有帳號了？點此立即登入></a>
		</h4>
	
</div>

</div>

</body>
</html>