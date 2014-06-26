package com.pwc.exercise.dao;

import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.exception.SimpleEspException;

public interface DomainDao {
	
	//insert a package into DB
	public void insertFileObject(FileObject fo) throws SimpleEspException;
	
	//query a package from DB
	public FileObject findFileObject(String packageId)throws SimpleEspException;
	
	//query all packages' id from DB
	//public List<String> findFileObjectId();
	
	//insert a video object
	//public void insertVideo(Video video);
	
	//insert an image object
	//public void insertImage(Image image);
	
	//insert a CC object
	//public void insertCC(CC cc);
	
	//find all videos according to packageId
	//public List<Video> findAllVideo(String packageId);
	
	//find all images according to packageId
	//public List<Image> findAllImage(String packageId);
	
	//find all images according to packageId
	//public List<CC> findAllCC(String packageId);
	
	//find all packageId
	//public List<String> findAllImageId();
}
