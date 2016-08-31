package com.app.utility;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import com.app.model.ImageModel;

public class ExcelUpdate {
	
	
	
	public void updateExcelWithFaceAndSigFileInfo(List<ImageModel> imgModelList,
			String file) {
		try {
			
			FileInputStream fIPS = new FileInputStream(file); // Read the
			// spreadsheet
			// that needs to
			// be updated
			HSSFWorkbook workbook = new HSSFWorkbook(fIPS);

			HSSFSheet worksheet = workbook.getSheetAt(0);
			//  final CreationHelper createHelper = workbook.getCreationHelper();
			
		 	
			for (int index = 0; index < imgModelList.size(); index++) {
				ImageModel model = imgModelList.get(index);
				int rowIndex = index + 1;				
				HSSFRow row = worksheet.getRow(rowIndex);
				
				
				row.createCell(3).setCellValue(model.getCroppedFaceFile());
				row.createCell(4).setCellValue(model.getCroppedSigFile());
				
//				row.getCell(3).setCellValue(model.getCroppedFaceFile());
//				row.getCell(4).setCellValue(model.getCroppedSigFile());
				
				
				}
			
			fIPS.close();
			
			 FileOutputStream fileOut = null;
			   fileOut = new FileOutputStream(file);
			   workbook.write(fileOut);
			   fileOut.close();

		} catch (Exception ex) {
			//ex.printStackTrace();
		}
		
	}
	
	
	




	   
	
	
	
	
	public void updateExcelWithFaceAndSig(List<ImageModel> imgModelList,
			String file) {
		try {
			
			FileInputStream fIPS = new FileInputStream(file); // Read the
			// spreadsheet
			// that needs to
			// be updated
			HSSFWorkbook workbook = new HSSFWorkbook(fIPS);

			HSSFSheet worksheet = workbook.getSheetAt(0);
			
		   CreationHelper helper = workbook.getCreationHelper();

		   Drawing drawing = worksheet.createDrawingPatriarch();

		   ClientAnchor anchor = helper.createClientAnchor();
		   
		   //Creating HSSFPatriarch object
		    HSSFPatriarch patriarch=worksheet.createDrawingPatriarch();
		    //Creating picture with anchor and index information
		    //patriarch.createPicture(anchor,index);
		    //Write workbook with the data
			
			
			for (int index = 0; index < imgModelList.size(); index++) {
				ImageModel model = imgModelList.get(index);
				int rowIndex = index + 1;
				
				HSSFRow row = worksheet.getRow(rowIndex);
				
				final FileInputStream inputStream = new FileInputStream(new File(
						model.getCroppedFaceFile()));
				byte[] imageBytes = IOUtils.toByteArray(inputStream);
				
				anotherTry(workbook, worksheet,row,imageBytes,drawing,anchor, rowIndex,3,patriarch);
					
				
	
				//addThumbnail(worksheet, workbook, row, 3, imageBytes,patriarch);
				
				final FileInputStream inputStream1 = new FileInputStream(new File(
						model.getCroppedSigFile()));
				byte[] imageBytes1 = IOUtils.toByteArray(inputStream1);
				
				anotherTry(workbook,worksheet,row,imageBytes1,drawing,anchor, rowIndex,4,patriarch);
				//addThumbnail(worksheet, workbook, row, 4,imageBytes1,patriarch);
				
				
				
				//insertImage(file, model.getCroppedFaceFile(), row, 3);
				//insertImage(file, model.getCroppedSigFile(), row, 5);
			}
			 FileOutputStream fileOut = null;
			   fileOut = new FileOutputStream(file);
			   workbook.write(fileOut);
			   fileOut.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	private void anotherTry(HSSFWorkbook workbook,HSSFSheet sheet, HSSFRow row, byte[] imgBytes, Drawing drawing, ClientAnchor anchor, int rowIndex, int col, HSSFPatriarch apatriarch){
		try {
			   int pictureureIdx = workbook.addPicture(imgBytes, Workbook.PICTURE_TYPE_JPEG);

			   anchor.setCol1(col);
			   anchor.setRow1(rowIndex);
	 		   drawing.createPicture(anchor, pictureureIdx);
			   
			 
			   HSSFPicture picture = apatriarch.createPicture(anchor, pictureureIdx);

				Dimension d = picture.getImageDimension();

				
				row.setHeightInPoints((short) d.getHeight());

				picture.resize();
				
				HSSFClientAnchor clientanchor = new HSSFClientAnchor();
				// int col1=1,row1=1;
				// HSSFClientAnchor object mainly sets the excel cell location where
				// the image needs to be inserted
				// (col1, row1, x1, y1, col2, row2, x2, y2)
				clientanchor.setAnchor((short) col, row.getRowNum(), 0, 0, (short) col, row.getRowNum(),	0, 0);
				anchor.setAnchorType(2);

				float anchorHeight = clientanchor.getAnchorHeightInPoints(sheet);

				row.setHeightInPoints(anchorHeight);

				picture.resize();
			   
			   
			  
			}catch (Exception e) {
			   System.out.println(e);
			}
	}

	private void insertImageInExcel(String file, int row, int col,
			String imageFilePath) throws FileNotFoundException, IOException {

		FileInputStream fIPS = new FileInputStream(file); // Read the
		// spreadsheet
		// that needs to
		// be updated
		HSSFWorkbook workbook = new HSSFWorkbook(fIPS);

		HSSFSheet worksheet = workbook.getSheetAt(0);

		final FileInputStream inputStream = new FileInputStream(new File(
				imageFilePath));

		byte[] imageBytes = IOUtils.toByteArray(inputStream);
		final CreationHelper helper = workbook.getCreationHelper();
		final Drawing drawing = worksheet.createDrawingPatriarch();

		final ClientAnchor anchor = helper.createClientAnchor();
		anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);

		final int pictureIndex = workbook.addPicture(imageBytes,
				Workbook.PICTURE_TYPE_JPEG);

		anchor.setCol1(col);
		anchor.setRow1(row);
		anchor.setRow2(row);
		anchor.setCol2(col);
		final Picture pict = drawing.createPicture(anchor, pictureIndex);
		pict.resize();
		inputStream.close();
		fIPS.close();

	}

	public void insertImage(String excelFile, String image, int row1, int col1) {
		try {
			// Creating a excel workbook
			FileInputStream fIPS = new FileInputStream(excelFile);

			HSSFWorkbook wb = new HSSFWorkbook(fIPS);
			// Creating a excel sheet named "test" within the workbook
			// HSSFSheet testsheet=wb.getSheetAt(0);

			// Pointing the excel file to FileOutputStream where image needs to
			// be inserted
			FileOutputStream fos = new FileOutputStream(excelFile);
			System.out.println("File sample.xls is created");
			// Pointing the image file to FileInputStream which needs to be
			// inserted in excel sheet
			FileInputStream fis = new FileInputStream(image);
			// Creating a ByteArrayOutputStream object
			ByteArrayOutputStream img_bytes = new ByteArrayOutputStream();
			int b;
			// The loop reads each byte from input stream and puts it into the
			// byte array
			while ((b = fis.read()) != -1)
				img_bytes.write(b);
			// Closing the input stream as it is not required further
			fis.close();
			// Creating HSSFClientAnchor object
			HSSFClientAnchor anchor = new HSSFClientAnchor();
			// int col1=1,row1=1;
			// HSSFClientAnchor object mainly sets the excel cell location where
			// the image needs to be inserted
			// (col1, row1, x1, y1, col2, row2, x2, y2)
			anchor.setAnchor((short) col1, row1, 0, 0, (short) ++col1, ++row1,
					0, 0);
			anchor.setAnchorType(2);
			// Converts the byte array stream to JPEG image
			int index = wb.addPicture(img_bytes.toByteArray(),
					HSSFWorkbook.PICTURE_TYPE_JPEG);
			// Gets sheet from the workbook
			HSSFSheet sheet = wb.getSheetAt(0);
			// Creating HSSFPatriarch object
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			// Creating picture with anchor and index information
			patriarch.createPicture(anchor, index);
			// Write workbook with the data
			wb.write(fos);
			System.out.println("Writing data to the xls file");
			// Close FileOutputStream as it will no longer be required
			fos.close();
			System.out.println("File closed");
		} catch (IOException ioe) {
			System.out
					.println("Hi ! You got an exception. " + ioe.getMessage());
		}
	}

	/**
	 * 
	 * Adds an image to the given cell.
	 * 
	 * 
	 * 
	 * @param sheet
	 *            The sheet to add the image to.
	 * 
	 * @param wb
	 *            The workbook to add the image to.
	 * 
	 * @param row
	 *            The row index (0-based)
	 * 
	 * @param columnIndex
	 *            The column index (0-based)
	 * 
	 * @param bis
	 *            The {@link ByteArrayInputStream} of the image to add
	 * @param apatriarch 
	 */

	private void addThumbnail(HSSFSheet sheet, HSSFWorkbook wb, HSSFRow row,
			int columnIndex, byte[] bis, HSSFPatriarch apatriarch) {

		//HSSFPatriarch apatriarch = sheet.createDrawingPatriarch();

		//HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255,(short) columnIndex, row.getRowNum(), (short) columnIndex, row.getRowNum());
		HSSFClientAnchor anchor = new HSSFClientAnchor();
		// int col1=1,row1=1;
		// HSSFClientAnchor object mainly sets the excel cell location where
		// the image needs to be inserted
		// (col1, row1, x1, y1, col2, row2, x2, y2)
		anchor.setAnchor((short) columnIndex, row.getRowNum(), 0, 0, (short) columnIndex, row.getRowNum(),	0, 0);
		anchor.setAnchorType(2);

		HSSFPicture picture = apatriarch.createPicture(anchor, loadPicture(bis, wb));

		Dimension d = picture.getImageDimension();

		row.setHeightInPoints((short) d.getHeight());

		picture.resize();

		float anchorHeight = anchor.getAnchorHeightInPoints(sheet);

		row.setHeightInPoints(anchorHeight);

		picture.resize();

	}

	/**
	 * 
	 * Loads the picture in the workbook and returns its index.
	 * 
	 * 
	 * 
	 * @param bis
	 *            The ByteArrayInputStream of the image.
	 * 
	 * @param wb
	 *            The workbook to add the picture to.
	 * 
	 * @return The index of the image.
	 * 
	 * @throws IOException
	 *             When the
	 */

	private int loadPicture(byte[] bis, HSSFWorkbook wb)  { 

          int pictureIndex; 

        //  ByteArrayOutputStream bos = null; 

          try { 

//                bos = new ByteArrayOutputStream(); 
//
//                int c; 
//
//                while ((c = bis.read()) != -1) 
//
//                      bos.write(c); 

                pictureIndex = wb.addPicture(bis, 

                           HSSFWorkbook.PICTURE_TYPE_JPEG); 

          } finally { 

               

          } 

          return pictureIndex; 

    }
	
}
