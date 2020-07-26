<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>최근 방문 기록</title>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<style>
		#mainPageLink {
			display: inline-block;
			font-size: 1.7em;
			background-color: #ff00ff;
		}
		#deleteCookiesLink {
			display: inline-block;
			font-size: 1.7em;
			background-color: #33cc00;
		}
		div span[id=title] {
			width: 400px;
		}
		div span{
			display: inline-block;
			font-size: 1.7em;
		}
		div {
			background-color: #d6f5d6;
			border: border: 4px solid #ffff80;
			height: 60px;
		}
	</style>
	<script>
		function mainPage(){
			location.href="../list/sellList?id="+"${id}";
		}
		function deleteCookies(){
			location.href="deleteRecords?id="+"${id}";
		}
		function checkContent(iden){
			location.href="../list/sellContent?identifier="+iden+"&id="+"${id}";
		}
	</script>
</head>
<body>
	<h1>최근에 방문한 페이지입니다.</h1>
	<input id="mainPageLink" type="button" value="메인 페이지" onclick="mainPage()"/>
	<input id="deleteCookiesLink" type="button" value="기록 삭제" onclick="deleteCookies()" /> <hr/>
	
	<c:forEach var="rList" items="${record}">
		<div id="${rList.identifier}" onclick='checkContent("${rList.identifier}")'>
			<span id="title">${rList["title"]}</span> <span>${rList["address"]}</span>
		</div>
	</c:forEach>
	
	<script>
		//hover는 mouseenter와 mouseleave를 동시에 연결
		$('div').hover(function(){ 
			$(this).css("background-color", "#ffe6ff");
		}, function(){ 
			$(this).css("background-color", "#d6f5d6");
		});
	</script>
	
</body>
</html>