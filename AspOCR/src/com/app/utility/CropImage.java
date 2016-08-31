package com.app.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.app.model.ImageModel;
import com.text.OCRNumber;


public class CropImage {
	
	
	public List<ImageModel> crop(String inputFolder, String processFolder){
		//Read all images from the folder
		//iterate file by file 
		//crop photo from first image 
		//crop signature from second image
		
		
		
		File file = new File(inputFolder);
		File images[] = file.listFiles();
		ArrayList<ImageModel> imgModels = new ArrayList<ImageModel>();
		for(int index=0;index<images.length;index++){
			
			//String number = new FormNumberExtractor().extractNumber(images[index]);
			String number= new OCRNumber().extractNumber(images[index])	;	
			ImageModel  imgModel = new ImageModel();
			imgModel.setRoleNumber(101);
			imgModel.setFileNamePage1(images[index]);	
			imgModel.setCroppedFaceFile(cropFace(number,images[index],processFolder));
			imgModel.setFileNamePage2(images[++index]);	
			imgModel.setCroppedSigFile(cropSignature(number,images[index],processFolder));
			imgModels.add(imgModel);
		}
		return imgModels;
			
	}

	private String cropSignature(String roleNumber, File file, String outputFolder) {
		String croppedSigFile="";
		
		try {
			BufferedImage originalImage = ImageIO.read(file);
			System.out.println("Original Image Dimension: "+originalImage.getWidth()+"x"+originalImage.getHeight());
			BufferedImage SubImgage = originalImage.getSubimage(900, 1910, 400 , 160);
			System.out.println("Cropped Image Dimension: "+SubImgage.getWidth()+"x"+SubImgage.getHeight());
			File outputfile = new File(outputFolder +"/signature/"+ roleNumber + ".jpg" );
			ImageIO.write(SubImgage, "jpg", outputfile);
			croppedSigFile = outputfile.getAbsolutePath();
			System.out.println("Output File path: "+ croppedSigFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return croppedSigFile;
		
	}

	private String cropFace(String roleNumber,File file, String outputFolder) {
		String croppedFaceFile="";
		try {
			BufferedImage originalImage = ImageIO.read(file);
			System.out.println("Original Image Dimension: "+originalImage.getWidth()+"x"+originalImage.getHeight());
			BufferedImage SubImgage = originalImage.getSubimage(1140, 245, 1430-1140 , 610-245);
			System.out.println("Cropped Image Dimension: "+SubImgage.getWidth()+"x"+SubImgage.getHeight());
			File outputfile = new File(outputFolder +"/photo/"+ roleNumber + ".jpg" );
			ImageIO.write(SubImgage, "jpg", outputfile);
			croppedFaceFile = outputfile.getAbsolutePath();;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return croppedFaceFile;
	}

}
