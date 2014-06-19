package com.pwc.exercise.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FileObject")
public class FileObject {
	//@Id
	private String package_id;
	private List<Video> videos;
	private List<Image> images;
	private List<CC> ccs;


	public String getPackage_id() {
		return package_id;
	}

	public void setPackage_id(String package_id) {
		this.package_id = package_id;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	

	public List<CC> getCcs() {
		return ccs;
	}

	public void setCcs(List<CC> ccs) {
		this.ccs = ccs;
	}

}
