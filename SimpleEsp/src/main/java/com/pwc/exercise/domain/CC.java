package com.pwc.exercise.domain;

import java.util.ArrayList;

//@Document(collection = "CC")
public class CC {
	//private String package_id;
	private String fileName;
	private String uniqueId;
	private String path;
	private String status;
	
	private ArrayList<String> languages;
	
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
	public ArrayList<String> getLanguages() {
		return languages;
	}
	public void setLanguages(ArrayList<String> languages) {
		this.languages = languages;
	}
	
//	public String getPackage_id() {
//		return package_id;
//	}
//
//	public void setPackage_id(String package_id) {
//		this.package_id = package_id;
//	}
	

}
