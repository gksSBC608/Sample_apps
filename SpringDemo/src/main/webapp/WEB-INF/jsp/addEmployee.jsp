<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring Demo</title>
</head>
<body>

	<h3>Add Employee</h3>

	<form:form action="/SpringDemo/addEmployee.action" method="POST"
		commandName="employee">
		<table>
			<tr>
				<td><label>Name:</label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>Address Line 1:</label></td>
				<td><form:input path="address.line1" /></td>
				<td><form:errors path="address.line1" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>Address Line 2:</label></td>
				<td><form:input path="address.line2" /></td>
				<td><form:errors path="address.line2" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>City:</label></td>
				<td><form:input path="address.city" /></td>
				<td><form:errors path="address.city" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>State:</label></td>
				<td><form:input path="address.state" /></td>
				<td><form:errors path="address.state" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>Country:</label></td>
				<td><form:input path="address.country" /></td>
				<td><form:errors path="address.country" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>Zipcode:</label></td>
				<td><form:input path="address.zipcode" /></td>
				<td><form:errors path="address.zipcode" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td><label>Department:</label></td>
				<td><form:select path="department.id">
						<c:forEach items="${departments}" var="dep">
							<form:option value="${dep.id}">${dep.name }</form:option>
						</c:forEach>
					</form:select></td>
				<td><form:errors path="name" cssClass="error"></form:errors></td>
			</tr>
			<tr>
				<td colspan="3"><button type="submit">Add Employee</button></td>
			</tr>
		</table>
	</form:form>

</body>
</html>