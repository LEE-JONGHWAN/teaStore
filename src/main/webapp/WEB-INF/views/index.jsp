<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JH 찻집</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/teaHome.css'/>">
</head>
</head>
<body>
	<div class="top">
		<div class="textPart">
			<h1>${welcomeMsg}</h1>
			<p>${sellitems}</p>
		</div>
	</div>
	<div class="mainDiv">
		<p>
			<a href="<c:url value='/tea/add'/>">전통차 등록</a>
		<p>
			<a href="<c:url value='/tea/teaDetail?teaId=3'/>">차 (ID=3)</a>
		<p>
			<a href="<c:url value='/tea/teaDetail?teaId=2'/>">차 (ID=2)</a>
		<p>
			<a href="<c:url 
				value='/tea/listing/녹차/price;low=20000;high=30000'/>">
				녹차(2~3만원)</a>
		<p>
			<a href="<c:url value='/tea/listing'/>">전통차 목록</a>
		<p>
			<a href="<c:url value='/tea/todaytea'/>">오늘차 할인</a>
		<p>
			<a href="<c:url value='/tea/listing/녹차'/>">녹차</a>
		<p>
			<a href="<c:url value='/tea/listing/율무차'/>">율무차</a>
	</div>
</body>
</html>