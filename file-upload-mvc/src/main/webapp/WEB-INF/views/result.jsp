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
			<td>Name</td>
			<td>${employee.name }</td>
		</tr>
		<tr>
		<td>Address</td>
			<td>${employee.address }</td>
		</tr>
		<tr>
			<td>${employee.fileUpload.name }</td>
			<td><a href="/emp/downloadFile/${employee.uuidCode }">Download
					File</a></td>
		</tr>
	</table>


	<a href="/emp/register">Home</a>
</body>
</html>