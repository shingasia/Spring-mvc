<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<title>나의 판매 리스트</title>
</head>
<body>
	<c:url var="logout" value="/logout">
		<c:param name="id" value="${requestScope.id}"/>
	</c:url>
	<script>
		function logout(){
			location.href="${logout}"
		}
	</script>
	<!-- id값을 받아서 해당 리스트를 조회 -->
	<label>${id}</label>님의 게시물
	<input type="button" onclick="logout()" value="로그아웃"/><hr/>
	<hr/>
	
	<table border="2">
	    <thead>
	        <tr>
		        <th>제목</th>
		        <th>글쓴이</th>
		        <th>날짜</th>
		        <th>장소</th>
		        <th>화폐 단위</th>
		        <th>금액</th>
		        <th>식별자</th>
		        <th>내용 확인</th>
		        <th></th>
		        <!-- <th>내용</th>-->
		        <!-- 판매완료 여부 -->
	        </tr>
	    </thead>
	    <tbody id="tbody">
	        <c:forEach var="lists" items="${myList}">
	        	<tr>
	        		<div>
	        			<td>${lists.title}</td>
	        			<td>${lists.id}</td>
	        			<td>${lists.date}</td>
	        			<td>${lists.location}</td>
	        			<td>${lists.unit}</td>
	        			<td>${lists.money}</td>
	        			<td>${lists.identifier}</td>
	        			<td>
	        				<c:url var="content" value="/sell/list/sellContent">
		        				<c:param name="identifier" value="${lists.identifier}"/>
		        				<c:param name="id" value="${requestScope.id}"/>
		        			</c:url>
		        			<a href="${content}">내용 확인</a>
	        			</td>
	        		</div>
	        		<td>
	        			<c:if test="${requestScope.id==lists.id}">
			        		<c:url var="delete" value="/delete/deleteSell" >
			        			<c:param name="listType" value="myList"/>
		        				<c:param name="identifier" value="${lists.identifier}"/>
		        				<c:param name="id" value="${requestScope.id}"/>
			        		</c:url>
			        		<div>
			        			<input type="button" value="삭제"/>
			        			<script>
			        				$('input[value=\"삭제\"]').on('click', function(){
			        					location.href="${delete}";
			        				});
			        			</script>
			        		</div>
			        	</c:if>
	        		</td>
	        	</tr>
	        </c:forEach>
	    </tbody>
	</table> 
</body>
</html>