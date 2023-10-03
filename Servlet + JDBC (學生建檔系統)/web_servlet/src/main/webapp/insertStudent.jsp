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

<form method="post" action="InsertStudentServlet">
請輸入要產生學生資料的筆數: <input type="number" name="insertNum" placeholder="輸入數字">
<p><button type="submit" >Submit</button>
</form>

<font color="green"><c:out value="${requestScope.success}"/></font>
<p><font color="red"><c:out value="${requestScope.fail}"/></font>

<p><c:if test="${requestScope.success != null}">
<form action="ShowStudentServlet" >
<button type="submit" >${requestScope.resultButton}</button>
</form>
</c:if>

</body>
</html>