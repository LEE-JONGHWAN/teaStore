<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/resources/css/teaHome.css'/>">
<title>전통차 상품 목록</title>
</head>
<body>
	<script>
		<c:set var="home" value= "${pageContext.request.contextPath}"/>
	</script>
	<div class="top">
		<div class="textPart">
			<h1>${welcomeMsg}</h1>
			<p>${sellitems}</p>
		</div>
	</div>
	<div class="mainDiv">
		<div class="homeLink">
			<p>
				<a href="${home}">홈으로</a>
			</p>
		</div>
		<div class="teaBlock">
			<c:forEach items="${tradiTeas}" var="tradiTea">
				<div class="caption" style="display: inline">
					<p>차이름: ${tradiTea.teaName}</p>
					<p>제고량: ${tradiTea.stock}</p>
					<p>생산일: ${tradiTea.prodDate}</p>
					<p>판매가: ${tradiTea.price}</p>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>