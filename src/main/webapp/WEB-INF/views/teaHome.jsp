<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JH 웹 전통 찻집</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/teaHome.css'/>">
</head>
<body>
   <div class="top">
      <div class="textPart">
         <h1>${welcomeMsg}</h1>
         
         <p>${sellItems}</p>
      </div>
   </div>
	
</body>
</html>