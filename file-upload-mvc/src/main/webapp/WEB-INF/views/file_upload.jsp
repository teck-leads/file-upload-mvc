<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>File upload</h1>
<h4>${fileName }</h4>
<h4>${FileSize }</h4>

	<form:form enctype="multipart/form-data" modelAttribute="empCmd">

name: <form:input path="name" />
		<br>
Address: <form:input path="address" />
		<br>
File-1: <form:input path="file1" type="file" />
		<br>
File-2: <form:input path="file1" type="file" />
		<br>
		<input type="submit" value="Submit">

	</form:form>
</body>
</html>