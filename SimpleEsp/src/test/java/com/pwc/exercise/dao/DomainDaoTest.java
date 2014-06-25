package com.pwc.exercise.dao;

import static org.easymock.EasyMock.eq;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.classextension.EasyMock;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import com.pwc.exercise.domain.CC;
import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.domain.Image;
import com.pwc.exercise.domain.Video;
import com.pwc.exercise.exception.SimpleEspException;

public class DomainDaoTest {
	
	@Test
	public void testInsertFileObject() throws JsonGenerationException, SimpleEspException {
		FileObject expectFo = new FileObject();
		expectFo.setPackage_id("382921");
		
		List<Video> expect_VO = new ArrayList<Video>();
		Video[] video = new Video[2];
		Video v1 = new Video();
		//v1.setPackage_id("382921");
		v1.setFileName("videos/PRO87/test555/test_PRO87.tar");
		v1.setPath("videos/PRO87/test555/");
		v1.setRuntime("2222");
		v1.setStatus("NEW");
		v1.setType("FEATURE-PRO87");
		v1.setUniqueId("test_pro87");
		video[0] = v1;
		
		Video v2 = new Video();
		//v2.setPackage_id("382921");
		v2.setFileName("videos/PRO88/test555/test_PRO88.tar");
		v2.setPath("videos/PRO88/test555/");
		v2.setRuntime("0");
		v2.setStatus("NEW");
		v2.setType("FEATURE-PRO88");
		v2.setUniqueId("test_pro88");
		video[1] = v2;
		
		expect_VO.add(video[0]);
		expect_VO.add(video[1]);
		

		ArrayList<Image> expect_IM = new ArrayList<Image>();
		Image[] image = new Image[2];
		
		Image i0 = new Image();
		//i0.setPackage_id("382921");
		i0.setLengthx("1300");
		i0.setLengthy("500");
		i0.setFileName("images/hbo/series/test123/test123_1300x500.jpg");
		i0.setPath("images/hbo/series/test123/");
		i0.setStatus("NEW");
		i0.setType("SERIES_1300x500_B");
		i0.setUniqueId("123456");
		image[0] = i0;
		
		Image i1 = new Image();
		//i1.setPackage_id("382921");
		i1.setLengthx("400");
		i1.setLengthy("90");
		i1.setFileName("images/hbo/series/test123/test123_400x90.png");
		i1.setPath("images/hbo/series/test123/");
		i1.setStatus("NEW");
		i1.setType("SERIES_400x90");
		i1.setUniqueId("1234567");
		image[1] = i1;
		
		for(Image i_im:image){
			expect_IM.add(i_im);
		}
		
		ArrayList<CC> expect_CC = new ArrayList<CC>();
		CC[] cc = new CC[2];
		
		CC c0 = new CC();
		c0.setFileName("captions/PRO87/test555/test_PRO87_CC.tar");
		c0.setPath("captions/PRO87/test555/");
		//c0.setPackage_id("382921");
		ArrayList<String> l1 = new ArrayList<String>();
		l1.add("ENG");
		c0.setLanguages(l1);
		c0.setStatus("NEW");
		c0.setUniqueId("test_pro87");
		cc[0] = c0;
		
		CC c1 = new CC();
		c1.setFileName("captions/PRO88/test555/test555_PRO88_CC.tar");
		c1.setPath("captions/PRO88/test555/");
		//c1.setPackage_id("382921");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("ENG");
		c1.setLanguages(l2);
		c1.setStatus("NEW");
		c1.setUniqueId("test_PRO88");
		cc[1] = c1;
		
		expect_CC.add(cc[0]);
		expect_CC.add(cc[1]);
		
		expectFo.setImages(expect_IM);
		expectFo.setCcs(expect_CC);
		expectFo.setVideos(expect_VO);
		
		Capture<FileObject> capturedObject = new Capture<FileObject>(CaptureType.ALL);
		DomainDaoImpl domainDao = new DomainDaoImpl();
		
		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
		mongoTemplate.insert(EasyMock.capture(capturedObject));
		EasyMock.expectLastCall().once();
		EasyMock.replay(mongoTemplate);
		domainDao.setMongoTemplate(mongoTemplate);

		domainDao.insertFileObject(expectFo);
		EasyMock.verify(mongoTemplate);

		FileObject capturedFO = capturedObject.getValue();

		assertNotNull(capturedFO);
		assertEquals("packakgeId is not equal", "382921",
				capturedFO.getPackage_id());
		assertEquals(2, capturedFO.getImages().size());
		assertEquals(2, capturedFO.getVideos().size());
		assertEquals(2, capturedFO.getCcs().size());
		//assertEquals();
		
	}
	
