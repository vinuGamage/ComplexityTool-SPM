<%@page import="POJO_MODEL.AllStatetementComplexity"%>
<%@page import="POJO_MODEL.StatementLine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type ="text/css" rel="stylesheet" href="resources/bootstrap/css/bootstrap.css" >
<title>Complexity of All </title>

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
					<font color="#00376c">Complexity of Size</font>
				</h1>
				<br> 
	
			</div>
			
			 <table class="table" style=" font-family: century gothic" cellpadding="20px" cellspacing="20px" align="center" border="2"  >
             <thead>
              <tr>
                    <th style="font=weight:bold;">Line Number</th>
                    <th style="font=weight:bold;">Statement</th>
                    <th style="font=weight:bold;">Cs</th>
                    <th style="font=weight:bold;">Ctc</th>
                    <th style="font=weight:bold;">Cnc</th>
                    <th style="font=weight:bold;">Ci</th>
                    <th style="font=weight:bold;">TW</th>
                    <th style="font=weight:bold;">Cps</th>
                    <th style="font=weight:bold;">Cr</th>
                    
                    
              </tr>
              </thead>
               
		
		<%
		
		int x = 1;
		int TW,Cps;
		int Cp =0;
		
		String[] codeLines = (String[])session.getAttribute("codeLines");
		ArrayList<AllStatetementComplexity> AllComplexityList = (ArrayList<AllStatetementComplexity>)session.getAttribute("finalDisplay");
		
		
	     while(x<= codeLines.length){
	    	 AllStatetementComplexity all = AllComplexityList.get(x-1) ;
	    	 TW = all.getCi()+all.getCtc()+all.getCnc();
	    	 Cps = TW * all.getCs() ;
	    	 
	    	 if(all.getCr()==0){
	    		 Cp=Cp + Cps;
	    	 }
	    	 else {
	    		 Cp = Cp + all.getCr();
	    	 }
		%>
		
		<tr>
			<td><%=x %></td>
			<td><%=codeLines[x-1].replace("  ", "&nbsp;") %></td>
			<td><%=all.getCs() %></td>
			<td><%=all.getCtc() %></td>
			<td><%=all.getCnc() %></td>
			<td><%=all.getCi() %></td>
			<td><%=TW %></td>
			<td><%=Cps %></td>
			<td><%=all.getCr() %></td>
			
		</tr>
		
		<%
		x++;
	     }
		%>
		<td></td>
		<td>Cp</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td><%=Cp %></td>
		 </table>    
		</div>
		
		
	</div>
		
		<div class="container" style="height:100px">
			<form action="GenerateReport" class="form" method="POST" style="font-family: century gothic;">

			

			<div class="form-group" style="font-size: 25px; font-weight: normal;">
				<label for="code">Enter File Name:</label>
				<input type="text" class="form-control" name="fileName">
			</div>



			<button type="submit" class="btn btn-primary btn-lg">Generate Report</button>
		</form>
		</div>
		</body>
</html>