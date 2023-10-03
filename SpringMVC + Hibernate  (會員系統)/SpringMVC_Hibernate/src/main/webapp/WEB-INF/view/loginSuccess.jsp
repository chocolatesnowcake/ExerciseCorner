<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員資料</title>

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
<%
request.setCharacterEncoding("UTF-8");
String name = request.getParameter("user");
%>

<div class="container">

<div class="vertical-center">
	<form:form action="../delete/user" modelAttribute="user" method="post">
		<table class="table">
			<thead style="background-color: #0000A8; color: #FFFFFF;">
				<tr>
					<th>代號</th>
					<th>帳號</th>
					<th>姓名</th>
					<th>信箱</th>
					<th>性別</th>
					<th>生日</th>
					<th>帳號創建日期</th>
				</tr>
			</thead>
			
			<tbody style="background-color: #EBEBFF; color: #000000;">
			<tr>
				<td><c:out value="${user.userId}" /></td>
				<td><c:out value="${user.account}" /></td>
				<td><c:out value="${user.name}" /></td>
				<td><c:out value="${user.email}" /></td>
				<td><c:out value="${user.sex}" /></td>
				<td><c:out value="${user.birthday}" /></td>
				<td><c:out value="${user.createdDate}" />
				</td>
			</tr>
			</tbody>
		</table>
		<p><form:input type="hidden" path="userId" value="${user.userId}" />
		<p><button>刪除帳號</button>
	</form:form>
	
		<p>	<h4>
		<a href="http://localhost:8080/SpringMVC_Hibernate/loginProccess"><會員登出></a>
	</h4>
		<p>	<h4>
		<a href="http://localhost:8080/SpringMVC_Hibernate/index.jsp"><返回首頁></a>
	</h4>
</div>

</div>

</body>
</html>

<!-- form表單的路徑跳轉 根目錄說明 參考: https://blog.csdn.net/a450590464/article/details/43052177 -->