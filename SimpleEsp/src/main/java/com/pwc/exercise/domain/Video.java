package com.pwc.exercise.domain;


//@Document(collection = "Video")
public class Video {
	//private String package_id;
	private String fileName;
	private String uniqueId;
	private String path;
	private String status;
	private String runtime;
	private String type;
	
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
	public String getRuntime() {
		return runtime;
	}
	public void setRuntime(String runtime) {
		this.runtime = runtime;
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
