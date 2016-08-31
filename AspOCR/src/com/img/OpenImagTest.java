package com.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;
import org.openimaj.image.processing.face.detection.DetectedFace;
import org.openimaj.image.processing.face.detection.FaceDetector;
import org.openimaj.image.processing.face.detection.HaarCascadeDetector;

public class OpenImagTest {
	public static void main(String[] args) {
		try {
			
		
			
			String fileName = "C:\\Users\\hi\\Desktop\\Application\\inputs\\44.jpg";
			MBFImage image = ImageUtilities.readMBF(new FileInputStream(fileName));
			
			File file = new File(fileName);
			//DisplayUtilities.display(image);
			//DisplayUtilities.display(image.getBand(0), "Red Channel");
			
//			MBFImage clone = image.clone();
//			for (int y=0; y<image.getHeight(); y++) {
//			    for(int x=0; x<image.getWidth(); x++) {
//			        clone.getBand(1).pixels[y][x] = 0;
//			        clone.getBand(2).pixels[y][x] = 0;
//			    }
//			}
			//DisplayUtilities.display(clone);
			//MBFImageRenderer image = toDraw.createRenderer();

			FaceDetector<DetectedFace,FImage> fd = new HaarCascadeDetector(80);
			//FaceDetector<DetectedFace,FImage> fd = new CLMFaceDetector().;
			//List<DetectedFace> faces = fd. detectFaces (Transforms.calculateIntensity(image));
			List<DetectedFace> faces = fd. detectFaces (image.flattenMax());
			System.out.println("detected face size:" + faces.size());
			
			
			for(int i=0;i<faces.size();i++){
				DetectedFace detectedFace = (DetectedFace)faces.get(i);
				
				System.out.println("Area:" +detectedFace.getBounds().calculateArea());
				int x = (int)detectedFace.getBounds().x;
				int y = (int)detectedFace.getBounds().y;
				int w = (int)detectedFace.getBounds().width;
				int h = (int)detectedFace.getBounds().height;
				
				BufferedImage originalImage = ImageIO.read(file);
				BufferedImage SubImgage = originalImage.getSubimage(x,y-15,w,h+15);
				System.out.println("Cropped Image Dimension: "+SubImgage.getWidth()+"x"+SubImgage.getHeight());
				File outputfile = new File("C:\\Users\\hi\\Desktop\\Application\\inputs\\12-face"+x+".jpg");
				ImageIO.write(SubImgage, "jpg", outputfile);
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
