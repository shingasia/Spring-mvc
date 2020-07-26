<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>가입 완료</title>
	<script>
		var name="${joinRequest.name}";
		var id="${joinRequest.id}";
		var password="${joinRequest.password}";
		
		alert(name+'님\n'+'가입이 완료되었습니다.');
		alert('아이디 :'+id+'\n'+'비밀번호: '+password+'입니다.');
		location.href="../login/loginStep1";
	</script>
</head>
<body>
	
	
</body>
</html>