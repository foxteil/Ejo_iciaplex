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
	${welcome }
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href="http://192.168.200.132/LogInform">로그인 하러 가기</a>
</body>
</html>
