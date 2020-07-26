<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>게시물 변경</title><!-- 날짜는 변경할 수 없음 -->
	<style>
		label {
			font-size: 2.5em;
			color: #0000cc;
		}
		input, select {
			font-size: 2.5em;
			color: #4d004d;
		}
		textarea {
			font-size: 30px;
			color: #4d004d;
			width: 70%;
			height: 500px;
			border: 5px solid #e62e00;
		}
		body {
			background-color: #b3ffff;
		}
	</style>
</head>
<body>
	<form action="modifySellStep2" method="post" id="form">
		<p>
			<label>제목: </label>
			<input type="text" name="title" id="title" value="${sellInfo.title}"/>
		</p>
		
		<input type="hidden" name="id" id="id" value="${sellInfo.id}"/>
		
		
		<input type="hidden" name="location" id="location" value="${sellInfo.location}"/>
		<input type="hidden" name="address" id="address" value="${sellInfo.address}" />
		
		<label> 화폐 단위:  </label>
			<select name="unit" id="unit"><%--value 속성으로 EL식을 통해 값을 지정할 수도 있다 --%>
				<option value="대한민국 KRW">대한민국 KRW</option>
				<option value="남아프리카 공화국 ZAR">남아프리카 공화국 ZAR</option>
				<option value="네팔 NPR">네팔 NPR</option>
				<option value="노르웨이 NOK">노르웨이 NOK</option>
				<option value="뉴질랜드 NZD">뉴질랜드 NZD</option>
				<option value="대만 TWD">대만 TWD</option>
				<option value="덴마크 DKK">덴마크 DKK</option>
				<option value="러시아 RUB">러시아 RUB</option>
				<option value="마카오 MOP">마카오 MOP</option>
				<option value="말레이시아 MYR">말레이시아 MYR</option>
				<option value="멕시코 MXN">멕시코 MXN</option>
				<option value="미국 USD">미국 USD</option>
				<option value="바레인 BHD">바레인 BHD</option>
				<option value="방글라데시 BDT">방글라데시 BDT</option>
				<option value="베트남 VND">베트남 VND</option>
				<option value="브라질 BRL">브라질 BRL</option>
				<option value="브루나이 BND">브루나이 BND</option>
				<option value="사우디아라비아 SAR">사우디아라비아 SAR</option>
				<option value="스웨덴 SEK">스웨덴 SEK</option>
				<option value="스위스 CHF">스위스 CHF</option>
				<option value="싱가포르 SGD">싱가포르 SGD</option>
				<option value="아랍에미리트 AED">아랍에미리트 AED</option>
				<option value="영국 GBP">영국 GBP</option>
				<option value="오만 OMR">오만 OMR</option>
				<option value="요르단 JOD">요르단 JOD</option>
				<option value="유럽연합 EUR">유럽연합 EUR</option>
				<option value="이스라엘 ILS">이스라엘 ILS</option>
				<option value="이집트 EGP">이집트 EGP</option>
				<option value="인도 INR">인도 INR</option>
				<option value="인도네시아 IDR">인도네시아 IDR</option>
				<option value="일본 JPY">일본 JPY</option>
				<option value="중국 CNY">중국 CNY</option>
				<option value="체코 CZK">체코 CZK</option>
				<option value="칠레 CLP">칠레 CLP</option>
				<option value="카자흐스탄 KZT">카자흐스탄 KZT</option>
				<option value="카타르 QAR">카타르 QAR</option>
				<option value="캐나다 CAD">캐나다 CAD</option>
				<option value="쿠웨이트 KWD">쿠웨이트 KWD</option>
				<option value="태국 THB">태국 THB</option>
				<option value="터키 TRY">터키 TRY</option>
				<option value="파키스탄 PKR">파키스탄 PKR</option>
				<option value="폴란드 PLN">폴란드 PLN</option>
				<option value="필리핀 PHP">필리핀 PHP</option>
				<option value="헝가리 HUF">헝가리 HUF</option>
				<option value="호주 AUD">호주 AUD</option>
				<option value="홍콩 HKD">홍콩 HKD</option>
			</select>
		<p>
			<label>액수(금액) : </label>
			<input type="text" name="money" id="money" value="${sellInfo.money}"/>
		</p>
		<br/>
		
		<p>
			<label>판매 가격: </label>
			<input type="text" name="suggest" id="suggest"/>
		</p>
		<br/>
		
		<!-- 가로, 세로는 CSS로 width, height로 지정한게 더 우선된다 -->
		<textarea name="content" id="content" rows="10" cols="70" form="form">${sellInfo.content}</textarea>
		<br/>
		
		<input type="hidden" name="identifier" id="identifier" value="${sellInfo.identifier}"/>
		
		<input type="submit" value="수정"/>
		<input type="reset" value="취소"/>
	</form>
	
	<!-- 지도  -->
	<div id="map" style="width:80%;height:600px;"></div><br/>
	
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db87e9828f2b6a261357dbc4ce9d4cfb&libraries=services"></script>
	
	<script>
		var beforeLocation=[${sellInfo.location}];
		//var arr=beforeLocation.split(", ");// 위도와 경도의 문자열을 (, )로 분리해서 배열로 리턴
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(Number(beforeLocation[0]), Number(beforeLocation[1])), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };
		// 지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
		
		// 지도를 클릭한 위치에 표출할 마커입니다
		var marker = new kakao.maps.Marker({ 
		    // 지도 중심좌표에 마커를 생성합니다 
		    position: map.getCenter() 
		});
		
		
		// 지도에 마커를 표시합니다
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