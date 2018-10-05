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
<script src="JavaScript/viewSubscription.js">
	
</script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.user==null }">
			<c:redirect url="login.jsp" />
		</c:when>
		<c:when test="${sessionScope.user !=null }">
			<%@ include file="header.jsp"%>
			<h2 class="title">View Subscriptions</h2>
			<div align=center>
				<!-- <form action="/DishTvApp/viewDetail" method="post"> -->
				<form>
					<table>

						<tr>
							<td><label class="heading">Subscription ID*</label></td>
							<td><input type="text" name="id" id="id"
								onblur="getChannelsDetail();"></td>

						</tr>
						<tr>
							<td class="heading"><input type="button" value="SUBMIT"
								onclick="return getChannelsDetail();" /></td>
						</tr>
					</table>
				</form>
			</div>
			<div id="myDiv"></div>
			<%-- <c:if test="${detail != null}">


				<h2>Subscription Summary</h2>
				<table width="50%">
					<tr id=formField>
						<td><label>Subscription ID : </label></td>
						<td><c:out value="${detail.subscriptionId}" /></td>
					</tr>
					<tr id=formField>
						<td><label>Customer name : </label></td>
						<td><c:out value="${detail.customerName }" /></td>
					</tr>
					<tr id=formField>
						<td><label>Total Subscription Cost </label></td>
						<td><c:out value="${detail.totalCost }" /></td>
					</tr>

				</table>
				<br>
				<br>
				<table width="100%">
					<tr>
						<th colspan=3 align=left>Channels Subscribed :</th>
					</tr>
					<tr id=topic>

						<td>Channel Name</td>
						<td>Cost Per Month</td>
					</tr>
					<c:forEach items="${detail.subscribedChannel}" var="item">
						<tr id=content>

							<td>${item.channelName }</td>
							<td>${item.costPerMonth }</td>

						</tr>

					</c:forEach>
				</table>

			</c:if> 
			--%>

			<jsp:include page="footer.jsp" />
		</c:when>
	</c:choose>
</body>
</html>