<%@include file="commons/header.jspf"%>


<div class="container">
	<div class="col-md-8 col-md-offset-2">
	<h3>File upload and download with master and child tables</h3>
		<table class="table">
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
	</div>
</div>
<%@include file="commons/footer.jspf"%>