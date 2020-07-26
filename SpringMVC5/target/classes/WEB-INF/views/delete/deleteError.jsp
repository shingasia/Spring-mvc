<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<title>회원 탈퇴 에러</title>
	<script>
		alert("아이디 또는 비밀번호가 틀립니다.");
	</script>
</head>
<body>
	<form id="deleteForm"></form>
	<script>
		$('#deleteForm').attr({
			"action": "../delete/deleteStep1",
			"method": "post"
		}).submit();
	</script>
</body>
</html>