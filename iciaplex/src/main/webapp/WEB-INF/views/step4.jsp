<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.text.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.movie{
	width: 150px;
	height: 30px;
	margin: 5px 5px 5px 5px;
	background-size: contain;
	}
	
.row{ display: flex;}

.able{
	width: 4%;
	height: 30px;
	background-color: #4374d9;
	border: 1px solid #ffffff;
	cursor: pointer;
	}

.hall{
	width : 4%;
	height: 30px;
	float: left;
	background-color: #ffffff;
	border: 1px solid #ffffff;
	cursor: default;
	}

.repair{
	width:4%;
	height:30px;
	float:left;
	background-color: #bdbdbd;
	border: 1px solid #ffffff;
	cursor: default;
	}
	
.reserve{
	width: 4%;
	height: 30px;
	float: left;
	background-color: #ffbb00;
	border: 1px solid #ffffff;
	cursor: default;
	}

</style>

</head>
<body onLoad="init()">
now Time : ${Access }
<section id="seatZone"></section>

</body>
<script>
function init(){
	let sZone = document.getElementById("seatZone");
	let seatInfo = JSON.parse('${SeatInfo}');

	
	for(rowIndex = 0; rowIndex < 20; rowIndex++){
		let rows = document.createElement("div");
		rows.className="row";
		for(colIndex=0; colIndex<20; colIndex++){

			let cols=document.createElement("div");
			cols.setAttribute("name","seat");
			cols.className ="able";
					
			rows.appendChild(cols);
		}
		sZone.appendChild(rows);
	}

	/*Inactive Seat 배치*/
	let seat = document.getElementsByName("seat");

	for(index=0;index<seatInfo.length;index++){
		seat[seatInfo[index].seatNum-1].className=(seatInfo[index].seatType == "H")? "hall" : 
			((seatInfo[index].seatType =="R  ")? "reserve":"repair");
	}

	/*Active Seat에 Event 추가*/
	let rowChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	let cnt = -1;
	for(index =0;index<seat.length;index++){
		if(seat[index].className !="hall"){
			cnt++;
			let first = rowChar.substr(parseInt(cnt/10),1);
				let second = (cnt%10)+1;
				if(seat[index].className == "able"){
					seat[index].setAttribute("onClick","selectSeat("+(index+1)+",'"+first+second+"')");
			}
		}
	}
}
function selectSeat(sNumber,cNumber){
	let type = prompt("성인:A | 청소년:J | 어린이:C");
	alert(sNumber+":"+cNumber+":"+type);
}
</script>
</html>