package CONTROLLER_SERVLET;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.itextpdf.text.DocumentException;

import DAO_SERVICE.PdfCreation.GeneratePDF;
import POJO_MODEL.AllStatetementComplexity;

/**
 * Servlet implementation class GenerateReport
 */
public class GenerateReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		HttpSession session = request.getSession();
		ArrayList<AllStatetementComplexity> AllComplexityList = (ArrayList<AllStatetementComplexity>) session.getAttribute("finalDisplay");
		String[] codeLines = (String[])session.getAttribute("codeLines");
		System.out.println("Post method");
		String name = request.getParameter("fileName");
		System.out.println(name);
		try {
			if(GeneratePDF.generatePDF(name,AllComplexityList,codeLines)) {
				JOptionPane pane = new JOptionPane("PDF Successfully created in E:\\GeneratedReports\\"+name+".pdf",JOptionPane.OK_OPTION);  
				JDialog dialog = pane.createDialog("Status");  
				dialog.setAlwaysOnTop(true);  
				dialog.show();
				
				request.getRequestDispatcher("Home.jsp").forward(request, response);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
