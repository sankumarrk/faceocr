package com.app.model;

import java.io.File;

public class ImageModel {
	
	private int roleNumber;
	private File fileNamePage1;
	private File fileNamePage2;
	private String croppedSigFile;
	private String croppedFaceFile;
	
	public String getCroppedSigFile() {
		return croppedSigFile;
	}
	public void setCroppedSigFile(String croppedSigFile) {
		this.croppedSigFile = croppedSigFile;
	}
	public String getCroppedFaceFile() {
		return croppedFaceFile;
	}
	public void setCroppedFaceFile(String croppedFaceFile) {
		this.croppedFaceFile = croppedFaceFile;
	}
	public int getRoleNumber() {
		return roleNumber;
	}
	public void setRoleNumber(int roleNumber) {
		this.roleNumber = roleNumber;
	}
	public File getFileNamePage1() {
		return fileNamePage1;
	}
	public void setFileNamePage1(File fileNamePage1) {
		this.fileNamePage1 = fileNamePage1;
	}
	public File getFileNamePage2() {
		return fileNamePage2;
	}
	public void setFileNamePage2(File fileNamePage2) {
		this.fileNamePage2 = fileNamePage2;
	}
	

}
