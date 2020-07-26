<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>비밀번호 변경</title>
	<style>
		body {
			background-color: #99ffcc;
		}
		h2 {
			font-size: 2em;
			color: #ff3300;
		}
		form label {
			font-size: 2.2em;
			font-family: sans-serif;
			color: #002699;
		}
		form > p > input {
			height: 30px;
			margin: 20px;
			width: 300px;
			font-size: 25px;
		}
		form > input[type=submit] {
			margin: 20px;
			width: 80px;
			height:	40px;
		}
	</style>
	<script>
		if("${message}"){
			alert("${message}");
		}
	</script>
</head>
<body>
	<h2>비밀번호 변경을 위해 회원 정보를 입력하세요.</h2>
	<form action="modifyStep2" method="post">
		<p>
			<label>ID:</label><br/>
			<input type="text" name="id" id="id"/><%--value 속성으로 EL식을 통해 자동으로 ID를 가져오도록 바꾸자 --%>
		</p>
		<p>
			<label>CURRENT PASSWORD: </label><br/>
			<input type="password" name="currentPassword" id="currentPassword"/>
		</p>
		<p>
			<label>NEW PASSWORD: </label><br/>
			<input type="password" name="newPassword" id="newPassword"/>
		</p>
		
		<p>
			<label>CONFIRM NEW PASSWORD: </label><br/>
			<input type="password" name="confirmNewPassword" id="confirmNewPassword" />
		</p>
		
		<input type="submit" value="변경" />
		
	</form>
</body>
</html>