	@Test
	public void testInsertFileObjectWithNull() {
		DomainDaoImpl domainDao = new DomainDaoImpl();
		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
		mongoTemplate.insert(null);
		EasyMock.expectLastCall().once();
		EasyMock.replay(mongoTemplate);
		domainDao.setMongoTemplate(mongoTemplate);

		try {
			domainDao.insertFileObject(null);
			fail("No exception captured");
		} catch (SimpleEspException e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		}
		//EasyMock.verify(mongoTemplate);
	}
	
	@Test
	public void testfindFileObject() throws  SimpleEspException {
		FileObject expectFo = new FileObject();
		expectFo.setPackage_id("382921");
		
		List<Video> expect_VO = new ArrayList<Video>();
		Video[] video = new Video[2];
		Video v1 = new Video();
		//v1.setPackage_id("382921");
		v1.setFileName("videos/PRO87/test555/test_PRO87.tar");
		v1.setPath("videos/PRO87/test555/");
		v1.setRuntime("2222");
		v1.setStatus("NEW");
		v1.setType("FEATURE-PRO87");
		v1.setUniqueId("test_pro87");
		video[0] = v1;
		
		Video v2 = new Video();
		//v2.setPackage_id("382921");
		v2.setFileName("videos/PRO88/test555/test_PRO88.tar");
		v2.setPath("videos/PRO88/test555/");
		v2.setRuntime("0");
		v2.setStatus("NEW");
		v2.setType("FEATURE-PRO88");
		v2.setUniqueId("test_pro88");
		video[1] = v2;
		
		expect_VO.add(video[0]);
		expect_VO.add(video[1]);
		

		ArrayList<Image> expect_IM = new ArrayList<Image>();
		Image[] image = new Image[2];
		
		Image i0 = new Image();
		//i0.setPackage_id("382921");
		i0.setLengthx("1300");
		i0.setLengthy("500");
		i0.setFileName("images/hbo/series/test123/test123_1300x500.jpg");
		i0.setPath("images/hbo/series/test123/");
		i0.setStatus("NEW");
		i0.setType("SERIES_1300x500_B");
		i0.setUniqueId("123456");
		image[0] = i0;
		
		Image i1 = new Image();
		//i1.setPackage_id("382921");
		i1.setLengthx("400");
		i1.setLengthy("90");
		i1.setFileName("images/hbo/series/test123/test123_400x90.png");
		i1.setPath("images/hbo/series/test123/");
		i1.setStatus("NEW");
		i1.setType("SERIES_400x90");
		i1.setUniqueId("1234567");
		image[1] = i1;
		
		for(Image i_im:image){
			expect_IM.add(i_im);
		}
		
		ArrayList<CC> expect_CC = new ArrayList<CC>();
		CC[] cc = new CC[2];
		
		CC c0 = new CC();
		c0.setFileName("captions/PRO87/test555/test_PRO87_CC.tar");
		c0.setPath("captions/PRO87/test555/");
		//c0.setPackage_id("382921");
		ArrayList<String> l1 = new ArrayList<String>();
		l1.add("ENG");
		c0.setLanguages(l1);
		c0.setStatus("NEW");
		c0.setUniqueId("test_pro87");
		cc[0] = c0;
		
		CC c1 = new CC();
		c1.setFileName("captions/PRO88/test555/test555_PRO88_CC.tar");
		c1.setPath("captions/PRO88/test555/");
		//c1.setPackage_id("382921");
		ArrayList<String> l2 = new ArrayList<String>();
		l2.add("ENG");
		c1.setLanguages(l2);
		c1.setStatus("NEW");
		c1.setUniqueId("test_PRO88");
		cc[1] = c1;
		
		expect_CC.add(cc[0]);
		expect_CC.add(cc[1]);
		
		expectFo.setImages(expect_IM);
		expectFo.setCcs(expect_CC);
		expectFo.setVideos(expect_VO);
		
		DomainDaoImpl domainDao = new DomainDaoImpl();
		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
		EasyMock.expect(
				mongoTemplate.findOne(EasyMock.isA(Query.class), eq(FileObject.class)))
				.andReturn(expectFo);
		EasyMock.replay(mongoTemplate);
		domainDao.setMongoTemplate(mongoTemplate);

		FileObject result_FO = domainDao.findFileObject("382921");

		assertEquals("packakgeId is not equal", "382921",
				result_FO.getPackage_id());

		EasyMock.verify(mongoTemplate);
	}
	
