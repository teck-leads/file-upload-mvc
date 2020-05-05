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


	<table>
		<tr>

			<td>File Path</td>
			<td>${filePath }</td>
		</tr>
		<tr>
			<td>File Name</td>
			<td>${fileName }</td>
		</tr>
		<tr>
			<td>File Size</td>
			<td>${FileSize }</td>
		</tr>
		<tr>
			<td>Download file</td>
			<td><a href="/downloadFile/100">Download file</a></td>
		</tr>
	</table>


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
	<a href="/uploadfile">Home</a>
</body>
</html>