<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>게시물 등록 완료</title>
</head>
<body>
	
	<c:url var="list" value="../list/sellList">
		<c:param name="id" value="${id}"/>
	</c:url>
	
	<!-- 다시 리스트 페이지로 이동 -->
	<script>
		alert('게시물이 등록되었습니다.');
		location.href="${list}";
	</script>
	
</body>
</html>