<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="s" uri="/struts-tags"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料頁面</title>

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
.table{
	text-align:center;
	height: 100px;
	width: 1000px;
	padding:5px;
}
</style>


</head>
<body>

<div class="container">

<div class="vertical-center">
	<form>
		<table class="table">
			<thead style="background-color: #0000A8; color: #FFFFFF;">
				<tr>
					<th>代號</th>
					<th>帳號</th>
					<th>姓名</th>
					<th>信箱</th>
					<th>性別</th>
					<th>生日</th>
					<th>帳號更新時間</th>
					<th>帳號創建日期</th>
				</tr>
			</thead>
			
			<tbody style="background-color: #EBEBFF; color: #000000;">
			<tr>
				<td>${user.getUserId()}</td>
				<td>${user.getAccount()}</td>
				<td>${user.getName()}</td>
				<td>${user.getEmail()}</td>
				<td>${user.getSex()}</td>
				<td>${user.getBirthday()}</td>
				<td>${user.getLastModifiedDate()}</td>
				<td>${user.getCreatedDate()}</td>
			</tr>
			</tbody>
		</table>
	</form>
		<form action="ResetPasswordAction">
			<p><h4>
			<input type="hidden" name="account" value="${user.getAccount()}" />
				<button> 重設密碼 </button>
			</h4>
		</form>
		<form action="DeleteAction">
			<p><h4>
			<input type="hidden" name="userId" value="${user.getUserId()}" />
				<button> 刪除帳號 </button>
			</h4>
		</form>
		
		
	<p>
		<h4>
			<a href="http://localhost:8080/Struts2_SpringMVC_Hibernate/LoginAction"><帳號登出></a>
		</h4>

</body>
</html>