<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<title>내용</title>
	<style>
		form[id=bookmark] > input[type=submit] {
			font-size: 2em;
			background-color: #00e64d;
		}
	</style>
	<script>
		if("${message}"){
			alert("${message}");
		}
	</script>
</head>
<body>
	
	<script>
		$('body').css('background-color', '#ffffcc');
        $('<div></div>').addClass('sell_information').css('background-color', '#e6fff7').appendTo('body');
        $('<hr/>').appendTo('body');
        $('<div></div>').addClass('sell_content').css({
            "width": "70%",
            "height": "500px",
            "border": "4px solid #ff6600",
            "overflow": "scroll",
            "background-color": "#ffb3d1"
        }).appendTo('body');
        
        var title=$('<h1></h1>').html('제목: ${sellVO.title}');
     	var id=$('<h3></h3>').html('글쓴이: ${sellVO.id}');
     	var date=$('<h3></h3>').html('날짜: ${sellVO.date}');
     	var suggest=$('<h3></h3>').html('제시 가격: ${sellVO.suggest}');
     	
     	$('.sell_information').append(title, id, date, suggest);
     	$('.sell_content').text('${sellVO.content}');
    </script>
    
	
	<!-- 만약에 로그인한 사용자와 글쓴이가 같은 사람이라면 변경과 삭제 버튼 생성 -->
	<c:if test="${requestScope.id == sellVO.id}">
		<hr/>
		<c:url var="modify" value="/sell/modify/modifySellStep1" >
			<c:param name="identifier" value="${sellVO.identifier}"/>
		</c:url>
		<c:url var="delete" value="/delete/deleteSell"><!-- 컨트롤러 경로에 맞게 작성 -->
			<c:param name="identifier" value="${sellVO.identifier}"/>
			<c:param name="listType" value="sellContent"/>
			<c:param name="id" value="${id}"/>
		</c:url>
		
		<input type="button" value="변경" onclick="modify()"/><!-- 변경하는 폼으로 이동 -->
		<input type="button" value="삭제" onclick="remove()"/>
		
		<script>
			function modify(){
				location.href="${modify}";
			}
			function remove(){
				location.href="${delete}";
			}
		</script>
		
	</c:if>
	<hr/>
	
	<!-- 북마크 추가 버튼 -->
	<form id="bookmark" action="../../basket/register/registerBasket" method="post">
		<input type="hidden" name="id" value="${requestScope.id}"/>
		<input type="hidden" name="identifier" value="${sellVO.identifier}"/>
		<input type="submit" value="북마크에 추가"/>
	</form>
	
	<!-- 댓글 달기 -->
	댓글 쓰기<br/>
	<form action="registerComment" method="post"><!-- action="registerComment" method="post"-->
		
		<input type="hidden" name="commentId" id="commentId" value=""/>
		<input type="hidden" name="identifier" id="identifier" value="${sellVO.identifier}"/>
		<input type="hidden" name="id" id="id" value="${requestScope.id}"/><!-- 댓글 작성자 ID -->
		<textarea name="content" id="content" rows="8" cols="70"></textarea>
		
		<input type="submit" value="확인"/>
	</form>
	<script>
		$('<div id="comment_box"></div>').appendTo('body');
	</script>
	<script>
		/*
			serialize()			입력 양식의 내용을 요청 매개변수 문자열로 만든다.
			serializeArray()	입력 양식의 내용을 객체로 만든다.
			$.param()			객체의 내용을 요청 매개변수 문자열로 만든다.
		*/
		//모든 댓글 가져오기
		$(document).ready(function(){
			$.ajax({
				type: "GET",
				dataType: "json",
				contentType: "application/json;charset=utf-8",
		        url : "commentListByJSON/${sellVO.identifier}",
		        async: true,
		        success : function(data) {
		        	var list=data;//JSON 배열을 받음
		        	var loginId="${requestScope.id}";
		        	
		        	$.each(list, function(index, item){
		        		
		        		
		        		var commentDiv=$('<div></div>').addClass(item['commentId']);
		        		var content=$('<span><h3></h3></span>').text(item['content']);
		        		var id=$('<span><h5></h5></span>').text(item['id']);
		        		var date=$('<span><h5></h5></span>').text(item['date']);
		        		
		        		
		        		commentDiv.append(content, id, date).appendTo('#comment_box');
		        		
		        		if(item['id']==loginId){//JSP 에서 EL식으로 표현된 문자열과 JavaScript문자열을 비교하는 방법을 알아보자
		        			
		        			var deleteForm = $('<form></form>').attr({
		        				"action" : "deleteComment",//->/sell/list/deleteComment
		        				"method" : "post"
		        			});
		        			
		        			$('<input type="hidden" />').attr('name', 'commentId').val(item['commentId']).appendTo(deleteForm);
		        			$('<input type="hidden" />').attr('name', 'id').val(loginId).appendTo(deleteForm);
		        			$('<input type="hidden" />').attr('name', 'identifier').val('${sellVO.identifier}').appendTo(deleteForm);
		        			$('<input type="submit" />').val('삭제').appendTo(deleteForm);
		        			commentDiv.append(deleteForm);
		        			
		        			
		        		}
		        		$('<br/>').appendTo('#comment_box');//줄바꿈
		        	});
		        	
		        }
			});
		});
		
	</script>
	
</body>
</html>