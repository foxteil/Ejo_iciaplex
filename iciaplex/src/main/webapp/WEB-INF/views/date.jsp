<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Date</title>
<style>
box-sizing: border-box;
.test{
	display: block;
	
	margin: auto;
}



</style>
</head>
<body>

${poster }



</body>
<script>
function DateClick(date){
	
	var dates = date.value.split(":");
	
	var mvcode= document.getElementsByName("mvcode")[0].value;
	var date = dates[0];

	var form = document.createElement("form");
	form.action = "Step3?sCode=S2&iCode=b&mvCode="+mvcode+"&mvDate="+date;
	form.method = "post";
	document.body.appendChild(form);
	
	form.submit();
	
	
}

</script>




</html>