	@Test
	public void testfindFileObjectwilNull(){
		FileObject expectFo = null;
		
		DomainDaoImpl domainDao = new DomainDaoImpl();
		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
		EasyMock.expect(
				mongoTemplate.findOne(EasyMock.isA(Query.class), eq(FileObject.class)))
				.andReturn(expectFo);
		EasyMock.replay(mongoTemplate);
		domainDao.setMongoTemplate(mongoTemplate);

		//FileObject result_FO;
		try {
			 domainDao.findFileObject("382921");
		} catch (SimpleEspException e) {
			assertTrue(true);
		}

		EasyMock.verify(mongoTemplate);
	}
	
//	public void testfindFileObjectId() throws JsonGenerationException{
//		FileObject expectFo = new FileObject();
//		FileObject expectFo1 = new FileObject();
//		FileObject expectFo2 = new FileObject();
//		
//		expectFo.setPackage_id("321212");
//		expectFo1.setPackage_id("326597");
//		expectFo2.setPackage_id("323440");
//		
//		List<FileObject> list_FO = new ArrayList<FileObject>();
//		list_FO.add(expectFo);
//		list_FO.add(expectFo1);
//		list_FO.add(expectFo2);
//		
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		EasyMock.expect(
//				mongoTemplate.findAll(FileObject.class))
//				.andReturn(list_FO);
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		List<String> result_Id = domainDao.findFileObjectId();
//		EasyMock.verify(mongoTemplate);
//		
//		assertEquals(3,result_Id.size());
//		
//	}
	
//	public void testfindFileObjectIdwithRepetation() throws JsonGenerationException{
//		FileObject expectFo = new FileObject();
//		FileObject expectFo1 = new FileObject();
//		FileObject expectFo2 = new FileObject();
//		
//		expectFo.setPackage_id("321212");
//		expectFo1.setPackage_id("321212");
//		expectFo2.setPackage_id("323440");
//		
//		List<FileObject> list_FO = new ArrayList<FileObject>();
//		list_FO.add(expectFo);
//		list_FO.add(expectFo1);
//		list_FO.add(expectFo2);
//		
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		EasyMock.expect(
//				mongoTemplate.findAll(FileObject.class))
//				.andReturn(list_FO);
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		List<String> result_Id = domainDao.findFileObjectId();
//		EasyMock.verify(mongoTemplate);
//		
//		assertEquals(2,result_Id.size());
//		
//	}
	
	
	
//	@Test
//	public void testInsertVideo() {
//		Capture<Video> capturedObject = new Capture<Video>(CaptureType.ALL);
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//
//		Video v0 = new Video();
//		// v0.setPackage_id("311512");
//		v0.setFileName("videos/PRO87/test555/test_PRO87.tar");
//		v0.setPath("videos/PRO87/test555/");
//		v0.setRuntime("2222");
//		v0.setStatus("NEW");
//		v0.setType("FEATURE-PRO87");
//		v0.setUniqueId("test_pro87");
//
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		mongoTemplate.insert(EasyMock.capture(capturedObject));
//		EasyMock.expectLastCall().once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		domainDao.insertVideo(v0);
//		EasyMock.verify(mongoTemplate);
//
//		Video capturedVideo = capturedObject.getValue();
//
//		assertNotNull(capturedVideo);
//		assertEquals("packakgeId is not equal", "311512",
//				capturedVideo.getPackage_id());
//		assertEquals("filename is not equal",
//				"videos/PRO87/test555/test_PRO87.tar",
//				capturedVideo.getFileName());
//		assertEquals("videos/PRO87/test555/", capturedVideo.getPath());
//		assertEquals("2222", capturedVideo.getRuntime());
//		assertEquals("NEW", capturedVideo.getStatus());
//		assertEquals("FEATURE-PRO87", capturedVideo.getType());
//		assertEquals("test_pro87", capturedVideo.getUniqueId());
//	}
//
//	@Test
//	public void testInsertVideowithNullOjbect() {
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		Capture<Video> capturedObject = new Capture<Video>(CaptureType.ALL);
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		mongoTemplate.insert(EasyMock.capture(capturedObject));
//		EasyMock.expectLastCall().once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		domainDao.insertVideo(null);
//		EasyMock.verify(mongoTemplate);
//
//		Video capturedVideo = capturedObject.getValue();
//
//		// domainDao.insertVideo(null);
//		assertNull("captureVideo is not null", capturedVideo);
//	}
//
//	@Test
//	public void testInsertImage() {
//		Capture<Image> capturedObject = new Capture<Image>(CaptureType.ALL);
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//
//		Image i0 = new Image();
//		i0.setPackage_id("309512");
//		i0.setLengthx("1300");
//		i0.setLengthy("500");
//		i0.setFileName("images/hbo/series/test123/test123_1300x500.jpg");
//		i0.setPath("images/hbo/series/test123/");
//		i0.setStatus("NEW");
//		i0.setType("SERIES_1300x500_B");
//		i0.setUniqueId("123456");
//
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		mongoTemplate.insert(EasyMock.capture(capturedObject));
//		EasyMock.expectLastCall().once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		domainDao.insertImage(i0);
//		EasyMock.verify(mongoTemplate);
//
//		Image capturedImage = capturedObject.getValue();
//
//		assertNotNull(capturedImage);
//		assertEquals("packakgeId is not equal", "309512",
//				capturedImage.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/series/test123/test123_1300x500.jpg",
//				capturedImage.getFileName());
//		assertEquals("images/hbo/series/test123/", capturedImage.getPath());
//		assertEquals("NEW", capturedImage.getStatus());
//		assertEquals("SERIES_1300x500_B", capturedImage.getType());
//		assertEquals("123456", capturedImage.getUniqueId());
//		assertEquals("1300", capturedImage.getLengthx());
//		assertEquals("500", capturedImage.getLengthy());
//	}
//
//	@Test
//	public void testInserImagewithNullOjbect() {
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		Capture<Image> capturedObject = new Capture<Image>(CaptureType.ALL);
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		mongoTemplate.insert(EasyMock.capture(capturedObject));
//		EasyMock.expectLastCall().once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//		EasyMock.verify(mongoTemplate);
//
//		domainDao.insertImage(null);
//		Image captureImage = capturedObject.getValue();
//
//		assertNull("captureImage is not null", captureImage);
//	}
//
//	@Test
//	public void testInsertCC() {
//		Capture<CC> capturedObject = new Capture<CC>(CaptureType.ALL);
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//
//		CC c0 = new CC();
//		c0.setFileName("captions/PRO87/test555/test_PRO87_CC.tar");
//		c0.setPath("captions/PRO87/test555/");
//		c0.setPackage_id("309512");
//		ArrayList<String> l1 = new ArrayList<String>();
//		l1.add("ENG");
//		c0.setLanguages(l1);
//		c0.setStatus("NEW");
//		c0.setUniqueId("test_pro87");
//
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		mongoTemplate.insert(EasyMock.capture(capturedObject));
//		EasyMock.expectLastCall().once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		domainDao.insertCC(c0);
//		EasyMock.verify(mongoTemplate);
//
//		CC capturedCC = capturedObject.getValue();
//
//		assertNotNull(capturedCC);
//		assertEquals("packakgeId is not equal", "309512",
//				capturedCC.getPackage_id());
//		assertEquals("filename is not equal",
//				"captions/PRO87/test555/test_PRO87_CC.tar",
//				capturedCC.getFileName());
//		assertEquals("captions/PRO87/test555/", capturedCC.getPath());
//		assertEquals("NEW", capturedCC.getStatus());
//		assertEquals("ENG", capturedCC.getLanguages().get(0));
//		assertEquals("test_pro87", capturedCC.getUniqueId());
//
//	}
//
//	@Test
//	public void testInserCCwithNullOjbect() {
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		Capture<CC> capturedObject = new Capture<CC>(CaptureType.ALL);
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		mongoTemplate.insert(EasyMock.capture(capturedObject));
//		EasyMock.expectLastCall().once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//		EasyMock.verify(mongoTemplate);
//
//		domainDao.insertCC(null);
//		CC captureCC = capturedObject.getValue();
//
//		assertNull("captureImage is not null", captureCC);
//	}
//
//	@Test
//	public void testFindAllVideo() {
//		ArrayList<Video> test_VO = new ArrayList<Video>();
//		Video[] video = new Video[2];
//
//		Video v0 = new Video();
//		v0.setPackage_id("325142");
//		v0.setFileName("videos/PRO87/test555/test_PRO87.tar");
//		v0.setPath("videos/PRO87/test555/");
//		v0.setRuntime("2222");
//		v0.setStatus("NEW");
//		v0.setType("FEATURE-PRO87");
//		v0.setUniqueId("test_pro87");
//		video[0] = v0;
//
//		Video v1 = new Video();
//		v1.setPackage_id("325142");
//		v1.setFileName("videos/PRO88/test555/test_PRO88.tar");
//		v1.setPath("videos/PRO88/test555/");
//		v1.setRuntime("0");
//		v1.setStatus("NEW");
//		v1.setType("FEATURE-PRO88");
//		v1.setUniqueId("test_pro88");
//		video[1] = v1;
//
//		test_VO.add(video[0]);
//		test_VO.add(video[1]);
//
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		// Query query = new Query();
//		// query.addCriteria(new Criteria("package_id").is("309512"));
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		// EasyMock.expect(mongoTemplate.find((Query)EasyMock.anyObject(),
//		// eq(Video.class))).andReturn(test_VO).once();
//		EasyMock.expect(
//				mongoTemplate.find(EasyMock.isA(Query.class), eq(Video.class)))
//				.andReturn(test_VO).once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		List<Video> verifyVideos = domainDao.findAllVideo("325142");
//		assertEquals(2, verifyVideos.size());
//
//		Video verify_v1 = verifyVideos.get(0);
//		assertEquals("packakgeId is not equal", "325142",
//				verify_v1.getPackage_id());
//		assertEquals("filename is not equal",
//				"videos/PRO87/test555/test_PRO87.tar", verify_v1.getFileName());
//		assertEquals("videos/PRO87/test555/", verify_v1.getPath());
//		assertEquals("2222", verify_v1.getRuntime());
//		assertEquals("NEW", verify_v1.getStatus());
//		assertEquals("FEATURE-PRO87", verify_v1.getType());
//		assertEquals("test_pro87", verify_v1.getUniqueId());
//
//		Video verify_v2 = verifyVideos.get(1);
//		assertEquals("packakgeId is not equal", "325142",
//				verify_v2.getPackage_id());
//		assertEquals("filename is not equal",
//				"videos/PRO88/test555/test_PRO88.tar", verify_v2.getFileName());
//		assertEquals("videos/PRO88/test555/", verify_v2.getPath());
//		assertEquals("0", verify_v2.getRuntime());
//		assertEquals("NEW", verify_v2.getStatus());
//		assertEquals("FEATURE-PRO88", verify_v2.getType());
//		assertEquals("test_pro88", verify_v2.getUniqueId());
//
//		EasyMock.verify(mongoTemplate);
//		// assertNotNull("File object can not be null.",domainDao.findAllVideo(382921));
//	}
//
//	@Test
//	public void testFindAllVideowithNullException() {
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		try {
//			MongoTemplate mongoTemplate = EasyMock
//					.createMock(MongoTemplate.class);
//			// EasyMock.expect(mongoTemplate.find((Query)EasyMock.anyObject(),
//			// eq(Video.class))).andReturn(test_VO).once();
//			EasyMock.expect(
//					mongoTemplate.find(EasyMock.isA(Query.class),
//							eq(Video.class))).andReturn(null).once();
//			EasyMock.replay(mongoTemplate);
//			domainDao.setMongoTemplate(mongoTemplate);
//			domainDao.findAllVideo("hkhkhiuhoi");
//		} catch (NullPointerException e) {
//			assertTrue(true);
//		}
//	}
//
//	@Test
//	public void testFindAllCC() {
//		ArrayList<CC> test_CC = new ArrayList<CC>();
//		CC[] cc = new CC[2];
//
//		CC c0 = new CC();
//		c0.setFileName("captions/PRO87/test555/test_PRO87_CC.tar");
//		c0.setPath("captions/PRO87/test555/");
//		c0.setPackage_id("309512");
//		ArrayList<String> l1 = new ArrayList<String>();
//		l1.add("ENG");
//		c0.setLanguages(l1);
//		c0.setStatus("NEW");
//		c0.setUniqueId("test_pro87");
//		cc[0] = c0;
//
//		CC c1 = new CC();
//		c1.setFileName("captions/PRO88/test555/test555_PRO88_CC.tar");
//		c1.setPath("captions/PRO88/test555/");
//		c1.setPackage_id("309512");
//		ArrayList<String> l2 = new ArrayList<String>();
//		l2.add("ENG");
//		c1.setLanguages(l2);
//		c1.setStatus("NEW");
//		c1.setUniqueId("test_PRO88");
//		cc[1] = c1;
//
//		test_CC.add(cc[0]);
//		test_CC.add(cc[1]);
//
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		EasyMock.expect(
//				mongoTemplate.find(EasyMock.isA(Query.class), eq(CC.class)))
//				.andReturn(test_CC).once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		List<CC> verifyCCs = domainDao.findAllCC("309512");
//
//		CC verify_c1 = verifyCCs.get(0);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_c1.getPackage_id());
//		assertEquals("filename is not equal",
//				"captions/PRO87/test555/test_PRO87_CC.tar",
//				verify_c1.getFileName());
//		assertEquals("captions/PRO87/test555/", verify_c1.getPath());
//		assertEquals("NEW", verify_c1.getStatus());
//		assertEquals("ENG", verify_c1.getLanguages().get(0));
//		assertEquals("test_pro87", verify_c1.getUniqueId());
//
//		CC verify_c2 = verifyCCs.get(1);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_c2.getPackage_id());
//		assertEquals("filename is not equal",
//				"captions/PRO88/test555/test555_PRO88_CC.tar",
//				verify_c2.getFileName());
//		assertEquals("captions/PRO88/test555/", verify_c2.getPath());
//		assertEquals("NEW", verify_c2.getStatus());
//		assertEquals("ENG", verify_c2.getLanguages().get(0));
//		assertEquals("test_PRO88", verify_c2.getUniqueId());
//
//		EasyMock.verify(mongoTemplate);
//	}
//
//	@Test
//	public void testFindAllCCwithNullException() {
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		try {
//			MongoTemplate mongoTemplate = EasyMock
//					.createMock(MongoTemplate.class);
//			// EasyMock.expect(mongoTemplate.find((Query)EasyMock.anyObject(),
//			// eq(Video.class))).andReturn(test_VO).once();
//			EasyMock.expect(
//					mongoTemplate.find(EasyMock.isA(Query.class), eq(CC.class)))
//					.andReturn(null).once();
//			EasyMock.replay(mongoTemplate);
//			domainDao.setMongoTemplate(mongoTemplate);
//			domainDao.findAllCC("hkhkhiuhoi");
//		} catch (NullPointerException e) {
//			assertTrue(true);
//		}
//	}
//
//	@Test
//	public void testFindAllImageId() {
//		ArrayList<Image> test_IM = new ArrayList<Image>();
//		Image[] image = new Image[6];
//
//		Image i0 = new Image();
//		i0.setPackage_id("309512");
//		i0.setLengthx("1300");
//		i0.setLengthy("500");
//		i0.setFileName("images/hbo/series/test123/test123_1300x500.jpg");
//		i0.setPath("images/hbo/series/test123/");
//		i0.setStatus("NEW");
//		i0.setType("SERIES_1300x500_B");
//		i0.setUniqueId("123456");
//		image[0] = i0;
//
//		Image i1 = new Image();
//		i1.setPackage_id("309512");
//		i1.setLengthx("400");
//		i1.setLengthy("90");
//		i1.setFileName("images/hbo/series/test123/test123_400x90.png");
//		i1.setPath("images/hbo/series/test123/");
//		i1.setStatus("NEW");
//		i1.setType("SERIES_400x90");
//		i1.setUniqueId("1234567");
//		image[1] = i1;
//
//		Image i2 = new Image();
//		i2.setPackage_id("309512");
//		i2.setLengthx("100");
//		i2.setLengthy("50");
//		i2.setFileName("images/hbo/season/test234/123_100x50.jpg");
//		i2.setPath("images/hbo/season/test234/");
//		i2.setStatus("NEW");
//		i2.setType("THUMB_MEDIUM");
//		i2.setUniqueId("123");
//		image[2] = i2;
//
//		Image i3 = new Image();
//		i3.setPackage_id("309512");
//		i3.setLengthx("754");
//		i3.setLengthy("424");
//		i3.setFileName("images/hbo/season/test234/1234_754x424.jpg");
//		i3.setPath("images/hbo/season/test234/");
//		i3.setStatus("NEW");
//		i3.setType("SLIDESHOW");
//		i3.setUniqueId("1234");
//		image[3] = i3;
//
//		Image i4 = new Image();
//		i4.setPackage_id("309512");
//		i4.setLengthx("300");
//		i4.setLengthy("130");
//		i4.setFileName("images/hbo/asset/test555/test555_300x130.jpg");
//		i4.setPath("images/hbo/asset/test555/");
//		i4.setStatus("NEW");
//		i4.setType("DETAILS_300x130");
//		i4.setUniqueId("efc2630f8d3da496a53f3cf1584f1adf21fa7078");
//		image[4] = i4;
//
//		Image i5 = new Image();
//		i5.setPackage_id("309512");
//		i5.setLengthx("180");
//		i5.setLengthy("80");
//		i5.setFileName("images/hbo/asset/test555/test555_180x80.jpg");
//		i5.setPath("images/hbo/asset/test555/");
//		i5.setStatus("NEW");
//		i5.setType("THUMB_180x80");
//		i5.setUniqueId("e6f4a36aac8c30a81fc465c54591e0cd1ada790e");
//		image[5] = i5;
//
//		for (Image i_im : image) {
//			test_IM.add(i_im);
//		}
//
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		EasyMock.expect(mongoTemplate.findAll(Image.class)).andReturn(test_IM)
//				.once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		List<String> Im_id = domainDao.findAllImageId();
//		String i_id = Im_id.get(0);
//		assertEquals("309512", i_id);
//	}
//
//	@Test
//	public void testFindAllImage() {
//		ArrayList<Image> test_IM = new ArrayList<Image>();
//		Image[] image = new Image[6];
//
//		Image i0 = new Image();
//		i0.setPackage_id("309512");
//		i0.setLengthx("1300");
//		i0.setLengthy("500");
//		i0.setFileName("images/hbo/series/test123/test123_1300x500.jpg");
//		i0.setPath("images/hbo/series/test123/");
//		i0.setStatus("NEW");
//		i0.setType("SERIES_1300x500_B");
//		i0.setUniqueId("123456");
//		image[0] = i0;
//
//		Image i1 = new Image();
//		i1.setPackage_id("309512");
//		i1.setLengthx("400");
//		i1.setLengthy("90");
//		i1.setFileName("images/hbo/series/test123/test123_400x90.png");
//		i1.setPath("images/hbo/series/test123/");
//		i1.setStatus("NEW");
//		i1.setType("SERIES_400x90");
//		i1.setUniqueId("1234567");
//		image[1] = i1;
//
//		Image i2 = new Image();
//		i2.setPackage_id("309512");
//		i2.setLengthx("100");
//		i2.setLengthy("50");
//		i2.setFileName("images/hbo/season/test234/123_100x50.jpg");
//		i2.setPath("images/hbo/season/test234/");
//		i2.setStatus("NEW");
//		i2.setType("THUMB_MEDIUM");
//		i2.setUniqueId("123");
//		image[2] = i2;
//
//		Image i3 = new Image();
//		i3.setPackage_id("309512");
//		i3.setLengthx("754");
//		i3.setLengthy("424");
//		i3.setFileName("images/hbo/season/test234/1234_754x424.jpg");
//		i3.setPath("images/hbo/season/test234/");
//		i3.setStatus("NEW");
//		i3.setType("SLIDESHOW");
//		i3.setUniqueId("1234");
//		image[3] = i3;
//
//		Image i4 = new Image();
//		i4.setPackage_id("309512");
//		i4.setLengthx("300");
//		i4.setLengthy("130");
//		i4.setFileName("images/hbo/asset/test555/test555_300x130.jpg");
//		i4.setPath("images/hbo/asset/test555/");
//		i4.setStatus("NEW");
//		i4.setType("DETAILS_300x130");
//		i4.setUniqueId("efc2630f8d3da496a53f3cf1584f1adf21fa7078");
//		image[4] = i4;
//
//		Image i5 = new Image();
//		i5.setPackage_id("309512");
//		i5.setLengthx("180");
//		i5.setLengthy("80");
//		i5.setFileName("images/hbo/asset/test555/test555_180x80.jpg");
//		i5.setPath("images/hbo/asset/test555/");
//		i5.setStatus("NEW");
//		i5.setType("THUMB_180x80");
//		i5.setUniqueId("e6f4a36aac8c30a81fc465c54591e0cd1ada790e");
//		image[5] = i5;
//
//		for (Image i_im : image) {
//			test_IM.add(i_im);
//		}
//
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		MongoTemplate mongoTemplate = EasyMock.createMock(MongoTemplate.class);
//		EasyMock.expect(
//				mongoTemplate.find(EasyMock.isA(Query.class), eq(Image.class)))
//				.andReturn(test_IM).once();
//		EasyMock.replay(mongoTemplate);
//		domainDao.setMongoTemplate(mongoTemplate);
//
//		List<Image> verifyImages = domainDao.findAllImage("309512");
//
//		Image verify_i1 = verifyImages.get(0);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_i1.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/series/test123/test123_1300x500.jpg",
//				verify_i1.getFileName());
//		assertEquals("images/hbo/series/test123/", verify_i1.getPath());
//		assertEquals("NEW", verify_i1.getStatus());
//		assertEquals("SERIES_1300x500_B", verify_i1.getType());
//		assertEquals("123456", verify_i1.getUniqueId());
//		assertEquals("1300", verify_i1.getLengthx());
//		assertEquals("500", verify_i1.getLengthy());
//
//		Image verify_i2 = verifyImages.get(1);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_i2.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/series/test123/test123_400x90.png",
//				verify_i2.getFileName());
//		assertEquals("images/hbo/series/test123/", verify_i2.getPath());
//		assertEquals("NEW", verify_i2.getStatus());
//		assertEquals("SERIES_400x90", verify_i2.getType());
//		assertEquals("1234567", verify_i2.getUniqueId());
//		assertEquals("400", verify_i2.getLengthx());
//		assertEquals("90", verify_i2.getLengthy());
//
//		Image verify_i3 = verifyImages.get(2);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_i3.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/season/test234/123_100x50.jpg",
//				verify_i3.getFileName());
//		assertEquals("images/hbo/season/test234/", verify_i3.getPath());
//		assertEquals("NEW", verify_i3.getStatus());
//		assertEquals("THUMB_MEDIUM", verify_i3.getType());
//		assertEquals("123", verify_i3.getUniqueId());
//		assertEquals("100", verify_i3.getLengthx());
//		assertEquals("50", verify_i3.getLengthy());
//
//		Image verify_i4 = verifyImages.get(3);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_i4.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/season/test234/1234_754x424.jpg",
//				verify_i4.getFileName());
//		assertEquals("images/hbo/season/test234/", verify_i4.getPath());
//		assertEquals("NEW", verify_i4.getStatus());
//		assertEquals("SLIDESHOW", verify_i4.getType());
//		assertEquals("1234", verify_i4.getUniqueId());
//		assertEquals("754", verify_i4.getLengthx());
//		assertEquals("424", verify_i4.getLengthy());
//
//		Image verify_i5 = verifyImages.get(4);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_i5.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/asset/test555/test555_300x130.jpg",
//				verify_i5.getFileName());
//		assertEquals("images/hbo/asset/test555/", verify_i5.getPath());
//		assertEquals("NEW", verify_i5.getStatus());
//		assertEquals("DETAILS_300x130", verify_i5.getType());
//		assertEquals("efc2630f8d3da496a53f3cf1584f1adf21fa7078",
//				verify_i5.getUniqueId());
//		assertEquals("300", verify_i5.getLengthx());
//		assertEquals("130", verify_i5.getLengthy());
//
//		Image verify_i6 = verifyImages.get(5);
//		assertEquals("packakgeId is not equal", "309512",
//				verify_i6.getPackage_id());
//		assertEquals("filename is not equal",
//				"images/hbo/asset/test555/test555_180x80.jpg",
//				verify_i6.getFileName());
//		assertEquals("images/hbo/asset/test555/", verify_i6.getPath());
//		assertEquals("NEW", verify_i6.getStatus());
//		assertEquals("THUMB_180x80", verify_i6.getType());
//		assertEquals("e6f4a36aac8c30a81fc465c54591e0cd1ada790e",
//				verify_i6.getUniqueId());
//		assertEquals("180", verify_i6.getLengthx());
//		assertEquals("80", verify_i6.getLengthy());
//
//		EasyMock.verify(mongoTemplate);
//
//	}
//
//	@Test
//	public void testFindAllImagewithNullException() {
//		DomainDaoImpl domainDao = new DomainDaoImpl();
//		try {
//			MongoTemplate mongoTemplate = EasyMock
//					.createMock(MongoTemplate.class);
//			// EasyMock.expect(mongoTemplate.find((Query)EasyMock.anyObject(),
//			// eq(Video.class))).andReturn(test_VO).once();
//			EasyMock.expect(
//					mongoTemplate.find(EasyMock.isA(Query.class),
//							eq(Image.class))).andReturn(null).once();
//			EasyMock.replay(mongoTemplate);
//			domainDao.setMongoTemplate(mongoTemplate);
//			domainDao.findAllImage("hkhkhiuhoi");
//		} catch (NullPointerException e) {
//			assertTrue(true);
//		}
//	}
	
}
