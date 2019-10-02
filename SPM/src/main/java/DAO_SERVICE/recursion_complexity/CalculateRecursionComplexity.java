package DAO_SERVICE.recursion_complexity;

import java.util.ArrayList;

import DAO_SERVICE.Common.Common;
import DAO_SERVICE.controlStructure_complexity.CalculateControlStructureComplexity;
import DAO_SERVICE.inheritance_complexity.CalculateInheritanceComplexity;
import DAO_SERVICE.size_complexity.SizeCalculation;
import DAO_SERVICE.size_complexity.SizeMeasurement;
import POJO_MODEL.IndividualFunction;
import POJO_MODEL.StatementLine;

public class CalculateRecursionComplexity {
	
	
	
	public static ArrayList<StatementLine> calculateComplexityByRecursion ( String[] stArray,String[] sizeCode) {
		
		int x,y;
		int startFunction,endFunction;
		String methodName;
		int CtcLine, CncLine;
		int CiLine = 1 , CsLine =1;
		boolean isRecursion = false;
		
		ArrayList<IndividualFunction> allFunctions = 	Common.divideToFunctions(stArray); 
		ArrayList<IndividualFunction> recursionFunctions = new ArrayList<>();
		
		ArrayList<StatementLine> recursionStatementLines = new ArrayList<>();
		System.out.println("----------------------------------------------------------------------------------------------------");
		for(y=0; y<allFunctions.size();y++) {
	 		IndividualFunction f2 = allFunctions.get(y);
	 		System.out.println(f2.getStart());
	 		System.out.println(f2.getEnd());
	 		System.out.println(f2.getMethodName());
	 	}
		
		
		ArrayList<StatementLine> recursionCtc  =CalculateControlStructureComplexity.calculateComplexityByType(allFunctions, stArray);
		ArrayList<StatementLine> recursionCnc = CalculateControlStructureComplexity.calculateComplexityByNestingControlStructure(allFunctions, stArray);
		
		
		 ArrayList<String> lines = new ArrayList<>();
		 
		 for(y=0;y<sizeCode.length;y++) {
			 System.out.println("Index Number :" + y +" Line Number : " + (y+1) + "Code : " + sizeCode[y] );
			 lines.add(sizeCode[y]);
		 }
		 
		 SizeCalculation sizeCal =  new SizeCalculation(lines);
		 ArrayList<StatementLine> recursionCs =  sizeCal.calculateComplexityDueSize();
		
		// recursionInheritance
		for(x=0;x<allFunctions.size();x++) {
			isRecursion = false;
			
			startFunction = allFunctions.get(x).getStart()+1;
			endFunction = allFunctions.get(x).getEnd();
			methodName = allFunctions.get(x).getMethodName();
			
			while(startFunction<endFunction) {
				System.out.println("Inside Line : " + startFunction);
				if(stArray[startFunction].contains(methodName)) {
					isRecursion = true;
				}
				startFunction++;
			}
			if(isRecursion) {
				System.out.println("the x value is :" + x);
				recursionFunctions.add(allFunctions.get(x));
			}
			
		}
		
		for(y=0;y<recursionFunctions.size();y++) {
		
			System.out.println("Function Name :" + recursionFunctions.get(y).getMethodName());
			System.out.println("Start Line :" + (recursionFunctions.get(y).getStart() +1));
			System.out.println("End Line :" + (recursionFunctions.get(y).getEnd() +1));
			
			System.out.println("");
		
		}
		
		
		
		for(int a=0;a<recursionFunctions.size();a++) {
			IndividualFunction f2 = recursionFunctions.get(a);
			startFunction =f2.getStart() +1;
			endFunction = f2.getEnd()+1;
			System.out.println("Recursion Functions Cnc and Ctc and others---------------------------------------------------------");
			while(startFunction<=endFunction) {
				CncLine = 0 ;
				CtcLine = 0 ;
				CiLine = CalculateInheritanceComplexity.CalculateCi(stArray);
				if(stArray[startFunction-1].isBlank()) {
					CiLine = 0;
				}
				CsLine =1 ;
				for(y=0;y<recursionCtc.size();y++) {
					if(startFunction == recursionCtc.get(y).getLineNumber()) {
						System.out.println("Line number :" + startFunction + " Recursion Ctc is : " + recursionCtc.get(y).getComplexity());
						CtcLine = recursionCtc.get(y).getComplexity();
					}
				}
				
			
				for(y=0;y<recursionCnc.size();y++) {
					if(startFunction == recursionCnc.get(y).getLineNumber()) {
						System.out.println("Line number :" + startFunction + " Recursion Cnc is : " + recursionCnc.get(y).getComplexity());
						CncLine = recursionCnc.get(y).getComplexity();
					}
				}
				
				for(y=0;y<recursionCs.size();y++) {
					if(startFunction == recursionCs.get(y).getLineNumber()) {
						System.out.println("Line number :" + startFunction + " Recursion Cnc is : " + recursionCs.get(y).getComplexity());
						CsLine = recursionCs.get(y).getComplexity();
					}
				}
				
			
//				CsLine =  (int) new SizeMeasurement(stArray[startFunction-1]).getSizeComplexity();
				System.out.println("the line is : "  + stArray[startFunction-1]);
				System.out.println("Ctc for  Line : " + startFunction + "  is : " + CtcLine);
				System.out.println("Cnc for  Line : " + startFunction + "  is : " + CncLine);
				System.out.println("Ci for  Line : " + startFunction + "  is : " + CiLine);
				System.out.println("Cs for  Line : " + startFunction + "  is : " + CsLine);
				
				System.out.println("");
				recursionStatementLines.add(new StatementLine(startFunction,( (CtcLine+CncLine+CiLine) * (CsLine)) * (2))); 
				
				//recursion inheritence for loop
				//recursion size for loop
				
				startFunction++;
			}
			
			
		}
		
		boolean isLinePresent;
		 ArrayList<StatementLine> displayCrList = new ArrayList<>();
			
			for(y=0;y<stArray.length;y++) {
				isLinePresent =true;
				for (int a=0;a<recursionStatementLines.size();a++) {
					if(recursionStatementLines.get(a).getLineNumber() == y) {
						isLinePresent = false;
					}
				}
				if(isLinePresent) {
					recursionStatementLines.add(new StatementLine(y, 0));
				}
				
				System.out.println("Check the total 1 - : " + y);
				
			}
			

			
			
			 for(y=1;y<=recursionStatementLines.size() ;y++) {
				 for(int z=0;z<recursionStatementLines.size();z++) {
					 if(recursionStatementLines.get(z).getLineNumber() == y ) {
						 StatementLine s1 = new StatementLine(y, recursionStatementLines.get(z).getComplexity() );
						 
						 displayCrList.add(s1);
					 }
				 }
				 
				 
			 }
			
			 for(y=0;y<displayCrList.size();y++) {
				 System.out.println(displayCrList.get(y).getLineNumber() +  " : " + displayCrList.get(y).getComplexity());
			 }
			
//			 for(y=0;y<displayCrList.size();y++) {
//				 if(stArray[displayCrList.get(y).getLineNumber() -1 ].isBlank()) {
//					 displayCrList.get(y).setComplexity(0);
//				 }
//			 }
		
			 displayCrList.add(new StatementLine(displayCrList.get(displayCrList.size()-1).getLineNumber() +1, 0));
		return displayCrList;
		
		
	}
	
	
	
}
