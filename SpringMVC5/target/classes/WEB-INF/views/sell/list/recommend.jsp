<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>추천순 정렬</title>
	<style>
		#search {
			background-color: #e6fff2;
		}
		#unit{
			font-size:1.4em;
			color: #cc00cc;
		}
		form > label {
			font-size:1.4em;
		}
		th{
			font-size: 1.8em;
			background-color: #f2ffe6;
		}
		td{
			font-size: 1.8em;
			background-color: #e6f9ff;
		}
		body {
			background-color: #ffe6ff;
		}
	</style>
</head>
<body>
	<div id="search">
		<form action="recommendSellOption" method="get">
			<input type="hidden" name="id" value="${id}"/> <%-- id를 넘겨서 myLocation을 계산 --%>
			<input type="hidden" name="myLocation" value="${myLocation}"/>
			<select name="unit" id="unit"><%--value 속성으로 EL식을 통해 값을 지정할 수도 있다 --%>
				<option value="대한민국 KRW">대한민국 KRW</option>
				<option value="남아프리카 공화국 ZAR">남아프리카 공화국 ZAR</option>
				<option value="네팔 NPR">네팔 NPR</option>
				<option value="노르웨이 NOK">노르웨이 NOK</option>
				<option value="뉴질랜드 NZD">뉴질랜드 NZD</option>
				<option value="대만 TWD">대만 TWD</option>
				<option value="덴마크 DKK">덴마크 DKK</option>
				<option value="러시아 RUB">러시아 RUB</option>
				<option value="마카오 MOP">마카오 MOP</option>
				<option value="말레이시아 MYR">말레이시아 MYR</option>
				<option value="멕시코 MXN">멕시코 MXN</option>
				<option value="미국 USD">미국 USD</option>
				<option value="바레인 BHD">바레인 BHD</option>
				<option value="방글라데시 BDT">방글라데시 BDT</option>
				<option value="베트남 VND">베트남 VND</option>
				<option value="브라질 BRL">브라질 BRL</option>
				<option value="브루나이 BND">브루나이 BND</option>
				<option value="사우디아라비아 SAR">사우디아라비아 SAR</option>
				<option value="스웨덴 SEK">스웨덴 SEK</option>
				<option value="스위스 CHF">스위스 CHF</option>
				<option value="싱가포르 SGD">싱가포르 SGD</option>
				<option value="아랍에미리트 AED">아랍에미리트 AED</option>
				<option value="영국 GBP">영국 GBP</option>
				<option value="오만 OMR">오만 OMR</option>
				<option value="요르단 JOD">요르단 JOD</option>
				<option value="유럽연합 EUR">유럽연합 EUR</option>
				<option value="이스라엘 ILS">이스라엘 ILS</option>
				<option value="이집트 EGP">이집트 EGP</option>
				<option value="인도 INR">인도 INR</option>
				<option value="인도네시아 IDR">인도네시아 IDR</option>
				<option value="일본 JPY">일본 JPY</option>
				<option value="중국 CNY">중국 CNY</option>
				<option value="체코 CZK">체코 CZK</option>
				<option value="칠레 CLP">칠레 CLP</option>
				<option value="카자흐스탄 KZT">카자흐스탄 KZT</option>
				<option value="카타르 QAR">카타르 QAR</option>
				<option value="캐나다 CAD">캐나다 CAD</option>
				<option value="쿠웨이트 KWD">쿠웨이트 KWD</option>
				<option value="태국 THB">태국 THB</option>
				<option value="터키 TRY">터키 TRY</option>
				<option value="파키스탄 PKR">파키스탄 PKR</option>
				<option value="폴란드 PLN">폴란드 PLN</option>
				<option value="필리핀 PHP">필리핀 PHP</option>
				<option value="헝가리 HUF">헝가리 HUF</option>
				<option value="호주 AUD">호주 AUD</option>
				<option value="홍콩 HKD">홍콩 HKD</option>
			</select>
			
			<label>거리 <input type="range" name="weight" min="0" max="100" step="10" value="${weight}" /> 환율
			<input type="submit" value="검색"/></label>
		</form>
	</div><hr/>
	
	
	<div id="list">
		<%-- 거리(m, km), 주소, 제목, money, suggest 표시 --%>
		<%-- forEach --%>
		<h2>${id} 님 안녕하세요.</h2>
		<h2>거주지: ${myAddress}</h2>
		<table>
			<thead>
				<tr>
					<th>제목</th>
					<th>판매 액수</th>
					<th>가격</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="lists" items="${sellList}">
					<tr>
						<td>${lists.title}</td>
						<td>${lists.money}</td>
						<td>${lists.suggest}</td>
						<td>${lists.address}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
	
</body>
</html>