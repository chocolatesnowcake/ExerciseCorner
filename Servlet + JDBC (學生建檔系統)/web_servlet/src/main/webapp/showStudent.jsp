<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table>
<thead>
	 <tr>
	    <th>學號</th>
	    <th>姓名</th>
	    <th>生日</th>
	    <th>性別</th>
	    <th>信箱</th>
	    <th>密碼</th>
	  </tr>
	  </thead>
	  <c:forEach var = "student" items = "${requestScope.studentList}">
		  <tr>
		    <td>${student.sno}</td>
		    <td>${student.sname}</td>
		    <td>${student.sbday}</td>
		    <td>${student.ssex}</td>
		    <td>${student.smail}</td>
		    <td>${student.spwd}</td>
		  </tr>
	  </c:forEach >
</table>

</body>
</html>