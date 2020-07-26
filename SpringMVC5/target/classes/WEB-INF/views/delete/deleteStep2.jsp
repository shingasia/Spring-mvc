<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>회원 탈퇴 완료</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		alert('${name}님 탈퇴가 완료되었습니다.');
		location.href="../login/loginStep1";
	</script>
</head>
<body>
	<!-- 다시 login페이지로 이동  -->
</body>
</html>