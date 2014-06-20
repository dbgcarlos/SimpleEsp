package com.pwc.exercise.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.pwc.exercise.dao.DomainDao;
import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.exception.SimpleEspException;

public class MongoService {
	@Autowired
	private DomainDao domainDao;
	private static final Log log = LogFactory.getLog(ParserService.class);

	/**
	 * insert video,image,cc objects into MongoDB
	 * @throws JsonGenerationException 
	 * @throws FileObjectNotFoundException 
	 * */
	public void dataInMongo(FileObject fileObject) throws SimpleEspException  {
		FileObject acquire_fo = fileObject;

		log.info("------------------------------------------------------");
		log.info("start to save package: ");
		//log.info(fileObject.getPackage_id());
		this.domainDao.insertFileObject(acquire_fo);
		
		
		
//		log.info("start to save images");
//		List<Image> acquire_Images = acquire_fo.getImages();
//		for (Image image : acquire_Images) {
//			this.domainDao.insertImage(image);
//		}
//
//		log.info("------------------------------------------------------");
//
//		log.info("start to save videos");
//		List<Video> acquire_Videos = acquire_fo.getVideos();
//		for (Video video : acquire_Videos) {
//			this.domainDao.insertVideo(video);
//		}
//
//		log.info("------------------------------------------------------");
//
//		log.info("start to save CC");
//		List<CC> acquire_CC = acquire_fo.getCcs();
//		for (CC cc : acquire_CC) {
//			this.domainDao.insertCC(cc);
//		}
//
//		log.info("------------------------------------------------------");
//		log.info("Saving done");
	}

	/**
	 * query video,image,cc objects from MongoDB,put into a FileObject
	 * @throws JsonGenerationException 
	 * @throws FileObjectNotFoundException 
	 * */
	public FileObject dataOutofMongo(String package_Id) throws SimpleEspException{
		FileObject new_fo = null;
		
		log.info("------------------------------------------------------");
		log.info("start to load package: " );
		new_fo = this.domainDao.findFileObject(package_Id);
		log.info(new_fo.getPackage_id());
		return new_fo;
		
//		log.info("start to load images");
//		List<Image> acquire_Images = this.domainDao
//				.findAllImage(package_Id);
//		for(Image loadImage: acquire_Images){
//			log.info("load image: " + loadImage.getFileName());
//		}
//
//		log.info("------------------------------------------------------");
//
//		log.info("start to load videos");
//		List<Video> acquire_Videos =  this.domainDao
//				.findAllVideo(package_Id);
//		for(Video loadVideo: acquire_Videos){
//			log.info("load video: " + loadVideo.getFileName());
//		}
//
//		log.info("------------------------------------------------------");
//
//		log.info("start to load CC");
//		List<CC> acquire_CCs = this.domainDao
//				.findAllCC(package_Id);
//		for(CC loadCC: acquire_CCs){
//			log.info("load CC: " + loadCC.getFileName());
//		}
//
//		log.info("------------------------------------------------------");
//
//		new_fo.setPackage_id(package_Id);
//		new_fo.setImages(acquire_Images);
//		new_fo.setVideos(acquire_Videos);
//		new_fo.setCcs(acquire_CCs);
//		return new_fo;
	}
	
	public DomainDao getDomainDao() {
		return domainDao;
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}

}
