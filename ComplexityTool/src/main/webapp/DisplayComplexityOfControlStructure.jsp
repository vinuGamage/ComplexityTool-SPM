<%@page import="POJO_MODEL.StatementLine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type ="text/css" rel="stylesheet" href="resources/bootstrap/css/bootstrap.css" >
<title>Complexity of Control Structure </title>

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
		
		<div class="container" style="overflow: auto;min-height: 500px"> 
		
			<div align="center">
	
				
				<h1 class="display-1"
					style="center: 2%; margin-left: 100px; font-family: century gothic; font-size: 50px;margin-top: 10px">
					<font color="#00376c">Complexity of Control Structure</font>
				</h1>
				<br> 
	
			</div>
			
			 <table class="table" style=" font-family: century gothic" cellpadding="20px" cellspacing="20px" align="center" border="2"  >
             <thead>
              <tr>
                    <th style="font=weight:bold;">Line Number</th>
                    <th style="font=weight:bold;">Statement</th>
                    <th style="font=weight:bold;">Ctc</th>
                    <th style="font=weight:bold;">Cnc</th>
                    <th style="font=weight:bold;">Ctc + Cnc</th>
                    
              </tr>
              </thead>
               
		
		<%
		
		int x = 1;
		
		String[] codeLines = (String[])session.getAttribute("codeLines");
		ArrayList<StatementLine> StatementListCtc = (ArrayList<StatementLine>)session.getAttribute("Ctc");
		ArrayList<StatementLine> StatementListCnc = (ArrayList<StatementLine>)session.getAttribute("Cnc");
		
	     while(x<= codeLines.length){
	    	 StatementLine ctc = StatementListCtc.get(x-1) ;
	    	 StatementLine cnc = StatementListCnc.get(x-1) ;
		%>
		
		<tr>
			<td><%=x %></td>
			<td><%=codeLines[x-1].replace(" ", "&nbsp;") %></td>
			<td><%=ctc.getComplexity() %></td>
			<td><%=cnc.getComplexity() %></td>
			<td><%=ctc.getComplexity() + cnc.getComplexity() %></td>
		</tr>
		
		<%
		x++;
	     }
		%>
		 </table>    
		</div>
		
		
		
		</body>
</html>