package com.example.util;

public class ValidateScripting {
	
	
	public static String validate(String str){
		str = str.replace("<", "_").replace(">", "_");
		//str =  str.replaceAll("[^\\dA-Za-z ]", "").replaceAll("\\s", "\\s").trim();
		System.out.println(str);
		return str;
	}
}
