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
	alert("param"+date);
	var dates = date.value.split(":");
	
	var mvcode= document.getElementsByName("mvcode")[0].value;
	var date = dates[0];
	alert("date"+date);
	divClick(mvcode,date);

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
			alert(jsonData);
			
		}
	};
	
	request.open("POST", "Step3a", true);
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