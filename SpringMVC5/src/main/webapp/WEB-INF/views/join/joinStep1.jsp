<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<!-- 
		이벤트 종류
	    ==========================================================
	    blur        focus       focusin     focusout    load
	    resize      scroll      unload      click       dblclick
	    mousedown   mouseup     mousemove   mouseover   mouseout
	    mouseenter  mouseleave  change      select      submit
	    keydown     keypress    keyup       error       ready
	    ==========================================================
	 -->
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<style>
		body {
			background-color: #b3ffff;
		}
		h2 {
			font-size: 2em;
			color: #ff3300;
		}
		form label {
			font-size: 2.2em;
			font-family: sans-serif;
			color: #002699;
		}
		form > p > input {
			height: 30px;
			margin: 20px;
			width: 300px;
			font-size: 25px;
		}
		form > input[type=submit] {
			margin: 20px;
			width: 80px;
			height:	40px;
		}
	</style>
	<title>회원가입</title>
	
</head>
<body>
	<script>
		if("${message}"){
			alert("${message}");
		}
	</script>
	<h2>회원 정보 입력</h2>
	<form action="joinStep2" method="post"><!-- joinStep1로 파라미터가 넘어간다 -->
		<p>
			<label>ID:</label><br/>
			<input type="text" name="id" id="id"/>
		</p>
		<p>
			<label>PASSWORD:</label><br/>
			<input type="password" name="password" id="password"/>
		</p>
		<p>
			<label>CONFIRM PASSWORD:</label><br/>
			<input type="password" name="confirmPassword" id="confirmPassword"/>
		</p>
		<p>
			<label>NAME:</label><br/>
			<input type="text" name="name" id="name"/>
		</p>
		<p>
			<label>전화번호: XXX-XXXX-XXXX의 형태로 입력하세요.</label><br/>
			<input type="text" name="phone" id="phone"/>
		</p>
		
		<!-- location 정보  -->
		<input type="hidden" name="location" id="location" value=""/>
		<!-- address 정보  -->
		<input type="hidden" name="address" id="address" value=""/>
		
		
		<input type="submit" value="확인"/>
		
	</form>
	
	
	
	<!-- 지도  -->
	<div id="map" style="width:80%;height:600px;"></div><br/>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db87e9828f2b6a261357dbc4ce9d4cfb&libraries=services"></script>
	
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  

		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		var marker = new kakao.maps.Marker({
			position: map.getCenter()
		}); // 클릭한 위치를 표시할 마커입니다
		
		
		marker.setMap(map);
		
		// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
		// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
		    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
		        if (status === kakao.maps.services.Status.OK) {
		        	
		        	//querySelector
				    var location=document.querySelector("form > input[name=\"location\"]");
				    location.setAttribute("value", ""+mouseEvent.latLng.getLat()+", "+mouseEvent.latLng.getLng());
		        	
		        	var addr=document.querySelector("form > input[name=\"address\"]");
				    addr.setAttribute("value", ""+result[0].address.address_name);

		            // 마커를 클릭한 위치에 표시합니다 
		            marker.setPosition(mouseEvent.latLng);
		            marker.setMap(map);

		        }   
		    });
		});
		
		function searchDetailAddrFromCoords(coords, callback) {
		    // 좌표로 법정동 상세 주소 정보를 요청합니다
		    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}
	</script>
</body>
</html>