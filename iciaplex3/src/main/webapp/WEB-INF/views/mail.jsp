<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Send Mail</title>
</head>
<body>
	<table>
	<tr>
	<td>Receiver</td>
	<td><input type="text" name="mailReceiver"/></td>
	</tr>
	<tr>
	<td>Subject</td>
	<td><input type="text" name="mailSubject"/></td>
	</tr>
	<tr>
	<td>Content</td>
	<td colspan="2"><textarea name="mailContent"></textarea></td>
	</tr>
	</table>
	<input type="button" value="send" onClick="sendMail()"/>
</body>

<script>
 function sendMail(){
	 let receiver = document.getElementsByName("mailReceiver")[0];
	 let subject = document.getElementsByName("mailSubject")[0];
	 let content = document.getElementsByName("mailContent")[0];
	 
	 let form = document.createElement("form");
     form.action = "SendMail";
     form.method = "post";
     
     form.appendChild(receiver);
     form.appendChild(subject);
     form.appendChild(content);
     
     document.body.appendChild(form);
     
     form.submit();
     

 }
</script>
</html>