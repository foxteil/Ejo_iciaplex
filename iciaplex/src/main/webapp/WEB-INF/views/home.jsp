<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<style>
* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;}
.mySlides {display: none;
 text-align:center;
			
			}
img {vertical-align: middle;
	cursor:pointer;
	width: 300px;
	height: 500px;
	
	}

/* Slideshow container */
.slideshow-container {
  max-width: 1000px;

  
}

/* Caption text */
.text {
  color: #f2f2f2;
  font-size: 15px;
  padding: 8px 12px;
  position: absolute;
  bottom: 8px;
  width: 100%;
  text-align: center;
}

/* Number text (1/3 etc) */
.numbertext {
  color: #f2f2f2;
  font-size: 12px;
  padding: 8px 12px;
  position: absolute;
  top: 0;
}

/* The dots/bullets/indicators */
.dot{
  height: 15px;
  width: 15px;
  margin: 0 2px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}

.active {
  background-color: #717171;
}

/* Fading animation */
.fade {
  -webkit-animation-name: fade;
  -webkit-animation-duration: 1.5s;
  animation-name: fade;
  animation-duration: 1.5s;
}

@-webkit-keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

@keyframes fade {
  from {opacity: .4} 
  to {opacity: 1}
}

/* On smaller screens, decrease text size */
@media only screen and (max-width: 300px) {
  .text {font-size: 11px}
}
.comment{
    background: #000000;
    opacity: 0;
    width: 300px;
    height: 500px;
    position: relative;
    bottom: -2%;
    z-index: 1;
}

.poster:hover .comment{
opacity:0.5;
text-align:center;
color:#ffffff;
}
#movieZone{
   position: absolute;
    left: 16%;
}

</style>
</head>
<body>

	<a href="http://192.168.35.195/LogInform" >로그인폼 이동</a><br />
	
	<p> ${movieL }</p>
	<p>now time ${Access }</p>
	<section id="movieZone"></section>
</body>
<script> 
	let dayStr = "${Access}";
	
	let day = (dayStr.split(" "))[0].split("-");
	let now = new Date();
	now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	let section = document.getElementById("movieZone");
	let movieList = JSON.parse('${jsonData}');
	let record = parseInt(movieList.length/5);
	record = (movieList.length%5 > 0)? record + 1: record;
	for(rIndex=0; rIndex < record; rIndex++){
		let div = document.createElement('Div');
		div.style.display = "inline-flex";
		div.setAttribute("name", "line");
		section.appendChild(div);
	}

	for(index=0; index < movieList.length; index++){
		let rDivIndex = parseInt(index/5);
		let mvDiv = document.createElement('Div');
		mvDiv.style.width = "140px";
		mvDiv.style.height = "200px";
		mvDiv.style.margin = "0px 10px 20px 0px";  
											
		mvDiv.style.backgroundImage = "url(/resources/img/" + movieList[index].mvImage + ")"
		mvDiv.style.backgroundSize = "contain";
		mvDiv.style.cursor = "pointer";
		let mvCode = movieList[index].mvCode;
		mvDiv.addEventListener('click', function(){divClick(mvCode);});
		let line = document.getElementsByName("line")[rDivIndex];
		line.appendChild(mvDiv);
		
	}
	
function divClick(mvCode){
	//서버전송
	var form = document.createElement("form");
	form.action = "Step2?mvCode="+mvCode+"&iCode=j";
	form.method = "post";
	document.body.appendChild(form);
	
	form.submit();
	
}


var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");

  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  slideIndex++;
  
  if (slideIndex > slides.length) {slideIndex = 1}    
  slides[slideIndex-1].style.display = "block";  
  setTimeout(showSlides, 2000); // Change image every 2 seconds
}

	
function movieClick(mCode){
	var form = document.createElement("form");
	form.action = "Step2?mvCode="+mCode+"&iCode=b";
	form.method = "post";
	document.body.appendChild(form);
	
	form.submit();
}
	
</script>
</html>
