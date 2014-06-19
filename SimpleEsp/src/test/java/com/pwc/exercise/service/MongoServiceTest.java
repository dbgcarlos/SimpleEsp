package com.pwc.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.classextension.EasyMock;
import org.junit.Test;

import com.pwc.exercise.dao.DomainDaoImpl;
import com.pwc.exercise.domain.CC;
import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.domain.Image;
import com.pwc.exercise.domain.Video;
import com.pwc.exercise.exception.SimpleEspException;


public class MongoServiceTest  {
	
	@Test
	public void testDataInMongo() throws SimpleEspException{
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
		//DomainDaoImpl domainDao = new DomainDaoImpl();
		MongoService mongoService = new MongoService();
		
		DomainDaoImpl domainDao = EasyMock.createMock(DomainDaoImpl.class);
		domainDao.insertFileObject(EasyMock.capture(capturedObject));
		EasyMock.expectLastCall().once();
		EasyMock.replay(domainDao);
		mongoService.setDomainDao(domainDao);

		mongoService.dataInMongo(expectFo);
		EasyMock.verify(domainDao);

		FileObject capturedFO = capturedObject.getValue();
		assertNotNull(capturedFO);
		assertEquals("packakgeId is not equal", "382921",
				capturedFO.getPackage_id());
		assertEquals(2, capturedFO.getImages().size());
		assertEquals(2, capturedFO.getVideos().size());
		assertEquals(2, capturedFO.getCcs().size());
	}
	
