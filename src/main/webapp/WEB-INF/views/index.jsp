<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<!--  123, 166 Google API Key 입력후 테스트 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Spring Project</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" href="img/favicon.ico">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="/WEB-INF/views/include/header.jsp" %>
		<section id="index_section">
			<div class="card col-sm-12 mt-1" style="min-height: 850px;">
				<div class="card-body">
				<script>
				var markers = []
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){
					 $.get("${root}/house/sido"
						,function(data, status){
							$.each(data, function(index, sido) {
								$("#sido").append("<option value='"+sido.sidoCode+"'>"+sido.sidoName+"</option>");
							});//each
						}//function
						, "json"
					);//get 
				});//ready
				$(document).ready(function(){
					$("#sido").change(function() {
						$.get("${root}/house/gugun/"+$('#sido').val()
								,function(data, status){
									$("#gugun").empty();
									$("#gugun").append('<option value="0">선택</option>');
									$.each(data, function(index, vo) {
										$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
									});//each
								}//function
								, "json"
						);//get
					});//change
					$("#gugun").change(function() {
						$.get("${root}/house/dong/"+$('#gugun').val()
								,function(data, status){
									$("#dong").empty();
									$("#dong").append('<option value="0">선택</option>');
									$.each(data, function(index, vo) {
										$("#dong").append("<option value='"+vo.dong+"'>"+vo.dong+"</option>");
									});//each
								}//function
								, "json"
						);//get
					});//change
					$("#dong").change(function() {
						$.get("${root}/house/apt/"+$('#dong').val()
								,function(data, status){
									$("#searchResult").empty();
									$("tbody").empty();
									$.each(data, function(index, vo) {
										let str = "<tr class="+colorArr[index%3]+">"
										+ "<td>" + vo.no + "</td>"
										+ "<td>" + vo.dong + "</td>"
										+ "<td>" + vo.aptName + "</td>"
										+ "<td>" + vo.jibun + "</td>"
										+ "<td>" + vo.code
										+ "</td><td id='lat_"+index+"'>"+vo.lat+"</td><td id='lng_"+index+"'>"+vo.lng+"</td></tr>";
										$("tbody").append(str);
										$("#searchResult").append(vo.dong+" "+vo.aptName+" "+vo.jibun+"<br>");
									});//each
									geocode(data);
								}//function
								, "json"
						);//get
					});//change
				});//ready
				function geocode(jsonData) {
					deleteMarkers();
					
					let idx = 0;
					let multi = {lat: Number(jsonData[0]["lat"]), lng: Number(jsonData[0]["lng"])};
					map = new google.maps.Map(document.getElementById('map'), {
						center:multi,zoom: 14
					});
					console.log(jsonData[0])
					$.each(jsonData, function(index, vo) {
						let tmpLat;
						let tmpLng;
						$.get("https://maps.googleapis.com/maps/api/geocode/json"
								,{	key:'AIzaSyDFTi3EVs4csWeDSzY0MmeFooTaTFUnPeE'
									, address:vo.dong+"+"+vo.aptName+"+"+vo.jibun
								}
								, function(data, status) {
									//alert(data.results[0].geometry.location.lat);
									
									addMarker(vo.lat, vo.lng, vo.aptName);
								}
								, "json"
						);//get
					});//each
				}
				function setMapOnAll(map){
					for(var i = 0;i< markers.length;i++){
						markers[i].setMap();
					}
				}
				function clearMarkers(){
					setMapOnAll(null);
				}
				function deleteMarkers(){
					clearMarkers();
					markers=[];
				}
				</script>
				시도 : <select id="sido">
					<option value="0">선택</option>
				</select>
				구군 : <select id="gugun">
					<option value="0">선택</option>
				</select>
				읍면동 : <select id="dong">
					<option value="0">선택</option>
				</select>
				<table class="table mt-2">
					<thead>
						<tr>
							<th>번호</th>
							<th>법정동</th>
							<th>아파트이름</th>
							<th>지번</th>
							<th>지역코드</th>
							<th>위도</th>
							<th>경도</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>

				<div id="map" style="width: 100%; height: 500px; margin: auto;"></div>
				<script src="https://unpkg.com/@google/markerclustererplus@4.0.1/dist/markerclustererplus.min.js"></script>
				<script defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDFTi3EVs4csWeDSzY0MmeFooTaTFUnPeE&callback=initMap"></script>
				<script>
					var multi = {lat: 37.5665734, lng: 126.978179};
					var map;
					function initMap() {
						map = new google.maps.Map(document.getElementById('map'), {
							center: multi, zoom: 12
						});
						var marker = new google.maps.Marker({position: multi, map: map});
						markers.push(marker);
					}
					function addMarker(tmpLat, tmpLng, aptName) {
						var marker = new google.maps.Marker({
							position: new google.maps.LatLng(parseFloat(tmpLat),parseFloat(tmpLng)),
							label: aptName,
							title: aptName
						});
						marker.addListener('click', function() {
							map.setZoom(17);
							map.setCenter(marker.getPosition());
							callHouseDealInfo(aptName);
						});
						marker.setMap(map);
						markers.push(marker);
					}
					function callHouseDealInfo(aptName) {
						$.get("${root}/house/deal/"+aptName
								,function(data, status){
									console.log(data);
									alert("***** "+aptName+ "정보 *****\n\n주소 : "+data['dong']+"\n종류 : "+data["type"]+"\n평수 : "+data["area"]+"m^2\n매매가 : "+data["dealAmount"]+",000원");
								}//function
								, "json"
						);//get 
					}

				</script>
				</div>
			</div>
		</section>
	</div>
</body>
</html>