<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>
</head>
<body>
<input type="text" name="mId" placeholder="아이디를 입력해주세요"/>
<input type="password" name ="mPwd" placeholder="비밀번호를 입력해주세여"/>
<input type="button" value="서버요청" onclick="moveLogIn()" >
<br />
${member.getMId() }<br />
${member.getMName() }<br />
${member.getMPhone() }<br />


</body>
<script>
function moveLogIn(){
	var mId = document.getElementsByName("mId")[0];
	var mPwd = document.getElementsByName("mPwd")[0];
	
	var form = document.createElement("form");
	form.action = "LogIn?ServiceCode=A";
	form.method = "post";
	
	form.appendChild(mId);
	form.appendChild(mPwd);
	
	document.body.appendChild(form);
	
	form.submit();
}

</script>
</html>