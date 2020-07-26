<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	//post방식 인코딩
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<title>회원 탈퇴</title>
	<style>
		p > label {
			font-size: 30px;
			color: #0000ff;
		}
		p > label > input {
			width: 300px;
			height: 60px;
			font-size: 30px;
			border : "4px solid #cc0066";
		}
		form > input[type=submit] {
			width: 70px;
			height: 40px;
			margin: 10px;
			border: "3px solid #00cc00"
		}
	</style>
</head>
<body>
	<form action="deleteStep2" method="post">
		<p>
			<label>ID 입력:<br/>
				<input type="text" name="id" id="id"/>
			</label>
		</p>
		<p>
			<label>PASSWORD 입력:<br/>
				<input type="password" name="password" id="password"/>
			</label>
		</p>
		<input type="submit" value="탈퇴"/>
	</form>
	
</body>
</html>