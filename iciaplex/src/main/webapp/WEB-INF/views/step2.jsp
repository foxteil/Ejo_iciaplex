<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>

</head>
<body onLoad="init()">
	<a href="http://192.168.35.195/LoginForm" >로그인폼 이동</a><br />
	
	<P>  Now Time : ${Access} </P>
	
	
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate"><h2>selectionDate</h2></div>
		<div id="selectionTime"><h2>selectionTime</h2></div>
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
			let date = (now.getDate()+i <10)? "0" + (now.getDate()+i):(now.getDate()+i);
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

function divClick(mvCode,date){
	//서버전송
	let dat = date.split("-");
	let mvDate = dat[0]+dat[1]+dat[2];
	
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if(this.readyState == 4 && this.status ==200){
			let jsonData = decodeURIComponent(request.response);

			screening(jsonData);
			
		}
	};
	
	request.open("POST", "Step3", true);
	request.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
	request.send("iCode=j&sCode=S3&mvCode="+mvCode+"&mvDate="+mvDate);
}
function screening(jsonData){
	
	let timeList = document.getElementById("selectionTime");
	let screen = JSON.parse(jsonData);
	
	for(let i=0;i<screen.length;i++){
	
		let Gr =((screen[i].mvGrade == "A" )? "전체이용가" : 
				 ((screen[i].mvGrade == "C" )? "12세이용가" : 
				  ((screen[i].mvGrade == "Y" )? "15세이용가" : "청소년관람불가")));
	
		let Ti = screen[i].DATIME.substring(8,10)
				+ ((screen[i].DATIME.substring(11,12)==0)? "시" : ":")
				 + ((screen[i].DATIME.substring(11,12)==0)? " " : screen[i].DATIME.substring(10,12));
		
		let scData = screen[i].mvCode +":"
			+ screen[i].mvSCREEN+":"
			+ screen[i].DATIME;
		//mvcode /scnumber ::Screen / DATIME
		
		
	let mvScreen = document.createElement('Div');
		mvScreen.textContent = Gr+" ["+screen[i].mvSCREEN+" 상영관] Begin>"+Ti;
		mvScreen.style.cursor="pointer";
		mvScreen.addEventListener('click',function(){send(scData);});
		timeList.appendChild(mvScreen);
	}
	
}
function send(scData){
	alert(scData);
	let step2 = scData.split(":");
	
	var form = document.createElement("form");
	form.action = "Step4?sCode=S4&mvCode="+step2[0]+"&mvSCREEN="+step2[1]+"&mvDate="+step2[2]+"&iCode=q";
	form.method = "post";
	document.body.appendChild(form);
	
	form.submit();
	
}
</script>
</html>
