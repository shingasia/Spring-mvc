<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>REST API TESTING</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<!-- 
		<script>
			$(document).ready(function(){
				
				$.getJSON('/sample/sampleList', function(data){
					alert('데이터 길이: '+data.length);
					alert(JSON.stringigy(data));
				});
			});
		</script>
		-->
	</head>
	<body>
		<button id="btnSelect" type="button">누르세요.</button> 
		<script>
			//-. url : 필수항목으로, 요청이(http request) 보내질 URL이다. 데이터 타입은 String.
	 		//-. data : option으로 key-value쌍으로 구성된 서버로 전송될 데이터.
	 		//-. success : optional으로 서버로부터 JSON 데이터 수신이 완료되었을 때 실행하게 될 function이다.
	 		//			이 function의 파라미터로 서버가 전송한 JSON 데이터가 담기게 된다.
			$('#btnSelect').on('click',function(){//클릭 이벤트가 발생했을때 서버와 ajax통신을 수행
				$.ajax({
					type: "GET",
					dataType: "json",
					contentType: "application/json;charset=utf-8",
			        url : "${pageContext.request.contextPath}/sample/sampleList",
			        async: true,
			        success : function(data, status, xhr) {
			        	var list=JSON.stringify(data);
			        	//alert(typeof(list)); String 타입이다
			        	$('<h1></h1>').text(list).appendTo('body');
			        }
				});
			});
		</script>
	</body>
</html>