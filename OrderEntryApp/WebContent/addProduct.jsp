<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="JavaScript/addProduct.js"></script>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">

</head>
<body>
	<c:if test="${ user ==null}">
		<c:redirect url="login.jsp" />
	</c:if>
	<%@ include file="header.jsp"%>
	<c:choose>
		<c:when test="${sessionScope.user==null }">
			<c:redirect url="login.jsp" />
		</c:when>
		<c:when test="${sessionScope.user !=null }">
			<div align="center">
				<form action="addProductDetail" method="post">
					<table>
						<tr>
							<th>Give description of the product</th>
						</tr>
						<tr>

							<td><label>Enter Product ID</label></td>
							<td>:</td>
							<td><input type="text" name="id" id="id"> <span
								id="idError" class="error"></span></td>
						</tr>
						<tr>
							<td><label>Enter Product Name</label></td>
							<td>:</td>
							<td><input type="text" name="name" id="name"> <span
								id="nameError" class="error"></span></td>
						</tr>
						<tr>
							<td><label>Enter category</label></td>
							<td>:</td>
							<td><input type="text" name="category" id="category">
								<span id="categoryError" class="error"></span></td>
						</tr>
						<tr>
							<td><label>Enter Price</label></td>
							<td>:</td>
							<td><input type="text" name="price" id="price"> <span
								id="priceError" class="error"></span></td>

						</tr>
						<tr>
							<td><label>Enter description</label></td>
							<td>:</td>
							<td><textarea name="detail" id="detail"></textarea> <span
								id="detailError" class="error"></span></td>
						</tr>
						<tr>
							<td><input type="submit" value="SUBMIT"
								onclick="return validate()" /></td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
			<div class="footer">
				<jsp:include page="footer.jsp" />
			</div>
		</c:when>
	</c:choose>
</body>
</html>