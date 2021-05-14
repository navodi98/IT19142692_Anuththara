<%@page import="com.ProjectManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/projects.js"></script>
</head>
<body>
 
<div class="container"><div class="row"><div class="col-6"> 

	<h1>Project Management</h1>
	<form id="formProject" name="formProject">
 		Project ID: 
 		<input id="projectID" name="projectID" type="text" class="form-control form-control-sm">
 	<br> Project Name: 
 	<input id="projectName" name="projectName" type="text" class="form-control form-control-sm">
 	<br> Project Type: 
 	<input id="projectType" name="projectType" type="text" class="form-control form-control-sm">
 	<br> Project Description: 
 	<input id="projectDescription" name="projectDescription" type="text" class="form-control form-control-sm">
 	<br>
 	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 	<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divItemsGrid">
	 <%
	 	Project proj = new Project(); 
 		out.print(proj.readProjects()); 
 	 %>
</div>

</div> </div> </div> 
</body>
</html>