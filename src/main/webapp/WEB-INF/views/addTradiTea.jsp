<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<c:url value='/resources/css/teaHome.css'/>">
<title>
<spring:message code="addTea.form.top"/>
</title>
</head>
<body>
<script>
		<c:set var="home" value= 
			"${pageContext.request.contextPath}"/>
	</script>
	<div class="top">
		<div class="textPart">
			<h1>
				<spring:message code="addTea.form.top.Sign"/>
			</h1>
		</div>
	</div>
	<div class="mainDiv">
		<div class="homeLink">
			<p>
				<a href="${home}">
					<spring:message code="addTea.form.top.Home"/>
				</a>
			</p>
		</div>
		<div class="newTea">
		<form:form method="POST" modelAttribute="tradiTea" 
			onsubmit="return checkName()">
			<fieldset>
				<legend>
					<spring:message code="addTea.form.label.TeaLabel"/>
				</legend>
			<div>
				<table>
					<tr>
						<th><label for="teaName"> 
							<spring:message code="addTea.form.label.TeaName"/>
							</label>
						</th>
							<td align="left">
							<form:input id="teaName" path="teaName" type="text"
									class="form:input-large" />
							</td>
					</tr>
	
					<tr>
						<th><label for="amount"> 
							<spring:message code="addTea.form.label.amount"/>
							</label>
						</th>
							<td align="left">
							<form:input id="amount" path="amount" type="text"
							class="form:input-large" />
							</td>
				</tr>	
	
				<tr>								
					<th><label for="price"> 
						<spring:message code="addTea.form.label.price"/>
						</label>
					</th>
						<td align="left">
						<form:input id="price" path="price" type="text"
								class="form:input-large" />
						</td>
				</tr>			
					
				<tr>
					<th><label class="control-label col-lg-2 col-lg-2" for="stock">
						<spring:message code="addTea.form.label.TeaStock"/>
					</label>
						<td align="left">
						<form:input id="stock" path="stock" type="text"
							class="form:input-large" />
						</td>
				</tr>
				
				<tr>
					<th><label for="prodDate">
						<spring:message code="addTea.form.label.prodDate"/>
						</label>
					</th>
						<td align="left">
						<form:input id="prodDate" path="prodDate" type="text" />
						</td>
				</tr>
				
				<tr>
					<th><label for="prodDesc"> 
						<spring:message code="addTea.form.label.prodDesc"/>
						</label></th>
					<td>
						<form:textarea style="resize: none;" 
						id="prodDesc" path="prodDesc" cols="40" rows="5" />
					</td>
				</tr>
				
						</table>
				</div>
				<div>
					<div>
						<input type="submit" id="btnAdd" value="상품 등록" />
					</div>
				</div>
			</fieldset>
		</form:form>		
		</div>
	</div>
	<script>
		function checkName() {
			var nameBox = document.getElementById('teaName');
			if (nameBox.value == '') {
				alert('"전통차 이름" 누락');
				return false;
			}
			else 
				return true;
		}
	</script>
</body>
</html>