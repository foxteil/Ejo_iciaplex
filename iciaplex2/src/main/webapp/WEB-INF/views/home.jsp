<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	


<style>
* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;
}
.mySlides{display: none;
text-align:center;}
img {
margin : auto;
width : 300px;
height : 500px;

}


/* Slideshow container */
.slideshow-container {
  max-width: 1000px;
  position: relative;
  margin: auto;

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
#movieZone{
   position: relative;
    display: flex;
    flex-wrap : wrap;
    justify-content : center;
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



footer {
    padding : 80px 0 0;
    text-align: center;
}


footer .footerLogo {
    font-size: 40px;
    font-weight: 700;
    letter-spacing: -5px;
    color: #39312b;
    margin : 40px 10 20px;
}

footer ul li {
    font-size: 17px;
    line-height: 1.4;
     color: #7e7e7e;
}

.comment{
    background: #000000;
    opacity: 0;
    width: 300px;
    height: 500px;
    position: absolute;
    bottom: -2%;
    z-index: 1;
}

.poster:hover .comment{
opacity:0.5;
text-align:center;
color:#ffffff;
}

</style>
</head>
<body>



	<a href="http://192.168.200.132/LoginForm" >로그인폼 이동</a><br />
	<P>  ${movieL } </P>
	
	<section id="movieZone"></section>
            <section>
            <footer>
                <h1 class="footerLogo">iciaplex</h1>
                	<ul style="list-style-type:none;">
                    <li>대표자 이승호 | 인천광역시 미추홀구 학익동</li>
                    <li>사업자등록번호:123-12-12345 | 이메일:abc@naver.com</li>
                    <li><span class="copyright">COPYRIGHT 2021. All Rights Reserved.</span></li>
               </ul>
            </footer>
</section>
 
</body>

			
            
            
<script> 
	let dayStr = "${Access}";
	let day = (dayStr.split(" "))[0].split("-");
	let now = new Date();
	
	now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	//now.setDate(now.getDate()+30);
	
	
	let section = document.getElementById("movieZone");
	let movieList = JSON.parse('${jsonData}');
		
	let record = parseInt(movieList.length/5);
	record = (movieList.length%5 > 0)? record + 1: record;
	for(rIndex=0; rIndex < record; rIndex++){
		let div = document.createElement('Div');
		div.style.display = "flex";


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

function divClick(mvCode){
	   //서버전송
	   var form = document.createElement("form");
	   form.action = "Step2?mvCode="+mvCode+"&iCode=j";
	   form.method = "post";
	   document.body.appendChild(form);
	   
	   form.submit();
	   
	}

</script>
             
</html>
