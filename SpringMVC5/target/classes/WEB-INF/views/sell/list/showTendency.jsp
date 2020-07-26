<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>현재 판매 추세</title>
	<script>
		function mainPage(){
			location.href="sellList?id="+"${id}";
		}
	</script>
	<style>
		#header {
			background-color: #e6ffe6;
		}
		body {
			background-color: #ccffff;
		}
		body > div > label {
			color: blue;
		}
		body > div > #mainPageLink {
			display: inline-block;
			font-size: 1.7em;
			background-color: #ff00ff;
		}
		#body span {
			width: 300px;
			display: inline-block;
			font-size: 1.6em;
			overflow: hidden;
		}
	</style>
</head>
<body>
	<div id="header">
		<label>${id} 님 안녕하세요.</label>
		<h1>현재 판매 동향</h1><input id="mainPageLink" type="button" value="메인 페이지" onclick="mainPage()"/><hr/>
	</div>
	<div id="body">
		<div>
			<c:forEach var="list0" items="${nationalities}">
				<span>${list0 }</span>
			</c:forEach>
		</div>
		<div>
			<c:forEach var="list1" items="${moneyAverage}">
				<span>${list1 }</span>
			</c:forEach>
		</div>
		<div>
			<c:forEach var="list2" items="${suggestAverage}">
				<span>${list2 }</span>
			</c:forEach>
		</div>
	</div>
	
</body>
</html>