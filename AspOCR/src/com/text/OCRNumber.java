package com.text;

import java.io.File;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

import com.asprise.ocr.Ocr;

public class OCRNumber {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String number="0000";
//		// TODO Auto-generated method stub
//		Ocr.setUp(); // one time setup
//		Ocr ocr = new Ocr(); // create a new OCR engine
//		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
//		String s = ocr.recognize(new File[] { new File("C:\\Users\\hi\\Desktop\\tamil scan\\tony\\number\\44.jpg") },
//				Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
//		System.out.println("Result: " + s);
//		
//		Pattern p = Pattern.compile("\\d\\d\\d\\d\\d");
//		Matcher m = p.matcher(s);
//		while (m.find()) {
//			number = m.group();
//		  System.out.println("number:  "+ m.group());
//		}
//		ocr.stopEngine();
//		
//		System.out.println("number: "+number);
		new OCRNumber().extractNumber(new File("dsd"));
	}

	
	public String extractNumber(File fileInfo){
		
		String number="0000";
		// TODO Auto-generated method stub
		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
		String s = ocr.recognize(new File[] { new File("C:\\Users\\hi\\Desktop\\tamil scan\\tony\\number\\44.jpg") },
				Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
		System.out.println("Result: " + s);
		
		Pattern p = Pattern.compile("\\d\\d\\d\\d\\d");
		Matcher m = p.matcher(s);
		while (m.find()) {
			number = m.group();
		  System.out.println("number:  "+ m.group());
		}
		ocr.stopEngine();
		
		System.out.println("number: "+number);
		
		return number;
		
	}
}
