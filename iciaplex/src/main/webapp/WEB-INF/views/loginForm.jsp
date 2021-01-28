<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" name="mId" placeholder="아이디를 입력해주세요" />
<input type="password" name="mPwd" placeholder="비밀번호를 입력해주세요 " />
<input type="button" value="서버 요청" onClick="moveLoginForm()" />


	${mId }<br />
	${mPwd }<br />
	${memberId }<br />
	${memberPwd }
</body>
<script>
function moveLoginForm(){
	var mId = document.getElementsByName("mId")[0];
	var mPwd = document.getElementsByName("mPwd")[0];
	
	var form = document.createElement("form");
	form.action= "Login?memberInfo="+mId.value+"&memberInfo="+mPwd.value;
	form.method = "post";
	
	form.appendChild(mId);
	form.appendChild(mPwd);
	
	document.body.appendChild(form);
	
	form.submit();
	
}

</script>
</html>