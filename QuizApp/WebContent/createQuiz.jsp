<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" language="javascript"
	src="JavaScript/createQuiz.js"></script>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">
</head>
<body >
	<%@ include file="header.html"%>

	<h2>Quiz Creator</h2>


	<c:if test="${options==null}">
		<form action="createOptions" method="post">
	</c:if>
	<c:if test="${options!=null}">
		<form action="submitOptions" method="post">
	</c:if>
	<table  width ="1000">
		<tr>
			<th>Add a new Question</th>
		</tr>
		<tr>
			<td class = "heading"><label >Quiz name</label></td>
			<td class = "content"><label>${topic }</label></td>
		</tr>
		<c:set var="topic" value="${topic}" scope="session" />
		<tr>
			<td class = "heading"><label >Question Text</label></td>
			<td class = content><input type="text" name="question" id="question" size="15" onfocus= "return initializeQError()"></td>
			<td id="questionError" class="error" ></td>
		</tr>
		<c:if test="${options==null}">
			<tr>
				<td class = "heading"><label>How many Options?</label></td>
				<td class = content><select name="optNo" id="optCount" >
						<option value="select">-select-</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
				</select></td>
				<td id="selectionError" class="error"></td>
			</tr>
			<tr class =heading>
				<td><input type="hidden" name="quiz" value="${topic}">
					<input type="submit" value="SUBMIT" onclick="return validate()"></td>
				<td><input type="button" value="BACK" onclick = "redirectHome()"></td>
			</tr>



		</c:if>
		<c:if test="${options!=null}">
			<tr>
				<td class =heading><label>Number of Options</label></td>
				<td class = content><label>${options }</label></td>
			<tr>
				<th>Options</th>
				<th>Is Option correct</th>

				<c:forEach var="i" begin="1" end="${options }">

					<tr >
						<td class = content><input type="text" name="opt${i}" id="opt${i}" onfocus = " initialize(${options })"></td>
						<td class = content><input type="radio" checked="checked" name=choice
							value="${i}"></td>
						<td class="error" id="err${i}"></td>
					</tr>
				</c:forEach>
			</tr>
			<tr class = heading>
				<td ><input type="hidden" name="quiz" value="${topic}">
					<input type="hidden" name="countOptions" value="${options}">
					<input type="submit" value="SUBMIT"
					onclick="return validateOptions(${options})"></td>
				<td ><input type="button" value="BACK" onclick = "func()"></td>
			</tr>
		</c:if>

	</table>

	</form>

	<jsp:include page="footer.jsp" />
</body>
</html>