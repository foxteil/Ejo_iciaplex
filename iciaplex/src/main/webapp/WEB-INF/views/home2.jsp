<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>


<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
<style>
* {box-sizing: border-box;}
body {font-family: Verdana, sans-serif;}
.mySlides, .mySlides2, .mySlides3 {display: none;}
img {vertical-align: middle;}

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
.dot, .dot2, .dot3 {
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
</style>
	
</head>
<body>
 

<p> ${movieL }</p>




<a href="http://192.168.0.107/LogInform">로그인 하러 가기</a>

<script type="text/javascript">
var slideIndex = 0;
showSlides();

function showSlides() {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var slides2 = document.getElementsByClassName("mySlides2");
  var slides3 = document.getElementsByClassName("mySlides3");
  var dots = document.getElementsByClassName("dot");
  var dots2 = document.getElementsByClassName("dot2");
  var dots3 = document.getElementsByClassName("dot3");
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
    slides2[i].style.display = "none";
    slides3[i].style.display = "none";
  }
  slideIndex++;
  if (slideIndex > slides.length) {slideIndex = 1}    
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
    dots2[i].className = dots2[i].className.replace(" active", "");
    dots3[i].className = dots3[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  slides2[slideIndex-1].style.display = "block";
  slides3[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
  dots2[slideIndex-1].className += " active";
  dots3[slideIndex-1].className += " active";
  setTimeout(showSlides, 2000); // Change image every 2 seconds
}

	
function movieClick(mCode){
	var form = document.createElement("form");
	form.action = "Step2?mvCode="+mCode;
	form.method = "post";
	document.body.appendChild(form);
	
	form.submit();
}
</script>
</body>


</html>
