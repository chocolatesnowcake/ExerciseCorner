<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>會員資料頁面</title>

<style>

.border { 
border:3px Black solid; 
padding:5px;
}

</style>

</head>
<body>

<div>
<center>
<table class="border">
		<tr>
			<th>使用者ID</th>
			<th>帳號</th>
			<th>姓名</th>
			<th>信箱</th>
			<th>生日</th>
			<th>性別</th>
		</tr>
		<tr>
			<td style="height:150px;width:200px;" align='center' valign="middle">${user.userId}</td>
			<td style="height:150px;width:200px;" align='center' valign="middle">${user.account}</td>
			<td style="height:150px;width:200px;" align='center' valign="middle">${user.name}</td>
			<td style="height:150px;width:200px;" align='center' valign="middle">${user.email}</td>
			<td style="height:150px;width:200px;" align='center' valign="middle">${user.birthday}</td>
			<td style="height:150px;width:200px;" align='center' valign="middle">${user.sex}</td>	
		</tr>
	
	</table>
	<p><a href="http://localhost:8080/springmvc_JDBC/index.jsp"><返回首頁></a>
</center>

</div>
	

</body>
</html>