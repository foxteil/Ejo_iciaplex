<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
</head>
<body onLoad="init()">
	<a href="http://192.168.200.132/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate">selectionDate</div>
		<div id="selectionTime">selectionTime</div>
	</section>
</body>
<script>
function init(){
	/* Convert Date */
	   let dateList = document.getElementById("selectionDate");
   let dayStr = "${Access}";
   let day = dayStr.split("-");
   let now = new Date(parseInt(day[0]), parseInt(day[1])-1,parseInt(day[2]));

   for(i=0;i<7;i++){
       let dateDiv = document.createElement('Div');
       let month = ((now.getMonth()+1)<10)? "0"+(now.getMonth()+1):(now.getMonth()+1);
       let date = (now.getDate()+i<10)? "0" + (now.getDate()+i):(now.getDate()+i);
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




function divClick(mvCode, date){
	//서버전송
	
 	var d = date.split("-");
 	var date = d[0]+d[1]+d[2];
	let request = new XMLHttpRequest();
	
	request.onreadystatechange = function(){
		if(this.readyState == 4 && this.status==200){
			
	       let jsonData = decodeURIComponent(request.response);
	       alert(jsonData);
	         
	         
	        let timeList = document.getElementById("selectionTime");
	        let time = JSON.parse(jsonData);
	     
	        for(let i=0; i<time.length; i++){

	        let mvScreen = document.createElement('Div');
	        mvScreen.textContent = time[i].mvSCREEN.substring(0,1)+"상영관";
	        mvScreen.className = "time";
	        timeList.appendChild(mvScreen);
	        	
	     	let mvGrade = document.createElement('Div');              
	     	mvGrade.textContent = ((time[i].mvGrade=="A")? "전체이용가" :
                									((time[i].mvGrade=="C")? "12세이용가" :
                    						((time[i].mvGrade=="Y")? "15세이용가" : ("청소년관람불가"))));
	     	mvGrade.className = "time";
	     	timeList.appendChild(mvGrade);

	     	let mvDATIME = document.createElement('Div');              
	     	mvDATIME.textContent = "상영시간: " + time[i].DATIME.substring(8,10)
	     		+((time[i].DATIME.substring(11,12)==0)? "시" : ":")
	     		+ ((time[i].DATIME.substring(11,12)==0)? "" :
	     			(time[i].DATIME.substring(10,12)));
	     	mvDATIME.className = "time";
	     	timeList.appendChild(mvDATIME);
	     	
	     	let mvScreen = document.createElement('Div');
	     	mvScreen.textContent
		}
		}
	};
	
	request.open("POST", "Step3", true);
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	request.send("iCode=j&sCode=S3&mvCode="+mvCode+"&mvDate="+date);

   
	}

</script>
</html>
