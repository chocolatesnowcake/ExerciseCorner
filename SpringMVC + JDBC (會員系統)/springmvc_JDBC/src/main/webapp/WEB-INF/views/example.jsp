<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>ajax</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script>

        function username(){
            $.post({
                url:"${pageContext.request.contextPath}/user/argcheck",
                data:{'name':$("#name").val()},
                success:function (data) {
                    if (data.toString()=='OK'){
                    	document.getElementById("emailcheck").innerHTML = "<font color='red'>该邮箱已经被注册</font>";
                    }else {
                        $("#userInfo").css("color","red");
                    }
                    $("#userInfo").html(data);
                }
            });
        }
        function password(){
            $.post({
                url:"${pageContext.request.contextPath}/user/argcheck",
                data:{'pwd':$("#pwd").val()},
                success:function (data) {
                    if (data.toString()=='OK'){
                        $("#pwdInfo").css("color","green");
                    }else {
                        $("#pwdInfo").css("color","red");
                    }
                    $("#pwdInfo").html(data);
                }
            });
        }

    </script>
</head>
<body>
<p>
    用户名:<input type="text" id="name" onblur="username()"/>
    <span id="userInfo"></span>
</p>
<p>
    密码:<input type="text" id="pwd" onblur="password()"/>
    <span id="pwdInfo"></span>
</p>
</body>
</html>

<!-- 
index.jsp 透過帶入連結 連到userRegister.jsp，然後找尋到userRegister名字的jsp檔後顯示並返回。
https://w3c.hexschool.com/blog/38e2a588  檢查使用者的帳號
https://www.ethereal.vip/article/1
 -->