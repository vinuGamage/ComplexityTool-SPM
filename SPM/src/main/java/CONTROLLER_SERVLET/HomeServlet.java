package CONTROLLER_SERVLET;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO_SERVICE.Common.Common;
import DAO_SERVICE.controlStructure_complexity.CalculateControlStructureComplexity;
import DAO_SERVICE.inheritance_complexity.CalculateInheritanceComplexity;
import DAO_SERVICE.recursion_complexity.CalculateRecursionComplexity;
import DAO_SERVICE.size_complexity.SizeCalculation;
import DAO_SERVICE.size_complexity.SizeMeasurement;
import POJO_MODEL.AllStatetementComplexity;
import POJO_MODEL.IndividualFunction;
import POJO_MODEL.StatementLine;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
		
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String code = request.getParameter("code");
		int type =  Integer.parseInt(request.getParameter("type"));
		
		PrintWriter out= response.getWriter();

	 	HttpSession session = request.getSession();
	 	int a; 
	 	String[] SessionCode = code.split("\\r?\\n");
	 	String[] sizeCode = code.split("\\r?\\n");
	 	String[] tempStArray = code.split("\\r?\\n"); ;// split on new lines
	 	String[] stArray;
		session.setAttribute("codeLines", code.split("\\r?\\n"));
		int x = tempStArray.length;
		int y;
		int z;
	 	
		System.out.println("The original code ---------------------- ");
	 	for(y=0;y<x;y++) {
			 
			 out.println("Line " + (y+1) + "  : " + tempStArray[y]);
			 
		 }
	 	
	 	// removes only comments - Cs
	 	for(a=0;a<sizeCode.length;a++) {
	 		
	 		if(sizeCode[a].contains("//")) {
	 			sizeCode[a] = "";
	 		}
	 	}
	 	// removes printing and comments
	 	for(a=0;a<tempStArray.length;a++) {
	 		if(tempStArray[a].contains("System.out")) {
	 			tempStArray[a] = "Printing Statement";
	 		}
	 		if(tempStArray[a].contains("//")) {
	 			tempStArray[a] = "";
	 		}
	 	}
	 	
	 	 	
	 	stArray = tempStArray;
	 	System.out.println("The Sysout removed code ---------------------- ");
		x = stArray.length;
		 for(y=0;y<x;y++) {
			 
			System.out.println("Line " + (y+1) + "  : " + stArray[y]);
			 
		 }
		 
		 ArrayList<IndividualFunction> allFunctions = 	Common.divideToFunctions(stArray); // Calling the method
		 for(y=0; y<allFunctions.size();y++) {
		 		IndividualFunction f2 = allFunctions.get(y);
		 		out.println(f2.getStart());
		 		out.println(f2.getEnd());
		 		out.println(f2.getMethodName());
		 	}
		 
		 
		 
		 if(type ==1) {
			 System.out.println("Size----------------------------------------------------------------------------------");
			 
//			 ArrayList<StatementLine> recursionSize = new ArrayList<>();
//			 
//			for(y=0;y<stArray.length;y++) {
//				SizeMeasurement s1 = new SizeMeasurement(stArray[y]);
				
//			System.out.println(" Line number :" + (y+1) + "complexity is : " + s1.getSizeComplexity());	
//		recursionSize.add(new StatementLine(y+1,(int) s1.getSizeComplexity()));
//			}
//			 SizeMeasurement s2 = new SizeMeasurement("public static void main(String args[]) { ");
//			 System.out.println(s2.getSizeComplexity());
//			session.setAttribute("Cs", recursionSize);
//			request.getRequestDispatcher("DisplayComplexityOfSize.jsp").forward(request, response);
			
			 ArrayList<String> lines = new ArrayList<>();
			 
			 for(y=0;y<sizeCode.length;y++) {
				 System.out.println("Index Number :" + y +" Line Number : " + (y+1) + "Code : " + sizeCode[y] );
				 lines.add(sizeCode[y]);
			 }
			 
			 System.out.println("--------------------------- ArrayList----------------------------------------------");
			 for(y=0;y<lines.size();y++) {
				 System.out.println("Index Number :" + y +" Line Number : " + (y+1) + "Code : " + lines.get(y) );
			 }
			
			 System.out.println("--------------------------- Call Method----------------------------------------------");
			 System.out.println("");
			 
			 SizeCalculation sizeCal =  new SizeCalculation(lines);
			 ArrayList<StatementLine> outputResults =  sizeCal.calculateComplexityDueSize();
			 
			 for(y=0;y<outputResults.size();y++) {
				 System.out.println("Line number : " + outputResults.get(y).getLineNumber() + " Cs : " + outputResults.get(y).getComplexity() );
			 }
			 
			 session.setAttribute("Cs", outputResults);
				request.getRequestDispatcher("DisplayComplexityOfSize.jsp").forward(request, response);
			 
			 
		 }
		 
		 if(type ==2) {
			 System.out.println("Control Structure");	
			 ArrayList<StatementLine> StatementListCtc = CalculateControlStructureComplexity.calculateComplexityByType(allFunctions, stArray);
			 
			 
			 out.println("Ctc Value for lines -------------------------------------------------------------------------------");
			 
			 for(y=0;y<StatementListCtc.size();y++) {
				 StatementListCtc.get(y);
				 out.println("Line Number " + StatementListCtc.get(y).getLineNumber() + ": Ctc :  " + StatementListCtc.get(y).getComplexity());
			 }
			 
			 ArrayList<StatementLine> StatementListCnc =  CalculateControlStructureComplexity.calculateComplexityByNestingControlStructure(allFunctions, stArray);
		 
			 out.println("Cnc Value for lines -------------------------------------------------------------------------------");
			 
			 for(y=0;y<StatementListCnc.size();y++) {
				 StatementListCnc.get(y);
				 out.println("Line Number " + StatementListCnc.get(y).getLineNumber() + ": Cnc :  " + StatementListCnc.get(y).getComplexity());
			 }
			 
			 

				session.setAttribute("Ctc", StatementListCtc);
				session.setAttribute("Cnc", StatementListCnc);
				request.getRequestDispatcher("DisplayComplexityOfControlStructure.jsp").forward(request, response);
		 }
		 
		 if(type ==3) {
			 System.out.println("Inheritance");
			 
			 int ci = CalculateInheritanceComplexity.CalculateCi(stArray);
			 ArrayList<StatementLine> inheritanceList = new ArrayList<>();
			 
			 for(y=0;y<stArray.length;y++) {
				 inheritanceList.add(new StatementLine(y+1, ci));
				}
			 
			 for(y=0;y<inheritanceList.size();y++) {
				 if (stArray[inheritanceList.get(y).getLineNumber() -1].isBlank()) {
					 inheritanceList.get(y).setComplexity(0);
				 }
			 }
			 
			 for(y=0;y<inheritanceList.size();y++) {
				 if (stArray[inheritanceList.get(y).getLineNumber() -1].contains("}")) {
					 inheritanceList.get(y).setComplexity(0);
				 }
			 }
			 
			 
			 session.setAttribute("Ci", inheritanceList);
			 request.getRequestDispatcher("DisplayComplexityOfInheritance.jsp").forward(request, response);
		 }
		 
		 if(type ==4) {
			 System.out.println("Recursion----------------------------------------------------------------------------------------");
			 ArrayList<StatementLine> StatementListCr =  CalculateRecursionComplexity.calculateComplexityByRecursion(stArray,sizeCode);
			 session.setAttribute("Cr", StatementListCr);
			 
			 out.println("Recursion ----------------------------------------------------------------------------------------------------------------------");
			 
			 for(y=0;y<StatementListCr.size();y++) {
				 StatementListCr.get(y);
				 out.println("Line Number " + StatementListCr.get(y).getLineNumber() + ": Cr :  " + StatementListCr.get(y).getComplexity());
			 }
			 
			 request.getRequestDispatcher("DisplayComplexityOfRecursion.jsp").forward(request, response);
		 }
		 
		 if(type==5) {
			 
			 
		System.out.println("Size----------------------------------------------------------------------------------");
			 
					 
					 
//					for(y=0;y<stArray.length;y++) {
//						SizeMeasurement s1 = new SizeMeasurement(stArray[y]);
//						
//					System.out.println(" Line number :" + (y+1) + "complexity is : " + s1.getSizeComplexity());	
//				recursionSize.add(new StatementLine(y+1,(int) s1.getSizeComplexity()));
					 
					 ArrayList<String> lines = new ArrayList<>();
					 
					 for(y=0;y<sizeCode.length;y++) {
						 System.out.println("Index Number :" + y +" Line Number : " + (y+1) + "Code : " + sizeCode[y] );
						 lines.add(sizeCode[y]);
					 }
					 
					 System.out.println("--------------------------- ArrayList----------------------------------------------");
					 for(y=0;y<lines.size();y++) {
						 System.out.println("Index Number :" + y +" Line Number : " + (y+1) + "Code : " + lines.get(y) );
					 }
					
					 System.out.println("--------------------------- Call Method----------------------------------------------");
					 System.out.println("");
					 
					 SizeCalculation sizeCal =  new SizeCalculation(lines);
					 ArrayList<StatementLine> recursionSize =  sizeCal.calculateComplexityDueSize();
					 
					
					

			 
			 
					
		System.out.println("Control Structure ---------------------------------------------------------------------------");	
					 ArrayList<StatementLine> StatementListCtc = CalculateControlStructureComplexity.calculateComplexityByType(allFunctions, stArray);
					 
					 
					 out.println("Ctc Value for lines -------------------------------------------------------------------------------");
					 
					 for(y=0;y<StatementListCtc.size();y++) {
						 StatementListCtc.get(y);
						 out.println("Line Number " + StatementListCtc.get(y).getLineNumber() + ": Ctc :  " + StatementListCtc.get(y).getComplexity());
					 }
					 
					 ArrayList<StatementLine> StatementListCnc =  CalculateControlStructureComplexity.calculateComplexityByNestingControlStructure(allFunctions, stArray);
				 
					 out.println("Cnc Value for lines -------------------------------------------------------------------------------");
					 
					 for(y=0;y<StatementListCnc.size();y++) {
						 StatementListCnc.get(y);
						 out.println("Line Number " + StatementListCnc.get(y).getLineNumber() + ": Cnc :  " + StatementListCnc.get(y).getComplexity());
					 }
					 

		System.out.println("Inheritance--------------------------------------------------------------------------------------------------------");
						 
						 int ci = CalculateInheritanceComplexity.CalculateCi(stArray);
						 ArrayList<StatementLine> inheritanceList = new ArrayList<>();
						 
						 for(y=0;y<stArray.length;y++) {
							 inheritanceList.add(new StatementLine(y+1, ci));
							}
						 
						 for(y=0;y<inheritanceList.size();y++) {
							 if (stArray[inheritanceList.get(y).getLineNumber() -1].isBlank()) {
								 inheritanceList.get(y).setComplexity(0);
							 }
						 }
						 
						 for(y=0;y<inheritanceList.size();y++) {
							 if (stArray[inheritanceList.get(y).getLineNumber() -1].contains("}")) {
								 inheritanceList.get(y).setComplexity(0);
							 }
						 }

		System.out.println("Recursion----------------------------------------------------------------------------------------");
						 ArrayList<StatementLine> StatementListCr =  CalculateRecursionComplexity.calculateComplexityByRecursion(stArray,sizeCode);
						 session.setAttribute("Cr", StatementListCr);	 
		 
		 
		
		 int e = 0; 
		 
		 ArrayList<AllStatetementComplexity> FinalDisplayList = new ArrayList<>();
			while (e< SessionCode.length) {
				 System.out.println("Type 5 ------------ ");
				 System.out.println("Line Number Cs : " + recursionSize.get(e).getLineNumber() +" is : " +recursionSize.get(e).getComplexity());
				 System.out.println("Line Number Ctc : " + StatementListCtc.get(e).getLineNumber()+" is : " + StatementListCtc.get(e).getComplexity() );
				 System.out.println("Line Number Cnc : " + StatementListCnc.get(e).getLineNumber()+" is : " + StatementListCnc.get(e).getComplexity());
				 System.out.println("Line Number Ci : " + inheritanceList.get(e).getLineNumber()+" is : " + inheritanceList.get(e).getComplexity());
				 System.out.println("Line Number Cr : " + StatementListCr.get(e).getLineNumber()+" is : " + StatementListCr.get(e).getComplexity());
				 
				 FinalDisplayList.add(new AllStatetementComplexity(recursionSize.get(e).getLineNumber(), recursionSize.get(e).getComplexity(), StatementListCtc.get(e).getComplexity(), StatementListCnc.get(e).getComplexity(), inheritanceList.get(e).getComplexity(), StatementListCr.get(e).getComplexity()));
				 e++;
				}
			 session.setAttribute("finalDisplay", FinalDisplayList);
			 request.getRequestDispatcher("DisplayComplexityAll.jsp").forward(request, response);
		 
		 }
		 
		

		 
		 
	}
	
	
	
}
