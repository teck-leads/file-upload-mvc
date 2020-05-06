<%@include file="commons/header.jspf"%>

<div class="container">

	<div class="col-md-8 col-md-offset-2">
		<h3>File upload and download with master and child tables</h3>
		<form:form enctype="multipart/form-data" modelAttribute="empCmd">
			<div class="form-group">
				<label for="name">Name</label>
				<form:input path="name" class="form-control" />
			</div>

			<div class="form-group">
				<label for="name">Address</label>
				<form:input path="address" class="form-control" />
			</div>

			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<span class="input-group-text">Upload</span>
				</div>
				<div class="custom-file">
					<input type="file" class="custom-file-input" id="inputGroupFile01">
					<form:input path="file1" type="file" class="custom-file-input" />
					<label class="custom-file-label" for="inputGroupFile01">Choose
						file</label>
				</div>
			</div>

			<input type="submit" value="Submit" class="btn btn-primary">

		</form:form>
		<a href="/emp/register">Home</a>
	</div>
</div>
<%@include file="commons/footer.jspf"%>