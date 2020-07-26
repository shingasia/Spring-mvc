<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script>
		/*
   			 이벤트 종류
		    ==========================================================
		    blur        focus       focusin     focusout    load
		    resize      scroll      unload      click       dblclick
		    mousedown   mouseup     mousemove   mouseover   mouseout
		    mouseenter  mouseleave  change      select      submit
		    keydown     keypress    keyup       error       ready
		    ==========================================================
		*/
		$(document).ready(function () {
            //$('body').css("background-color", "#ffe6f2").html('<div id="loginbox"></div>');
            $('#loginbox').css({
                "width" : "700px",
                "margin" : "100px auto",
                "border" : "5px solid #009933",
                "background-color" : "#d9ffb3"
			});

            //div[id=loginbox]
			$('div#loginbox').append($('<form></form').attr({
				"action": "loginStep2",
				"method": "get"
            }));
			
            //form
            $('form').append($('<div id="row1"></div>'), $('<div id="row2"></div>'), $('<div id="row3"></div>'));
			
            //row1
            $('<label></label>').text("ID 입력:").append($("<br/>")).append($('<input type="text" name="id" id="id" />')).append($("<br/>")).appendTo('#row1');
            $('<label></label>').text("PASSWORD 입력:").append($("<br/>")).append($('<input type="password" name="password" id="password" />')).appendTo('#row1');

            //row2
            $('<a href="../join/joinStep1"></a>').text("[회원가입]").appendTo('#row2');
            $('#row2').append("<br/>");
            $('<a href="../modify/modifyStep1"></a>').text("[비밀번호 변경]").appendTo('#row2');
            $('#row2').append("<br/>");
            $('<a href="../delete/deleteStep1"></a>').text('[회원탈퇴]').appendTo('#row2');
            $('#row2').append("<br/>");

            //row3
            $('<input type="submit"/>').val('로그인').appendTo('#row3');
            $('<input type="reset"/>').val('취소').appendTo('#row3');
            $('#row3').append($('<br/>'));


            //#row1, #row2, #row3 모두 해당
            $('form > div').css({
                "width": "300px",
                "margin": "50px auto"
            });

            $('label, a').css({
                "margin": "0 auto",
                "font-size": "20px"
            });
            
		});
	</script>
	<style>
		input:focus {
            border: 2px solid #ff0000;
            padding: 10px;
            font-size: 20px;
            margin: auto;
        }
        input:enabled {
            color: #9900cc;
            font-weight: bold;
            font-size: 20px;
            margin: auto;
        }
        body {
        	background-color: #e6ffff;
        }
        body > font[id=mainTitle] {
        	font-family: fantasy;
        	font-size: 90px;
        	color: #ffb3ff;
        	margin: 0px;
        }
        body > font[id=subTitle] {
        	font-family: fantasy;
        	font-size: 60px;
        	color: #d9b3ff;
        	margin: 0px;
        }
	</style>
	<title>로그인</title>
</head>
<body>
	<font id="mainTitle">외화   Dealer</font>
	<font id="subTitle">(Foreign Currency Dealer)</font>
	<div id="loginbox"></div>
</body>
</html>