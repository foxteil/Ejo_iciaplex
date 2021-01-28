<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	${welcome}

	
</h1>

<P>  The time on the server is ${serverTime}. </P>
<!-- <input type="button" value="로그인" onClick="moveLoginForm()"/> -->
<a href="http://192.168.219.101/LoginForm">로그인 폼으로 이동</a>
<!-- <a href="LoginForm">로그인 폼으로 이동</a> -->
</body>
<script>
	function moveLoginForm(){
		var form = document.createElement("form");
		//form.action = "http://192.168.219.101/LoginForm";
		form.action = "LoginForm";
		form.method = "post";
		
		
		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>
