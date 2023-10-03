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
	<form action="ResetPasswordAction" >
			<h1>重設密碼頁面</h1>
			
			新密碼：<input type="text" name="password" placeHolder="請輸入欲更新密碼" />
			
			<s:if test="message == 'success'">
			<p><font color="green">密碼更新成功，請重新登入</font>
			</s:if>
			<s:if test="message == 'error'">
			<p><font color="red">密碼更新失敗，請再次輸入</font>
			</s:if>

			<input type="hidden" name="account" value="${account}" />
			<p><button>更新密碼</button>
	</form>
		
		<p>	<h4>
		<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/LoginAction"><更新密碼了嗎?點此重新登入></a>
	</h4>
</div>

</div>

</body>
</html>