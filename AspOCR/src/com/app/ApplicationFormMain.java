package com.app;

import java.io.IOException;
import java.util.List;

import com.app.model.ImageModel;
import com.app.utility.CropImage;
import com.app.utility.ExcelUpdate;

public class ApplicationFormMain {
	
	private static String inputFolderPath="C:\\Users\\hi\\Desktop\\Application\\inputs";
	private static String processFolderPath="C:\\Users\\hi\\Desktop\\Application\\Output";
	//private static String outputExcelFileImage="C:\\Project\\Tony\\Output\\master.xls";
	private static String outputExcelFile="C:\\Project\\Tony\\Output\\masterFile.xls";
	private static String outputFile = "C:\\Users\\hi\\Desktop\\Application\\Output\\master.xls"; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new ApplicationFormMain().process();


	}
	
	public void process(){
		
		List<ImageModel> list =new CropImage().crop(inputFolderPath, processFolderPath);
	//	new ExcelUpdate().updateExcelWithFaceAndSig(list, outputExcelFileImage);
		new ExcelUpdate().updateExcelWithFaceAndSigFileInfo(list, outputExcelFile);
		
		try {
			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		try {
//			Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + outputExcelFileImage);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//save both the image in process folder
		//edit the excel file
		//insert in to D and E column
		
	}

}
