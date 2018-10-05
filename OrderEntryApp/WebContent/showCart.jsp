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
			<c:choose>
				<c:when test="${sessionScope.cart.size()!=0 }">
					<h2 class="title">Products in Cart</h2>


					<table width="100%">

						<tr id=topic>

							<td>Product Name</td>
							<td>Product Qty</td>
							<td>Price</td>

						</tr>
						<c:forEach items="${sessionScope.cart}" var="item">
							<tr id=content>

								<td>${item.product.name }</td>
								<td>${item.quantity }</td>
								<td>${item.product.price*item.quantity}<c:set var="total"
										value=""></c:set>
								</td>

							</tr>

						</c:forEach>
						<tr>
							<td colspan=3><hr></td>
						</tr>
						<tr>
							<td>Total Purchase :</td>
							<td></td>
							<td>${totalPrice }</td>
						</tr>
						<tr>
							<td colspan=3><hr></td>
						</tr>
						<tr>
							<td>
								<form action="listProducts">
									<input type="submit" value="ADD MORE">
								</form>

							</td>
							<td></td>
							<td><form action="placeOrder">
									<input type="submit" value="PLACE ORDER">
								</form></td>
						</tr>
					</table>


				</c:when>
				<c:otherwise>
					<h4 align="center" color="yellow">There are no items in the
						cart</h4>
				</c:otherwise>
			</c:choose>
			<div class="footer">
				<jsp:include page="footer.jsp" />
			</div>
		</c:when>
	</c:choose>
</body>
</html>