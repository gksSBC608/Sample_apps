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
				<tr class="subtitle">
					<th>${success}</th>
				</tr>
				<tr class="content">
					<td><a href="subscribeChannel">Subscribe a channel</a></td>
				</tr>
				<tr class=content>
					<td><a href="viewDetail">View Subscriptions</a></td>
				</tr>
				<tr>
					<td class = "content"><input type="button" value="BACK" onclick="history.back()" />
					</td>
				</tr>
			</table>
			<jsp:include page="footer.jsp" />
		</c:when>
	</c:choose>
</body>
</html>