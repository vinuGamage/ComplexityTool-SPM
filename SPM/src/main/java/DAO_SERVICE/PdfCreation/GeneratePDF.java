package DAO_SERVICE.PdfCreation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import POJO_MODEL.AllStatetementComplexity;

public class GeneratePDF {

	
	public static boolean generatePDF (String fileName, ArrayList<AllStatetementComplexity> allList,String[] codeLines) throws FileNotFoundException, DocumentException {
		
		
		
		
		Document document = new Document();
		PdfWriter  writer = PdfWriter.getInstance(document, new FileOutputStream("E:\\GeneratedReports\\"+fileName+".pdf"));
		document.open();
		
		try {
			BaseFont base = BaseFont.createFont("C:\\Windows\\Fonts\\GOTHIC.TTF",BaseFont.WINANSI,BaseFont.EMBEDDED);
			
			Font pfont = new Font(base, 12, Font.BOLD ,BaseColor.DARK_GRAY);
			Font hfont = new Font(base, 10, Font.BOLD);
			Font dfont = new Font(base, 10, Font.NORMAL);
			
			document.add(new Paragraph("Complexity of the code snippet",pfont));
			
			
			PdfPTable table = new PdfPTable(9);
			table.setWidthPercentage(110);
			
			
			table.setSpacingAfter(11f);
			table.setSpacingBefore(11f);
			
			float [] colWidth = {1f,8f,1f,1f,1f,1f,1f,1f,1f};
			table.setWidths(colWidth);
			PdfPCell ch1=new PdfPCell (new Paragraph("Line Number",hfont));
			PdfPCell ch2=new PdfPCell (new Paragraph("Statement",hfont));
			PdfPCell ch3=new PdfPCell (new Paragraph("Cs",hfont));
			PdfPCell ch4=new PdfPCell (new Paragraph("Ctc",hfont));
			PdfPCell ch5=new PdfPCell (new Paragraph("Cnc",hfont));
			PdfPCell ch6=new PdfPCell (new Paragraph("Ci",hfont));
			PdfPCell ch7=new PdfPCell (new Paragraph("TW",hfont));
			PdfPCell ch8=new PdfPCell (new Paragraph("Cps",hfont));
			PdfPCell ch9=new PdfPCell (new Paragraph("Cr",hfont));
			
			ch1.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch2.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch3.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch4.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch5.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch6.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch7.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch8.setHorizontalAlignment(Element.ALIGN_CENTER);
			ch9.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			ch1.setPadding(5f);
			ch2.setPadding(5f);
			ch3.setPadding(5f);
			ch4.setPadding(5f);
			ch5.setPadding(5f);
			ch6.setPadding(5f);
			ch7.setPadding(5f);
			ch8.setPadding(5f);
			ch9.setPadding(5f);
			
			
			
			table.addCell(ch1);
			table.addCell(ch2);
			table.addCell(ch3);
			table.addCell(ch4);
			table.addCell(ch5);
			table.addCell(ch6);
			table.addCell(ch7);
			table.addCell(ch8);
			table.addCell(ch9);
			
			int x = 1;
			int TW,Cps;
			int Cp =0;
			
			 while(x<= codeLines.length){
		    	 AllStatetementComplexity all = allList.get(x-1) ;
		    	 TW = all.getCi()+all.getCtc()+all.getCnc();
		    	 Cps = TW * all.getCs() ;
		    	 
		    	 if(all.getCr()==0){
		    		 Cp=Cp + Cps;
		    	 }
		    	 else {
		    		 Cp = Cp + all.getCr();
		    	 }
			
			
		    	 	PdfPCell ch10=new PdfPCell (new Paragraph(x+"",dfont));
					PdfPCell ch11=new PdfPCell (new Paragraph(codeLines[x-1],dfont));
					PdfPCell ch12=new PdfPCell (new Paragraph(all.getCs()+"",dfont));
					PdfPCell ch13=new PdfPCell (new Paragraph(all.getCtc()+"",dfont));
					PdfPCell ch14=new PdfPCell (new Paragraph(all.getCnc()+"",dfont));
					PdfPCell ch15=new PdfPCell (new Paragraph(all.getCi()+"",dfont));
					PdfPCell ch16=new PdfPCell (new Paragraph(TW +"",dfont));
					PdfPCell ch17=new PdfPCell (new Paragraph(Cps+"",dfont));
					PdfPCell ch18=new PdfPCell (new Paragraph(all.getCr()+"",dfont));
					
					ch10.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch11.setHorizontalAlignment(Element.ALIGN_LEFT);
					ch12.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch13.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch14.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch15.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch16.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch17.setHorizontalAlignment(Element.ALIGN_CENTER);
					ch18.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					ch10.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch11.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch12.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch13.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch14.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch15.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch16.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch17.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ch18.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
					ch10.setPadding(5f);
					ch11.setPadding(5f);
					ch12.setPadding(5f);
					ch13.setPadding(5f);
					ch14.setPadding(5f);
					ch15.setPadding(5f);
					ch16.setPadding(5f);
					ch17.setPadding(5f);
					ch18.setPadding(5f);
					
					table.addCell(ch10);
					table.addCell(ch11);
					table.addCell(ch12);
					table.addCell(ch13);
					table.addCell(ch14);
					table.addCell(ch15);
					table.addCell(ch16);
					table.addCell(ch17);
					table.addCell(ch18);
		
			x++;
		     }
		
			
			
			
			
			document.add(table);
			 document.add(new Paragraph("Cps value is : " + Cp,pfont));
			document.close();
			writer.close();
			return true;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
}
