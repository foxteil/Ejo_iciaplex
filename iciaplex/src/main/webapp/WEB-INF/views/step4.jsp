<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
now Time : ${Access }
</body>
<SCRIPT>
let seatInfo = JSON.parse('${SeatInfo}');
alert(seatInfo);

</SCRIPT>
</html>