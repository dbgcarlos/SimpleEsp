package com.pwc.exercise.service;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.easymock.Capture;
import org.easymock.CaptureType;
import org.easymock.EasyMock;

import static org.easymock.EasyMock.eq;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;

import com.pwc.exercise.domain.CC;
import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.domain.Image;
import com.pwc.exercise.domain.Video;
import com.pwc.exercise.exception.SimpleEspException;
import com.pwc.exercise.service.JsonPackageGeneraterService;

public class JsonPackageGeneraterServiceTest {
	private static final Log log = LogFactory.getLog(JsonPackageGeneraterServiceTest.class);
	
	@Test
	public void testSendJsonPackage() throws JsonGenerationException, JsonMappingException, IOException, SimpleEspException {
		FileObject expectFo = new FileObject();
		expectFo.setPackage_id("382921");
		
		ArrayList<Video> expect_VO = new ArrayList<Video>();
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
		Image[] image = new Image[6];
		
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
		
		Image i2 = new Image();
		//i2.setPackage_id("382921");
		i2.setLengthx("100");
		i2.setLengthy("50");
		i2.setFileName("images/hbo/season/test234/123_100x50.jpg");
		i2.setPath("images/hbo/season/test234/");
		i2.setStatus("NEW");
		i2.setType("THUMB_MEDIUM");
		i2.setUniqueId("123");
		image[2] = i2;
		
		Image i3 = new Image();
		//i3.setPackage_id("382921");
		i3.setLengthx("754");
		i3.setLengthy("424");
		i3.setFileName("images/hbo/season/test234/1234_754x424.jpg");
		i3.setPath("images/hbo/season/test234/");
		i3.setStatus("NEW");
		i3.setType("SLIDESHOW");
		i3.setUniqueId("1234");
		image[3] = i3;
		
		Image i4 = new Image();
		//i4.setPackage_id("382921");
		i4.setLengthx("300");
		i4.setLengthy("130");
		i4.setFileName("images/hbo/asset/test555/test555_300x130.jpg");
		i4.setPath("images/hbo/asset/test555/");
		i4.setStatus("NEW");
		i4.setType("DETAILS_300x130");
		i4.setUniqueId("efc2630f8d3da496a53f3cf1584f1adf21fa7078");
		image[4] = i4;
		
		Image i5 = new Image();
		//i5.setPackage_id("382921");
		i5.setLengthx("180");
		i5.setLengthy("80");
		i5.setFileName("images/hbo/asset/test555/test555_180x80.jpg");
		i5.setPath("images/hbo/asset/test555/");
		i5.setStatus("NEW");
		i5.setType("THUMB_180x80");
		i5.setUniqueId("e6f4a36aac8c30a81fc465c54591e0cd1ada790e");
		image[5] = i5;
		
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
		expectFo.setPackage_id("382921");
		
		ObjectMapper mapper = new ObjectMapper();
		
		AmqpTemplate amqpTemplate = EasyMock.createMock(AmqpTemplate.class);
		Capture<Object> capturedPackage = new Capture<Object>(CaptureType.ALL);
		amqpTemplate.convertAndSend(eq("successful"),EasyMock.capture(capturedPackage));
		EasyMock.expectLastCall().once();
		EasyMock.replay(amqpTemplate);
		
		JsonPackageGeneraterService service = new JsonPackageGeneraterService();
		service.setAmqpTemplate(amqpTemplate);
		service.sendJsonPackage(expectFo);
		EasyMock.verify(amqpTemplate);
		
		String generated =(String) capturedPackage.getValue();
		//assertNotNull(generated);
		//URL xmlpath = this.getClass().getClassLoader().getResource("example_json.txt"); 
		//System.out.println(xmlpath); 
		//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(xmlpath.toString()))));
		
		String expect = mapper.writeValueAsString(expectFo);
		
		assertEquals("the result is incorrect.",expect, generated);
		log.info("the generated json package like this:\n" + generated);
		
		//EasyMock.expect(amqpTemplate.convertAndSend((Message)EasyMock.anyObject())).;
		//assertNotNull(mapper.writeValueAsString(expectFo));
		//log.info("the generated json package like this:\n" + mapper.writeValueAsString(expectFo));
		
	}
	
	@Test
	public void testSendJsonPackagewithNullException(){
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String expect = mapper.writeValueAsString(null);
			if(expect.equals("null")){
				throw new JsonGenerationException("");
				//throw new JsonMappingException("");
				//throw new IOException("");
			}
			else
			 fail("do not throw any exception");
		} catch (JsonGenerationException e) {
			assertTrue("catch JsonGenerationException", true);
		} catch (JsonMappingException e) {
			assertTrue("catch JsonMappingException", true);
		} catch (IOException e) {
			assertTrue("catch IOException", true);
		}
	}
}
	