	@Test
	public void testDataoutofMongo() throws  SimpleEspException{
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
		
		MongoService mongoService = new MongoService();
		DomainDaoImpl domainDao = EasyMock.createMock(DomainDaoImpl.class);
		EasyMock.expect(
				domainDao.findFileObject((String)EasyMock.anyObject()))
				.andReturn(expectFo);
		EasyMock.replay(domainDao);
		mongoService.setDomainDao(domainDao);

		FileObject result_FO = mongoService.dataOutofMongo("382921");

		assertEquals("packakgeId is not equal", "382921",
				result_FO.getPackage_id());

		EasyMock.verify(domainDao);
	}

	
	//@Test
//	public void testDataoutofMongo(){
//		//FileObject expectFO = expectFO();
//		
//		//the object which we want to get
//		FileObject expectFo = new FileObject();
//		expectFo.setPackage_id("382921");
//		
//		ArrayList<Video> expect_VO = new ArrayList<Video>();
//		Video[] video = new Video[2];
//		
//		Video v1 = new Video();
//		v1.setPackage_id("382921");
//		v1.setFileName("videos/PRO87/test555/test_PRO87.tar");
//		v1.setPath("videos/PRO87/test555/");
//		v1.setRuntime("2222");
//		v1.setStatus("NEW");
//		v1.setType("FEATURE-PRO87");
//		v1.setUniqueId("test_pro87");
//		video[0] = v1;
//		
//		Video v2 = new Video();
//		v2.setPackage_id("382921");
//		v2.setFileName("videos/PRO88/test555/test_PRO88.tar");
//		v2.setPath("videos/PRO88/test555/");
//		v2.setRuntime("0");
//		v2.setStatus("NEW");
//		v2.setType("FEATURE-PRO88");
//		v2.setUniqueId("test_pro88");
//		video[1] = v2;
//		
//		expect_VO.add(video[0]);
//		expect_VO.add(video[1]);
//		
//		ArrayList<Image> expect_IM = new ArrayList<Image>();
//		Image[] image = new Image[6];
//		
//		Image i0 = new Image();
//		i0.setPackage_id("382921");
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
//		i1.setPackage_id("382921");
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
//		i2.setPackage_id("382921");
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
//		i3.setPackage_id("382921");
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
//		i4.setPackage_id("382921");
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
//		i5.setPackage_id("382921");
//		i5.setLengthx("180");
//		i5.setLengthy("80");
//		i5.setFileName("images/hbo/asset/test555/test555_180x80.jpg");
//		i5.setPath("images/hbo/asset/test555/");
//		i5.setStatus("NEW");
//		i5.setType("THUMB_180x80");
//		i5.setUniqueId("e6f4a36aac8c30a81fc465c54591e0cd1ada790e");
//		image[5] = i5;
//		
//		for(Image i_im:image){
//			expect_IM.add(i_im);
//		}
//		
//		ArrayList<CC> expect_CC = new ArrayList<CC>();
//		CC[] cc = new CC[2];
//		
//		CC c0 = new CC();
//		c0.setFileName("captions/PRO87/test555/test_PRO87_CC.tar");
//		c0.setPath("captions/PRO87/test555/");
//		c0.setPackage_id("382921");
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
//		c1.setPackage_id("382921");
//		ArrayList<String> l2 = new ArrayList<String>();
//		l2.add("ENG");
//		c1.setLanguages(l2);
//		c1.setStatus("NEW");
//		c1.setUniqueId("test_PRO88");
//		cc[1] = c1;
//		
//		expect_CC.add(cc[0]);
//		expect_CC.add(cc[1]);
//		
//		expectFo.setImages(expect_IM);
//		expectFo.setCcs(expect_CC);
//		expectFo.setVideos(expect_VO);
//		expectFo.setPackage_id("382921");
//		
//		//crate a mock dao
//		DomainDaoImpl domainDao  = EasyMock.createMock(DomainDaoImpl.class);
//		
//		//a video list which we need
//		ArrayList<Video> test_VO = new ArrayList<Video>();
//		Video[] t_video = new Video[2];
//		
//		Video t_v1 = new Video();
//		t_v1.setPackage_id("382921");
//		t_v1.setFileName("videos/PRO87/test555/test_PRO87.tar");
//		t_v1.setPath("videos/PRO87/test555/");
//		t_v1.setRuntime("2222");
//		t_v1.setStatus("NEW");
//		t_v1.setType("FEATURE-PRO87");
//		t_v1.setUniqueId("test_pro87");
//		t_video[0] = t_v1;
//		
//		Video t_v2 = new Video();
//		t_v2.setPackage_id("382921");
//		t_v2.setFileName("videos/PRO88/test555/test_PRO88.tar");
//		t_v2.setPath("videos/PRO88/test555/");
//		t_v2.setRuntime("0");
//		t_v2.setStatus("NEW");
//		t_v2.setType("FEATURE-PRO88");
//		t_v2.setUniqueId("test_pro88");
//		t_video[1] = t_v2;
//		
//		test_VO.add(t_video[0]);
//		test_VO.add(t_video[1]);
//		
//		EasyMock.expect(domainDao.findAllVideo((String)EasyMock.anyObject())).andReturn(test_VO);
//		
//		//an image list which we need
//		ArrayList<Image> test_IM = new ArrayList<Image>();
//		Image[] t_image = new Image[6];
//		
//		Image t_i0 = new Image();
//		t_i0 .setPackage_id("382921");
//		t_i0 .setLengthx("1300");
//		t_i0.setLengthy("500");
//		t_i0.setFileName("images/hbo/series/test123/test123_1300x500.jpg");
//		t_i0.setPath("images/hbo/series/test123/");
//		t_i0.setStatus("NEW");
//		t_i0.setType("SERIES_1300x500_B");
//		t_i0.setUniqueId("123456");
//		t_image[0] = t_i0;
//		
//		Image t_i1 = new Image();
//		t_i1.setPackage_id("382921");
//		t_i1.setLengthx("400");
//		t_i1.setLengthy("90");
//		t_i1.setFileName("images/hbo/series/test123/test123_400x90.png");
//		t_i1.setPath("images/hbo/series/test123/");
//		t_i1.setStatus("NEW");
//		t_i1.setType("SERIES_400x90");
//		t_i1.setUniqueId("1234567");
//		t_image[1] = t_i1;
//		
//		Image t_i2 = new Image();
//		t_i2.setPackage_id("382921");
//		t_i2.setLengthx("100");
//		t_i2.setLengthy("50");
//		t_i2.setFileName("images/hbo/season/test234/123_100x50.jpg");
//		t_i2.setPath("images/hbo/season/test234/");
//		t_i2.setStatus("NEW");
//		t_i2.setType("THUMB_MEDIUM");
//		t_i2.setUniqueId("123");
//		t_image[2] = t_i2;
//		
//		Image t_i3 = new Image();
//		t_i3.setPackage_id("382921");
//		t_i3.setLengthx("754");
//		t_i3.setLengthy("424");
//		t_i3.setFileName("images/hbo/season/test234/1234_754x424.jpg");
//		t_i3.setPath("images/hbo/season/test234/");
//		t_i3.setStatus("NEW");
//		t_i3.setType("SLIDESHOW");
//		t_i3.setUniqueId("1234");
//		t_image[3] = t_i3;
//		
//		Image t_i4 = new Image();
//		t_i4.setPackage_id("382921");
//		t_i4.setLengthx("300");
//		t_i4.setLengthy("130");
//		t_i4.setFileName("images/hbo/asset/test555/test555_300x130.jpg");
//		t_i4.setPath("images/hbo/asset/test555/");
//		t_i4.setStatus("NEW");
//		t_i4.setType("DETAILS_300x130");
//		t_i4.setUniqueId("efc2630f8d3da496a53f3cf1584f1adf21fa7078");
//		t_image[4] = t_i4;
//		
//		Image t_i5 = new Image();
//		t_i5.setPackage_id("382921");
//		t_i5.setLengthx("180");
//		t_i5.setLengthy("80");
//		t_i5.setFileName("images/hbo/asset/test555/test555_180x80.jpg");
//		t_i5.setPath("images/hbo/asset/test555/");
//		t_i5.setStatus("NEW");
//		t_i5.setType("THUMB_180x80");
//		t_i5.setUniqueId("e6f4a36aac8c30a81fc465c54591e0cd1ada790e");
//		t_image[5] = t_i5;
//		
//		for(Image i_im:t_image){
//			test_IM.add(i_im);
//		}
//		
//		EasyMock.expect(domainDao.findAllImage((String)EasyMock.anyObject())).andReturn(test_IM);
//		
//		//a CC list which we need
//		ArrayList<CC> test_CC = new ArrayList<CC>();
//		CC[] test_cc = new CC[2];
//		
//		CC t_c0 = new CC();
//		t_c0.setFileName("captions/PRO87/test555/test_PRO87_CC.tar");
//		t_c0.setPath("captions/PRO87/test555/");
//		t_c0.setPackage_id("382921");
//		ArrayList<String> t_l1 = new ArrayList<String>();
//		t_l1.add("ENG");
//		t_c0.setLanguages(l1);
//		t_c0.setStatus("NEW");
//		t_c0.setUniqueId("test_pro87");
//		test_cc[0] = t_c0;
//		
//		CC t_c1 = new CC();
//		t_c1.setFileName("captions/PRO88/test555/test555_PRO88_CC.tar");
//		t_c1.setPath("captions/PRO88/test555/");
//		t_c1.setPackage_id("382921");
//		ArrayList<String> t_l2 = new ArrayList<String>();
//		t_l2.add("ENG");
//		t_c1.setLanguages(l2);
//		t_c1.setStatus("NEW");
//		t_c1.setUniqueId("test_PRO88");
//		test_cc[1] = t_c1;
//		
//		test_CC.add(test_cc[0]);
//		test_CC.add(test_cc[1]);
//		
//		EasyMock.expect(domainDao.findAllCC((String)EasyMock.anyObject())).andReturn(test_CC);
//		EasyMock.replay(domainDao);
//		
//		MongoService mongo = new MongoService();
//		mongo.setDomainDao(domainDao);
//		
//		FileObject e_fo = mongo.dataOutofMongo("382921");
//		assertEquals(expectFo.getPackage_id(), e_fo.getPackage_id());
//		EasyMock.verify(domainDao);
//	}
}
