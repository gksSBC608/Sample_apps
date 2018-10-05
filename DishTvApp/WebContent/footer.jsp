<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<hr>
<label  class ="heading">Page created on :</label>
<jsp:useBean id="now" class="java.util.Date" scope="request" />
<span class =content><fmt:formatDate value="${now}" pattern="dd-MMM-yyyy" /></span>