package com.pwc.exercise.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.exception.SimpleEspException;

public class DomainDaoImpl implements DomainDao {
	@Autowired
	private MongoTemplate mongoTemplate;
	private static final Log log = LogFactory.getLog(DomainDaoImpl.class);
	
	
	/**
	 * insert a package into MongoDB
	 * */
	public void insertFileObject(FileObject fo) throws SimpleEspException {
		if (fo == null)
			throw new SimpleEspException("No package info will be saved");
		else {
			log.info("save a package into mongoDB: " + fo.getPackage_id());
			this.mongoTemplate.insert(fo);
		}
	}

	
	/**
	 * find the package according to the packageId parameter
	 * */
	public FileObject findFileObject(String packageId) throws SimpleEspException {
		Query query = new Query();
		query.addCriteria(new Criteria("package_id").is(packageId));
		FileObject result_FO = this.mongoTemplate.findOne(query, FileObject.class);
		if (result_FO == null) {
			throw new SimpleEspException("No package info will be found");
		} else {
			log.info("load a package from mongoDB: " + result_FO.getPackage_id());
			return result_FO;
		}
	}
	
	/**
	 * find all packages' packageId
	 * */
	public List<String> findFileObjectId() {
		List<FileObject> result = this.mongoTemplate.findAll(FileObject.class);
		List<String> result_id = null;
		if (result == null) {
			log.info("ID list is not found.");
		} else {
			result_id = new ArrayList<String>();
			for (FileObject fo : result) {
				if (result_id.contains(fo.getPackage_id()))
					continue;
				else
					result_id.add(fo.getPackage_id());
			}
		}
		return result_id;
	}
	

	/**
	 * insert a video object into MongoDB
	 * */
//	public void insertVideo(Video video) {
//		// save a video into mongoDB
//		if (video == null)
//			return;
//		else {
//			log.info("insert a video into mongoDB: " + video.getFileName());
//			this.mongoTemplate.insert(video);
//		}
//	}

	/**
	 * insert a video object into MongoDB
	 * */
//	public void insertImage(Image image) {
//		if (image == null)
//			return;
//		else {
//			// save a image into mongoDB
//			log.info("insert a image into mongoDB: " + image.getFileName());
//			this.mongoTemplate.insert(image);
//		}
//	}

	/**
	 * insert a video object into MongoDB
	 * */
//	public void insertCC(CC cc) {
//		if (cc == null)
//			return;
//		else {
//			// save a cc into mongoDB
//			log.info("insert a CC into mongoDB: " + cc.getFileName());
//			this.mongoTemplate.insert(cc);
//		}
//	}

	/**
	 * find all videos according to the packageId parameter
	 * */
//	public List<Video> findAllVideo(String packageId) {
//		Query query = new Query();
//		query.addCriteria(new Criteria("package_id").is(packageId));
//		List<Video> result_video = this.mongoTemplate.find(query, Video.class);
//		if (result_video == null) {
//			log.info("None video is found.");
//			return null;
//		} else {
//			return result_video;
//		}
//	}

	/**
	 * find all images according to the packageId parameter
	 * */
//	public List<Image> findAllImage(String packageId) {
//		Query query = new Query();
//		query.addCriteria(new Criteria("package_id").is(packageId));
//		List<Image> result_image = this.mongoTemplate.find(query, Image.class);
//		if (result_image == null) {
//			log.info("None image is found.");
//			return null;
//		} else {
//			return result_image;
//		}
//	}

	/**
	 * find all CCs according to the packageId parameter
	 * */
//	public List<CC> findAllCC(String packageId) {
//		Query query = new Query();
//		query.addCriteria(new Criteria("package_id").is(packageId));
//		List<CC> result_CC = this.mongoTemplate.find(query, CC.class);
//		if (result_CC == null) {
//			log.info("None CC is found.");
//			return null;
//		} else {
//			return result_CC;
//		}
//	}

	// @Override
	// public void insertFileId(int fileId) {
	// this.mongoTemplate.insert(fileId);
	// }



	

	/**
	 * find all Images's packageId
	 * */
//	public List<String> findAllImageId() {
//		List<Image> result = this.mongoTemplate.findAll(Image.class);
//		List<String> result_id = new ArrayList<String>();
//		if (result == null) {
//			log.info("ID list is not found.");
//		} else {
//			for (Image image : result) {
//				if (result_id.contains(image.getPackage_id()))
//					continue;
//				else
//					result_id.add(image.getPackage_id());
//			}
//		}
//		return result_id;
//	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

}
