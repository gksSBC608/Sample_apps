<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">

<script src="JavaScript/subscribeChannel.js">
	
</script>

</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user==null }">
			<c:redirect url="login.jsp" />
		</c:when>
		<c:when test="${sessionScope.user !=null }">
			<jsp:include page="header.jsp" />
			<h2 class="title">DTH Subscription</h2>
			<div align="center">
				<form action="/DishTvApp/subscribeChannel" method="post">
					<table>
						<tr class="subtitle">
							<th colspan=2></th>
						</tr>
						<tr>
							<td><label class="heading">Customer Subscription ID</label></td>
							<td>:</td>
							<td><input type="number" name="subId" id="subscriptionId"
								onfocus="return initialize()" /></td>
							<td id="errorId" class="error"></td>
						</tr>
						<tr>
							<td><label class="heading">Channel </label></td>
							<td>:</td>
							<td><select name="channel" id="channel"
								onfocus="return initialize()">
									<option value="select">select</option>
									<c:forEach items="${channelList }" var="channel">
										<option value="${channel.getChanelId() }">${channel.channelName }</option>

									</c:forEach>
							</select></td>
							<td id="errorChannel" class="error"></td>
						</tr>
						<tr>
							<td><label class="heading">Date of Subscription </label></td>
							<td>:</td>
							<td><input type="text" name=subDate id="subDate"
								onfocus="return initialize()" /></td>
							<td id="errorDate" class="error"></td>
						</tr>
						<tr>
							<td class="heading"><input type="submit" value="SUBMIT"
								onclick="return validate()"></td>
							<td></td>
							<td class="heading"><input type="reset" value="CLEAR" /></td>
						</tr>
					</table>
				</form>
			</div>
			<jsp:include page="footer.jsp" />
		</c:when>
	</c:choose>
</body>
</html>