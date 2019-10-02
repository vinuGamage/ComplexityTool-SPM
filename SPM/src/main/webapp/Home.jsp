<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">


<link type ="text/css" rel="stylesheet" href="resources/bootstrap/css/bootstrap.css" >

<title>Home Page</title>
<style>
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-color: #00376c;
	font-family: century gothic;
	font-size: 25px;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not (.active ) {
	background-color: #ffd700;
}

.active {
	background-color: #ffd700;
}
</style>
</head>
<body>


	<ul>
		<li><a href="Home.jsp">Home</a></li>
		<li><a href="Guide.jsp">Guide</a></li>

	</ul>

	<div class="container" style="height: 500px">
		<div align="center">

			<br> <br>
			<br> <br>
			<br> <br> <img src="resources/images/logo.png"  class="img-circle" alt="Cinque Terre">

			<h1 class="display-1"
				style="center: 2%; margin-left: 100px; font-family: century gothic; font-size: 5vw">
				<font color="#00376c">Try Complexity Cal now!!</font>
			</h1>
			<br> <br>

		</div>

		<form action="HomeServlet" class="form" method="POST" style="font-family: century gothic;">

			<div class="form-group" style="font-size: 20px; font-weight: normal;">
				<label for="complexity">Complexity Type:</label> 
				<select class="custom-select" name ="type">

					<option value="1">By Size</option>
					<option value="2">By Control Structure</option>
					<option value="3">By Inheritance</option>
					<option value="4">By Recursion</option>
					<option value="5">By All</option>
				</select>
			</div>
			

			<div class="form-group" style="font-size: 25px; font-weight: normal;">
				<label for="code">Enter Code:</label>
				<textarea class="form-control" rows="10" id="code" name="code"></textarea>
			</div>

			<br>

			<button type="submit" class="btn btn-primary btn-lg">Submit</button>
		</form>
	</div>


</body>
</html>