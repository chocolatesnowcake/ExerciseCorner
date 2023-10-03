<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>網站首頁</title>
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
	<form >
		<h1>網站首頁</h1>
		<h3>
			<a href="loginProccess"><已有會員？點此登入會員></a>
		</h3>
		<h3>
			<a href="registerProccess"><尚未註冊？點此註冊會員></a>
		</h3>
	</form>
</div>

</div>

</body>
</html>