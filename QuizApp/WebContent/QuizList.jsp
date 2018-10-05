<h3>Quiz List</h3>
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
	
	
	<%@ include file="footer.jsp"%>