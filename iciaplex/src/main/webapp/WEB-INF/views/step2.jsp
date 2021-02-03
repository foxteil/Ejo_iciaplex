<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>

</head>
<body onLoad="init()">
	<a href="http://192.168.0.107/LoginForm" >로그인폼 이동</a><br />
	
	<P>  Now Time : ${Access} </P>
	
	
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate"><h2>selectionDate</h2></div>
		<div id="selectionTime">selectionTime</div>
	</section>
</body>
<script>
function init(){
		let dateList = document.getElementById("selectionDate");
		let dayStr = "${Access}";//2021-02-03
		let day = dayStr.split("-");
		let now = new Date();
				
		for(i=0;i<7;i++){
			let dateDiv = document.createElement('Div');
			let month = ((now.getMonth())<10)? "0"+(now.getMonth()+1):(now.getMonth()+1);
			let date = (now.getDate() <10)? "0" + (now.getDate()+i):(now.getDate()+i);
			dateDiv.textContent = now.getFullYear()+"-"+month+"-"+date;
			dateDiv.style.cursor="pointer";
			dateDiv.addEventListener('click',function(){divClick(
				movie[0].mvCode,this.textContent);});
			dateList.appendChild(dateDiv);
		}
		

		
	let movieInfo = document.getElementById("movieInfo");
	/* Append movieInfo Div */
	let movie = JSON.parse('${movieData}');
	
	let mvImage = document.createElement('Div');
	mvImage.style.width = "150px";
	mvImage.style.height = "300px";
	mvImage.style.margin = "0px 10px 20px 0px";
	mvImage.style.backgroundImage = "url(/resources/img/" + movie[0].mvImage + ")";
	mvImage.style.backgroundSize = "contain";
	movieInfo.appendChild(mvImage);
	
	let mvTitle = document.createElement('Div');              
	mvTitle.textContent = movie[0].mvName;
	mvImage.className = "movie";
	movieInfo.appendChild(mvTitle);
	
	let mvGrade = document.createElement('Div');              
	mvGrade.textContent = movie[0].mvGrade;
	mvGrade.className = "movie";
	movieInfo.appendChild(mvGrade);
	
	let mvStatus = document.createElement('Div');              
	mvStatus.textContent = movie[0].mvStatus;
	mvStatus.className = "movie";
	movieInfo.appendChild(mvStatus);
	
	let mvComments = document.createElement('Div');              
	mvComments.textContent = movie[0].mvComments;
	mvComments.className = "movie";
	movieInfo.appendChild(mvComments);
}

function divClick(mvCode,day){
	//서버전송
	
	
	alert(mvCode +":"+ day);
// 	let form = document.createElement("form");
// 	form.action = "Step2?sCode=Step2&mvCode=" + mvCode;
// 	form.method = "post"
	
// 	document.body.appendChild(form);
// 	form.submit();
}
	
</script>
</html>
