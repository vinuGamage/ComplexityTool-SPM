package DAO_SERVICE.Common;

import java.util.ArrayList;

import POJO_MODEL.IndividualFunction;

public class Common {
	
	public static ArrayList<IndividualFunction> divideToFunctions(String[] stArray){
		

		
		int x = stArray.length;
		int y;
		int z; 
		int a; 
		int b;
		String methodName = null;
		
		ArrayList<IndividualFunction> FunctionList = new ArrayList<>();
		
		String[] methodLineSplitBySpaces;
		
		System.out.println(x);
		 for(y=0;y<x;y++) {
			 
			 System.out.println("Line " + y + "  : " + stArray[y]);
			 
		 }
		 
//		 System.out.println("The type of complexity is : " + type);
//		 System.out.println("The type of Language is : " + language);
		 
		 // Divide each function for java
		 ArrayList<Integer>  methodNameLineNumberList =  new ArrayList<>(); // stores the line number of method starting
		
		 ArrayList<String>  bracketCheckArray =  new ArrayList<>();  // used to store opening and closing brackets to divide the functions
		 String[] lineToChars; // to check each brackets
				 
		System.out.println("The lines of method name are given below \n");
		 	for(y=0;y<x;y++) {
			
			 if((stArray[y].contains("public") || stArray[y].contains("private") || stArray[y].contains("protected")) && (stArray[y].contains("void") ||stArray[y].contains("int") ||stArray[y].contains("long") || stArray[y].contains("float") || stArray[y].contains("double")   || stArray[y].contains("boolean") ||stArray[y].contains("String") || stArray[y].contains("ArrayList")) ) { 
				// Modifier Checked
				
					// Return Type Checked
					 if(stArray[y].contains("(") && stArray[y].contains(")") && stArray[y].contains("{")  ) {
						 System.out.println(stArray[y]);
						 methodNameLineNumberList.add(y);
					 }
				 
				 
			 }
			
			 
			 
			 
		 }
		 	 System.out.println(methodNameLineNumberList.size());
		 	for(y=0;y< methodNameLineNumberList.size();y++) {
				 System.out.println("Line number : " + methodNameLineNumberList.get(y));
				   
				 for(z=methodNameLineNumberList.get(y); z<stArray.length; z++) {
					lineToChars = stArray[z].split("(?!^)");
					
					for (a=0; a<lineToChars.length;a++) {
						if(lineToChars[a].contains("{")) {
							bracketCheckArray.add("{");
							System.out.println("{");
							System.out.println(bracketCheckArray.toString() );
						}
						if(lineToChars[a].contains("}")) {
							bracketCheckArray.remove(bracketCheckArray.size()-1);
							System.out.println("}");
							System.out.println(bracketCheckArray.toString());
						}
						
						
					}
					
					if(bracketCheckArray.toString() == "[]") {
						 break;
					 }
				 }
				 System.out.println("From :" + methodNameLineNumberList.get(y) + " To : " + z);
				methodLineSplitBySpaces = stArray[methodNameLineNumberList.get(y)].split("\\s");
				for(b=0;b<methodLineSplitBySpaces.length;b++) {
					
					if(methodLineSplitBySpaces[b].contains("(")) {
						System.out.println(methodLineSplitBySpaces[b]);
						System.out.println(methodLineSplitBySpaces[b].split("\\(")[0]);
					methodName=	methodLineSplitBySpaces[b].split("\\(")[0];
					}
				}
				IndividualFunction f1 = new IndividualFunction(methodNameLineNumberList.get(y), z, methodName);
				FunctionList.add(f1);
			 }
		 	
		 	for(y=0; y<FunctionList.size();y++) {
		 		IndividualFunction f2 = FunctionList.get(y);
		 		System.out.println(f2.getStart());
		 		System.out.println(f2.getEnd());
		 		System.out.println(f2.getMethodName());
		 	}
		 	
		 	return FunctionList;
		
	}

}
