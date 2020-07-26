<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<style>
		#header > input, #header > form > input {
            margin: 5px;
            border: 3px solid red;
            background-color: #ffccff;
            font-size: 1.7em;
            display: inline-block;
        }
		
		#header > form {
			display: inline-block;
		}
		
        #header > p {
            font-size:2em;
            display: inline-block;
        }
        #section {
            width: 100%;
            margin: 0 auto;
        }
        #row1 > button {
            display: inline-block;
            width: 330px;
            height: 300px;
            margin: 10px;
            border: 3px solid red;
            font-size: 3em;
            background-color: #ffff66;
        }
	</style>
	<title>메인 페이지</title>
</head>
<body>
	<%
		//post방식 인코딩
		request.setCharacterEncoding("utf-8");
	%>
	<!-- mainPage, sellList, myList 전부다 로그 아웃 버튼 있어야 한다  -->
	<c:url var="myList" value="../sell/list/myList">
		<c:param name="id" value="${id}"/>
	</c:url>
	<c:url var="logout" value="/logout">
		<c:param name="id" value="${requestScope.id}"/>
	</c:url>
	
	<script>
		function sellList(){
			location.href="../sell/list/sellList?id=${id}";
		}
		function myList(){
			location.href="${myList}";
		}
		function logout(){
			location.href="${logout}";
		}
	</script>
	
	
	
	<div id="header">
        <p>${id }님 안녕하세요.</p><!-- loginController에서 전달한 id 속성 -->
        <input type="button" onclick="logout()" value="로그아웃" />
        <form id="deleteButton">
        	<input type="button" value="회원 탈퇴"/>
        </form>
        <hr />
    </div>
    <script>
    	$("form[id=\"deleteButton\"]").attr({
			"action" : "../delete/deleteStep1",
			"method" : "post"
		}).on('click', function(){
    		this.submit();
    	});
    </script>
    
    
	<div id="section">
        <div id="row1">
            <button type="button" onclick="sellList()">전체 리스트</button>
            <button type="button" onclick="myList()">나의 리스트</button>
        </div>
    </div>
	
	
	<!-- input type button으로 해당 url로 이동하도록 만든다 onclick="method()"
	
		폼 전송 기능을 하는 <input type="submit"> 과 <button> 은 기능적으로 동일하다. 
		기본적으로 button 요소는 type 속성을 명시하지 않으면 submit 기능을 수행한다.
		즉 폼에서 이를 대체하기 위한 목적으로는 안성맞춤이다.
		
		type="submit" : 폼의 전송 기능을 담당한다.
		type="reset" : 폼 작성 내용을 초기화하는데 사용한다.
		type="button" : 흔히 자바스크립트를 이용한 기능 구현에 많이 사용한다.

	 -->
	<!-- 게시물 리스트로 이동 버튼(id 데이터를 전달해야 해서 컨트롤러를 따로 만들까 아니면 파라미터로 넘길까 생각중) -->
	
	
</body>
</html>