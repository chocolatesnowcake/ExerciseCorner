<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>刪除帳號頁面</title>

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
	<h1>刪除帳號頁面</h1>
	
	<form action="DeleteAction">
		<p><h2 ><font color="red">帳號一經刪除，將無法回復</font></h2>
		<p><h3> <font color ="red">是否確認刪除？</font></h3>
		
		<input type="hidden" name="userId" value="${userId}" />
		帳號：<input type="text" name="account" placeHolder="請再次輸入帳號" />
		
		<s:if test="message == 'success'">
		<p><font color="green">帳號已刪除成功</font>
		</s:if>
		<s:if test="message == 'error'">
		<p><font color="red">帳號刪除失敗，請確認帳號是否輸入錯誤</font>
		</s:if>
		
		<p><button>是</button>
	</form>
	
	
	<p>	<h4>
	<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/LoginAction"><點此返回登入頁面></a>
	</h4>
</div>

</div>

</body>
</html>