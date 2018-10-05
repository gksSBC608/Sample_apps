<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="com.mindtree.service.*, java.util.*, com.mindtree.entity.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my"	uri="WEB-INF/tags/quiz.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/myStyle.css" type="text/css"
	media="screen">
</head>
<body>
	<%@ include file="header.html"%>
	
	<my:QuizNamesList quizList="${requestScope.quizes}" successMsg= "${message }" />
	<table width="60%">
		<tr>
			<th>Quiz Name</th>
		</tr>
		
		<c:forEach items="${quizes }" var="quiz">
		
			<tr>
				<td class = "heading"><label>${quiz.name }</label></td>
				<td class = content><a href="addQuestion?name=${quiz.name}">Add Question</a></td>
				<td class =  content><a href="backup?id=${quiz.id }">Generate Quiz Report</a></td>
			</tr>
		</c:forEach>
		
	</table>
	
	<jsp:include page="footer.jsp" />
</body>
</html>


