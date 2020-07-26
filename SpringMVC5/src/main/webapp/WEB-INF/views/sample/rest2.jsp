<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		/*
			윈도 이벤트 종류
			ready	: 문서 객체가 준비 완료되면
			load 	: 윈도(문서 객체)를 불러들일 때 발생
			unload  : 윈도(문서 객체)를 닫을 때 발생
			resize  : 윈도의 크기를 변화시킬 때 발생
			scroll	: 윈도를 스크롤할 때 발생
			error	: 에러가 있을 떄 발생
		*/
		$(document).ready(function(){
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json;charset=utf-8",
		        url : "${pageContext.request.contextPath}/sample/sampleList",
		        async: true,
		        success : function(data, status, xhr) {
		        	var list=JSON.stringify(data);
		        	$('<h1></h1>').text(list).appendTo('body');
		        }
			});
		});
	</script>
</head>
<body>

</body>
</html>