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
<script src="JavaScript/cartManager.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user==null }">
			<c:redirect url="login.jsp" />
		</c:when>
		<c:when test="${sessionScope.user !=null }">

			<c:if test="${products == null}">

				<c:redirect url="errorPage.jsp">
					<c:param name="message" value="No data available"></c:param>
				</c:redirect>
			</c:if>

			<c:if test="${products != null}">
				<%@ include file="header.jsp"%>
				<h6 class="success" id = "success">${success }</h6>
				<h2 class="title">List of Products</h2>


				<table width="100%">

					<tr id=topic>

						<td>Product Name</td>
						<td>Product Category</td>
						<td>Description</td>
						<td>Price</td>
						<td>Quantity You want</td>
						<td>Add to cart?</td>
					</tr>

					<c:forEach items="${products}" var="item">

						<tr id=content>
							<form action="addToCart" onmouseover="initializeMsg()">
								<td>${item.name }</td>
								<td>${item.category }</td>
								<td>${item.details }</td>
								<td>${item.price }</td>
								<td><input type="number" name="qty" width="30"></td>
								<td><input type="hidden" value="${item.id }" id="itemId"
									name="itemId"> <input type="submit" value="ADD" /></td>
							</form>
						</tr>


					</c:forEach>
				</table>

			</c:if>
			<div class="footer">
				<jsp:include page="footer.jsp" />
			</div>
		</c:when>
	</c:choose>
</body>
</html>