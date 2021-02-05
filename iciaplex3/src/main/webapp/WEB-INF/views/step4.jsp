<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<a href="http://192.168.200.132/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	<section id="seatZone">좌석 선택 페이지입니다.</section>
</body>
<script type="text/javascript">
let seatInfo = ${SeatInfo };
let seat = JSON.stringify(seatInfo);
console.log(seat);
</script>
</html>