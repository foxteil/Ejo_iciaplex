<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>
</head>
<body>
<input type="text" name="memberInfo" placeholder="아이디를 입력해주세요"/>
<input type="password" name ="memberInfo" placeholder="비밀번호를 입력해주세여"/>
<input type="button" value="서버요청" onclick="moveLogIn()" >

</body>
<script>
function moveLogIn(){
	var memberInfo = document.getElementsByName("memberInfo");
	
	var form = document.createElement("form");
	form.action = "LogIn";
	form.method = "post";
	
	form.appendChild(memberInfo);
	
	document.body.appendChild(form);
	
	form.submit();
}

</script>
</html>