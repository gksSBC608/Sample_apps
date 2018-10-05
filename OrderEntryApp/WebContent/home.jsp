<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user==null }">
			<c:redirect url="login.jsp" />
		</c:when>
		<c:when test="${sessionScope.user !=null }">
			<%@ include file="header.jsp"%>
			<h2 class="title">MENU PAGE</h2>
			<table width=30% align=center>
				<tr class="success">
					<td>${success}</td>
				</tr>
				<tr class="content">
					<td><a href="listProducts">View the available products</a></td>
				</tr>
				<c:if test="${sessionScope.role.equalsIgnoreCase('admin')}">
					<tr class=content>
						<td><a href="addProduct.jsp">Add a new product</a></td>
					</tr>
					<tr class=content>
						<td><a href="generateOrderSummary.jsp">Generate order
								Summary</a></td>
					</tr>
				</c:if>
				<tr>
					<td class="content"><input type="button" value="BACK"
						onclick="history.back()" /></td>
				</tr>
			</table>
			<div class="footer">
				<jsp:include page="footer.jsp" />
			</div>
		</c:when>
	</c:choose>
</body>
</html>