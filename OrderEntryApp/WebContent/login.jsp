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

<script src="JavaScript/login.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user!=null }">
			<c:redirect url="errorPage.jsp" />
		</c:when>
		<c:when test="${sessionScope.user ==null }">

			<%@ include file="loginHeader.jsp"%>
			<h2 class="title">HOME</h2>
			<div id="content" align="center">
				<form action="login" method="post">
					<table width="35%">
						<tr>
							<th colspan=2 class="success" id="msg">${message }</th>
						</tr>

						<tr>
							<td><label class="heading">User ID*</label></td>
							<td><input type="text" name="userid" id="userid"
								onkeyup="validateAjax()" onblur="initialize1()"
								onfocus="initialize()"> <span id="idError" class=error></span></td>
							<td id="msgAjax"></td>
						</tr>
						<tr>
							<td><label class="heading">Password*</label></td>
							<td><input type="password" name="password" id="password"
								onfocus="initialize()"> <span id="pwdError" class=error></span></td>

						</tr>
						<tr >
							<td class="heading"><input type="submit" value="SUBMIT"
								onclick="return validate()" /></td>
							<td><input type="reset"></td>
						<tr>
							<td colspan=2 class=error id="msg1">${errorMsg}</td>
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