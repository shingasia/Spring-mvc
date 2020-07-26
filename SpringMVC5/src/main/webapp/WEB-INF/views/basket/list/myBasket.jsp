<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>나의 장바구니</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		function mainPage(){
			location.href="../../sell/list/sellList?id="+"${id}";
		}
		function checkContent(iden){
			location.href="../../sell/list/sellContent?identifier="+iden+"&id="+"${id}";
		}
	</script>
	<style>
		body div {
			font-size: 2.5em;
			border: 4px solid #ffff00;
			background-color: #99ccff;
			width: 80%;
			margin: auto;
		}
		div > form > input {
			font-size:25px;
			color: #cc0066;
			background-color: #e6e6ff;
		}
		#mainPageLink {
			display: inline-block;
			font-size: 1.7em;
			background-color: #ff00ff;
		}
	</style>
</head>
<body>
	<h1>${id} 님의 장바구니</h1><input id="mainPageLink" type="button" value="메인 페이지" onclick="mainPage()"/><hr/>
	
	<c:forEach var="bList" items="${myBasket}">
		<div id='${bList["identifier"]}' onclick='checkContent("${bList.identifier}")' >${bList["title"]},  ${bList["address"]}
			<form action="../delete/deleteBasket" method="get">
				<input type="hidden" name="id" value="${id}"/>
				<input type="hidden" name="identifier" value='${bList["identifier"]}'/>
				<input type="submit" value="북마크에서 삭제"/>
			</form>
		</div>
	</c:forEach>
	
	
	<script>
		$('div').hover(function(){ 
			$(this).css("background-color", "#ff00bf");
		}, function(){ 
			$(this).css("background-color", "#99ccff");
		});
	</script>
	
</body>
</html>