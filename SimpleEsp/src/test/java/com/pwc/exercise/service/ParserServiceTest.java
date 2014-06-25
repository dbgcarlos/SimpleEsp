package com.pwc.exercise.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.pwc.exercise.domain.CC;
import com.pwc.exercise.domain.Image;
import com.pwc.exercise.domain.Video;

public class ParserServiceTest {
	//private static final Log log = LogFactory.getLog(ParserServiceTest.class);

	@Test
	public void testParseImages() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException{
		ParserService parserService = new ParserService();
		File f = new File("./src/test/resources/example_test.txt");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(new FileInputStream(f));
		List<Image> expect = parserService.parseImages(xmlDocument);
		
		assertEquals(6, expect.size());
	}
	
	@Test
	public void testParseImageswithSAXException() throws ParserConfigurationException, FileNotFoundException, IOException, XPathExpressionException{
		ParserService parserService = new ParserService();
		File f = new File("./src/test/resources/example_testImage.txt");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		try {
			Document xmlDocument = builder.parse(new FileInputStream(f));
		    parserService.parseImages(xmlDocument);
			fail("no exception captured.");
		} catch (SAXException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testParseVideos() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException{
		ParserService parserService = new ParserService();
		File f = new File("./src/test/resources/example_test.txt");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(new FileInputStream(f));
		List<Video> expect = parserService.parseVideos(xmlDocument);
		
		assertEquals(2, expect.size());
	}
	
	@Test
	public void testParseCCs() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException{
		ParserService parserService = new ParserService();
		File f = new File("./src/test/resources/example_test.txt");
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document xmlDocument = builder.parse(new FileInputStream(f));
		List<CC> expect = parserService.parseCCs(xmlDocument);
		
		assertEquals(2, expect.size());
	}
	
	
//	@Test
//	public void testParserXMLFile() throws XPathExpressionException,
//			FileNotFoundException, ParserException {
//		// File f = new File(this.getClass().getResource("").getPath());
//		// System.out.println(f);
//		File f = new File("./src/test/resources/example_test.txt");
//		// BufferedReader br = new BufferedReader(new InputStreamReader(new
//		// FileInputStream(f)));
//		ParserService pService = new ParserService();
//		DomainDaoImpl domainDao = EasyMock.createMock(DomainDaoImpl.class);
//
//		List<String> test_IM_id = new ArrayList<String>();
//		Image[] image = new Image[2];
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
//		for (Image i_im : image) {
//			test_IM_id.add(i_im.getPackage_id());
//		}
//
//		EasyMock.expect(domainDao.findAllImageId()).andReturn(test_IM_id);
//		EasyMock.replay(domainDao);
//		pService.setDomainDao(domainDao);
//
//		FileObject expect = pService.parserXmlFile(new FileInputStream(f));
//		assertNotNull(expect);
//
//		EasyMock.verify(domainDao);
//
//		List<Image> expect_i = expect.getImages();
//		assertEquals(6, expect_i.size());
//	}
//
//	@Test
//	public void testParserXMLFileWithException() throws FileNotFoundException,
//			ParserException {
//		File f = new File("./src/test/resources/example_test.txt");
//		ParserService pService = new ParserService();
//		DomainDaoImpl domainDao = EasyMock.createMock(DomainDaoImpl.class);
//
//		List<String> test_IM_id = new ArrayList<String>();
//		Image[] image = new Image[2];
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
//		for (Image i_im : image) {
//			test_IM_id.add(i_im.getPackage_id());
//		}
//
//		EasyMock.expect(domainDao.findAllImageId()).andReturn(test_IM_id);
//
//		EasyMock.replay(domainDao);
//		pService.setDomainDao(domainDao);
//
//		try {
//			pService.parserXmlFile(new FileInputStream(f));
//		} catch (XPathExpressionException e) {
//			log.error(e);
//			assertTrue(true);
//		}
//		EasyMock.verify(domainDao);
//	}
//
//	@Test
//	public void testidGenerator() {
//		DomainDaoImpl domainDao = EasyMock.createMock(DomainDaoImpl.class);
//
//		List<String> test_IM_id = new ArrayList<String>();
//		Image[] image = new Image[2];
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
//		for (Image i_im : image) {
//			test_IM_id.add(i_im.getPackage_id());
//		}
//		ParserService pService = new ParserService();
//		EasyMock.expect(domainDao.findAllImageId()).andReturn(test_IM_id);
//		EasyMock.replay(domainDao);
//		pService.setDomainDao(domainDao);
//
//		String expect = pService.idGenerator();
//		assertNotNull(expect);
//	}

}
