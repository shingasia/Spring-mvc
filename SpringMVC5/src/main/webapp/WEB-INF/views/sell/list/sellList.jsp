<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
	<title>전체 판매 리스트</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<style>
		body > p {
			font-size:2em;
            display: inline-block;
		}
	</style>
</head>
<body>
	<p>${id} 님 안녕하세요.</p>
	<c:url var="register" value="/sell/register/registerSellStep1">
		<c:param name="id" value="${id}"/>
	</c:url>
	<c:url var="myList" value="myList">
		<c:param name="id" value="${id}"/>
	</c:url>
	<c:url var="logout" value="/logout">
		<c:param name="id" value="${id}"/>
	</c:url>
	
	<script>
		$('<button></button>').attr({
			id:"registerSell",
			type:"button"
		}).text('게시물 쓰기').css({"font-size": "1.7em", "background-color": "#66ffc2"}).appendTo('body');
		$('<button></button>').attr({
			id:"myList",
			type:"button"
		}).text('내 게시물').css({"font-size": "1.7em", "background-color": "#33d6ff"}).appendTo('body');
		var LogOut=$('<input id="logout" type="button" value="로그아웃"/>').css({
			"font-size": "1.7em", "background-color": "#ff8080"
		});
		var showTendency=$('<input id="showTendency" type="button" value="판매 경향" />').css({
			"font-size": "1.7em", "background-color": "#ffff99"
		});
		var basket=$('<input id="basket" type="button" value="장바구니" />').css({
			"font-size": "1.7em", "background-color": "#c299ff"
		});
		var recently=$('<input id="recently" type="button" value="최근 기록" />').css({
			"font-size": "1.7em", "background-color": "#ff8000"
		});
		var recommend=$('<input id="recommend" type="button" value="추천순" />').css({
			"font-size": "1.7em", "background-color": "red"
		});
		//var input='<input id="logout" type="button" value="로그아웃"/><hr/>'
		$('body').append(LogOut, showTendency, basket, recently, recommend).append('<hr/>');
		
		//이벤트 등록
		$('#registerSell').on('click',function(){
			location.href="${register}";
		});
		$('#myList').on('click',function(){
			location.href="${myList}";
		});
		$('#logout').on('click',function(){
			location.href="${logout}";
		});
		$('#showTendency').on('click', function(){
			location.href="showTendency?id="+"${id}";
		});
		$('#basket').on('click', function(){
			location.href="../../basket/list/myBasket?id="+"${id}";
		});
		$('#recently').on('click', function(){
			location.href="../recently/recentlyVisit?id="+"${id}";
		});
		$('#recommend').on('click', function(){
			location.href="recommend?id="+"${id}";
		});
	</script>
	
	<select name="unit" id="unit"><%--value 속성으로 EL식을 통해 값을 지정할 수도 있다 --%>
		<option value="ALL">ALL</option>
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
	
	<form id="crawler">
		<input type="text" name="money" />
		<input type="hidden" name="unit" value="ALL"/>
		<input type="button" value="환율계산" />
		<label></label>
	</form>
	
	<hr/>
	
	<script>
		$('#unit').css({
			"font-size" : "1.8em",
			"color" : "#cc00cc",
			"display": "inline-block"
		});
	</script>
	
	<script>
		//listBox(sellBox와 map을 둘러 싸고있는 바깥의 큰 박스)
        $('<div id="listBox"></div>').css({
            "background-color": "#e6ffe6"
        }).appendTo('body');
		
      	//sellBox(button들이 들어가는 태그)
        $('<div id="sellBox"></div>').css({
        	"width": "20%",
        	"height": "900px",
            "display" : "inline-block",
            "overflow" : "scroll",
            "background-color": "#99ffff"
        }).appendTo("#listBox");
		
      	//mapBox(지도)
      	$('<div id="map"></div>').css({
      		"width": "80%",
      		"height": "900px",
      		"display": "inline-block",
      		"float": "right"
      	}).appendTo('#listBox');
	</script>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=db87e9828f2b6a261357dbc4ce9d4cfb"></script>
	<script>
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = { 
		        center: new kakao.maps.LatLng(37.564402234476795, 126.99281263157685), // 지도의 중심좌표 <- 마커 아님
		        level: 10 // 지도의 확대 레벨
		    };
		
		// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다(전역 변수에 저장)
		var map = new kakao.maps.Map(mapContainer, mapOption);
		
		// 지도에 표시된 마커 객체({kakao.maps.Marker})를 가지고 있을 배열입니다
    	var markers = []; //map, position 속성 둘 다 저장된다
		var infoWindows = [];//infoWindow 객체 배열(content, map, position)
		
		// 마커를 생성, 지도위에 표시하고, InfoWindow와 같이 배열에 저장해 주는 함수입니다
		function addMarkerAndInfoWindow(position, markers, cnt, identifier) { // (kakao.maps.LatLng, 전역 배열, infowindow의 content, identifier)
		    
		    // 마커를 생성합니다 (position 속성 {kakao.maps.LatLng})
		    var marker = new kakao.maps.Marker({ //파라미터로 object를 받나?
		        position: position
		    });
			
		    // 마커가 지도 위에 표시되도록 설정합니다(map 속성)
		    marker.setMap(map);
		    
		    //infowindow 생성
		    var ifWindow = new kakao.maps.InfoWindow({
				content: cnt,
				position: position
			});
		    
		 	// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
		    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, ifWindow));
		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(ifWindow));
		    kakao.maps.event.addListener(marker, 'click', clickListener(marker, identifier))
		 	// 생성된 마커와 InfoWindow를 배열에 추가합니다
		    markers.push(marker);
		    infoWindows.push(ifWindow);
		    
		}
		
		// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
		function makeOverListener(map, marker, infowindow) {
		    return function() {
		        infowindow.open(map, marker);
		    };
		}
		// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
		function makeOutListener(infowindow) {
		    return function() {
		        infowindow.close();
		    };
		}
		// 마커를 클릭했을 때 실행되는 콜백함수
		function clickListener(marker, identifier){
			var latlng=marker.getPosition();
			return function() {
				location.href="sellContent?identifier="+identifier+"&id="+"${id}";
			}
		}
		
		
		/*
			javascript에는 아래 6개 데이터에 대해서 false 값으로 판단한다. <- Boolean() 함수, 논리연산자를 쓸 때 알아두자
			undefined, null, 0, -0, NaN, ""(빈 문자열)
		*/
	</script>

	
	<script>
		//이벤트 처리 부분은 전부 여기서 타이핑한다.
		
		$(document).ready(function(){
          	//JSON 배열로 서버에서 데이터 가져오기
            //게시물 하나하나 sellBox에 넣어서 출력
            $.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json;charset=utf-8",
		        url : "${pageContext.request.contextPath}/sell/list/sellListByJSON",
		        async: true,
		        success : function(data) {
		        	var list=data;//JSON 배열을 받음 
		        	
		        	//list의 location값들을 markers배열에 저장
		        	for(var i=0;i<list.length;i++){
		        		//markers.push(list[i]['location']); //string 타입
		        		var latlng=list[i]['location'].split(', ');//split으로 위도와 경도를 자른다->문자열 배열
		        		var content="<div>"+list[i]['address']+"<br/>"+list[i]['money']+" "+list[i]['unit']+"</div>";
		        		var identifier=list[i]['identifier'];
		        		
		        		//위도, 경도, div내용, identifier
		        		addMarkerAndInfoWindow(new kakao.maps.LatLng(Number(latlng[0]), Number(latlng[1])), markers, content, identifier);
		        	}
		        	
		        	
		        	$.each(list, function(index, item){
		        		var sellContent="sellContent?"+"identifier="+item['identifier']+"&id="+"${id}";
		        		
		        		//id 속성에 식별자, class 속성에 글쓴이를 각각 저장
		        		$('<button></button>').html(item['title']+'<br/>'
		        				+item['address']+'<br/>'
		        				+item['money']+' '+item['unit']+'<br/>'
		        				+item['suggest']+' KRW').attr({
		        			"id" : item['identifier'],
		        			"class": item['location']
		        		}).css({
		        			"width": "100%",
		        			"font-size": "1.2em",
		        			"background-color": "#ffe6ff",
		        			"overflow": "hidden"
		        		}).on('click',function(){
		        			location.href=sellContent;
		        		}).appendTo('#sellBox');
		        		
		        		
		        		$('<br/>').appendTo('#sellBox');//줄바꿈
		        	});
		        	
		        }
			});
          	
          	
          	//select 이벤트 처리
          	$('select[id="unit"]').on("change", function(){
          		//var selectedValue=$('select[id="unit"] option:selected').val();
          		//var selectedText=$('select[id="unit"] option:selected').text();
          		
          		//환율계산 form의 hidden 값을 바꿔준다
          		$('#crawler > input[name="unit"]').val(''+$('select[id="unit"] option:selected').val());
          		
          		//sellBox(button들이 들어가는 태그)의 내용을 비운다, 지도의 마커들을 다 지운다
    			$('#sellBox').empty();
    			
    			$.ajax({
    				type: "GET",
    				dataType: "json",
    				contentType: "application/json;charset=utf-8",
    				url : "${pageContext.request.contextPath}/sell/list/sellListByJSON_Unit?unit="+$('select[id="unit"] option:selected').val(),
    		        async: true,
    		        success : function(data) {
    		        	var list=data;//JSON 배열을 받음
    		        	
    		        	//지도에 마커들을 지우는 과정
    		        	for (var i = 0; i < markers.length; i++) {
    		                markers[i].setMap(null);
    		            }
    		        	
    		        	//전역으로 선언한 배열을 비워준다
    		        	markers = [];
    		        	infoWindows = [];
    		        	
    		        	
    		        	//list의 location 값들을 markers배열에 저장
    		        	for(var i=0;i<list.length;i++){
    		        		//markers.push(list[i]['location']); //string 타입
    		        		var latlng=list[i]['location'].split(', ');//split으로 위도와 경도를 자른다->문자열 배열
    		        		var content="<div>"+list[i]['address']+"<br/>"+list[i]['money']+" "+list[i]['unit']+"</div>";
    		        		var identifier=list[i]['identifier'];
    		        		
    		        		addMarkerAndInfoWindow(new kakao.maps.LatLng(Number(latlng[0]), Number(latlng[1])), markers, content, identifier);
    		        	}
    		        	
    		        	
    		        	$.each(list, function(index, item){
    		        		var sellContent="sellContent?"+"identifier="+item['identifier']+"&id="+"${id}";
    		        		
    		        		//id 속성에 식별자, class 속성에 글쓴이를 각각 저장
    		        		$('<button></button>').html(item['title']+'<br/>'
    		        				+item['address']+'<br/>'
    		        				+item['money']+' '+item['unit']+'<br/>'
    		        				+item['suggest']+' KRW').attr({
    		        			"id" : item['identifier'],
    		        			"class": item['location']
    		        		}).css({
    		        			"width": "100%",
    		        			"font-size": "1.2em",
    		        			"background-color": "#ffe6ff",
    		        			"overflow": "hidden"
    		        		}).on('click',function(){
    		        			location.href=sellContent;
    		        		}).appendTo('#sellBox');
    		        		
    		        		
    		        		$('<br/>').appendTo('#sellBox');//줄바꿈
    		        	});
    		        	
    		        }
    			});
          		
          	});
          	
          	//환율 계산기
          	$('#crawler > input[type="button"]').on('click', function(){
          		$.ajax({
    				type: "GET",
    				dataType: "text",
    				url : "${pageContext.request.contextPath}/sell/list/crawlerByTEXT?"+"money="+$('#crawler > input[name="money"]').val()+"&unit="+$('#crawler > input[name="unit"]').val(),
    		        async: true,
    		        success : function(data) {
    		        	$('#crawler > label').text(data);
    		        }
          		});
          	});
          	
		});
		
	
	</script>
	
	
</body>
</html>