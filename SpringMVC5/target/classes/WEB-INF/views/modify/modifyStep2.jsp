<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>비밀번호 변경</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
</head>
<body>
	
	<h4>${modifyRequest.id }</h4>님의 비밀번호가
	<h4>${modifyRequest.confirmNewPassword }</h4>로 변경되었습니다.
	
	<!-- 다시 로그인 화면으로 이동 -->
	<c:url var="login" value="/login/loginStep1">
	</c:url>
	<input type="button" value="확인"/>
	<script>
		$("input[value=\"확인\"]").on('click', function(){
			location.href="${login}";
		});
	</script>
</body>
</html>