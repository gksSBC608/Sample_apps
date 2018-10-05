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
			<h2 class="title">View Summary</h2>
			<div align=center>
				<form action="/OrderEntryApp/generateSummary" method="post">
					<table>

						<tr>
							<td><label class="heading">Enter a date*</label></td>
							<td><input type="text" name="date" id="date" /></td>
						</tr>
						<tr>
							<td class="heading"><input type="submit" value="SUBMIT" /></td>
						</tr>
					</table>
				</form>
			</div>
			<c:if test="${detail != null}">


				<h2 class="title">Orders for date ${date}</h2>


				<table width="100%">

					<tr id=topic>

						<td>Order ID</td>
						<td>Product</td>
						<td>User</td>
						<td>Total amount</td>

					</tr>
					<c:forEach items="${detail}" var="item">

						<tr id=content>

							<td>${item.id }</td>
							<td>${item.product.name }</td>
							<td>${item.user.userName }</td>
							<td>${item.totalPrice}</td>
						</tr>


					</c:forEach>
				</table>

			</c:if>
			<jsp:include page="footer.jsp" />
		</c:when>
	</c:choose>
</body>
</html>