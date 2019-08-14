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
import DAO_SERVICE.recursion_complexity.CalculateRecursionComplexity;
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
		int language =  Integer.parseInt(request.getParameter("language"));
		PrintWriter out= response.getWriter();

	 	HttpSession session = request.getSession();

	 	String[] tempStArray ;
		
		String[] stArray = code.split("\\r?\\n"); // split on new lines
		session.setAttribute("codeLines", stArray);
		int x = stArray.length;
		int y;
		int z;

		 for(y=0;y<x;y++) {
			 
			 out.println("Line " + (y+1) + "  : " + stArray[y]);
			 
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
			 ArrayList<StatementLine> recursionSize = new ArrayList<>();
			 
				for(y=0;y<stArray.length;y++) {
					SizeMeasurement s1 = new SizeMeasurement(stArray[y]);
					
				System.out.println(" Line number :" + (y+1) + "complexity is : " + s1.getSizeComplexity());	
			recursionSize.add(new StatementLine(y+1,(int) s1.getSizeComplexity()));
				}
//				 SizeMeasurement s2 = new SizeMeasurement("public static void main(String args[]) { ");
//				 System.out.println(s2.getSizeComplexity());
				session.setAttribute("Cs", recursionSize);
				request.getRequestDispatcher("DisplayComplexityOfSize.jsp").forward(request, response);
			 }
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
		 }
		 
		 if(type ==4) {
			 System.out.println("Recursion----------------------------------------------------------------------------------------");
			 ArrayList<StatementLine> StatementListCr =  CalculateRecursionComplexity.calculateComplexityByRecursion(stArray);
			 session.setAttribute("Cr", StatementListCr);
			 
			 out.println("Recursion ----------------------------------------------------------------------------------------------------------------------");
			 
			 for(y=0;y<StatementListCr.size();y++) {
				 StatementListCr.get(y);
				 out.println("Line Number " + StatementListCr.get(y).getLineNumber() + ": Cr :  " + StatementListCr.get(y).getComplexity());
			 }
			 
			 request.getRequestDispatcher("DisplayComplexityOfRecursion.jsp").forward(request, response);
		 }
		 	
		 
		 	
		 
	}
	
	
	
}
