package com.pwc.exercise.domain;



//@Document(collection = "Image")
public class Image {
	//private String package_id;
	private String fileName;
	private String uniqueId;
	private String path;
	private String status;
	private String lengthx;
	private String lengthy;
	private String type;
	private int fileSize;
	
	public int getFileSize() {
		return fileSize;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLengthx() {
		return lengthx;
	}
	public void setLengthx(String lengthx) {
		this.lengthx = lengthx;
	}
	public String getLengthy() {
		return lengthy;
	}
	public void setLengthy(String lengthy) {
		this.lengthy = lengthy;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
//	public String getPackage_id() {
//		return package_id;
//	}
//
//	public void setPackage_id(String package_id) {
//		this.package_id = package_id;
//	}

}
