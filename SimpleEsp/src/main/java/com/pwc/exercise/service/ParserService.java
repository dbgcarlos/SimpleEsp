package com.pwc.exercise.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pwc.exercise.dao.DomainDao;
import com.pwc.exercise.domain.CC;
import com.pwc.exercise.domain.FileObject;
import com.pwc.exercise.domain.Image;
import com.pwc.exercise.domain.Video;
import com.pwc.exercise.exception.SimpleEspException;

public class ParserService {
	@Autowired
	private DomainDao domainDao;
	private static final Log log = LogFactory.getLog(ParserService.class);

	/**
	 * parse the xml file from RabbitMQ
	 * @throws JsonGenerationException 
	 * 
	 * @throws XPathExpressionException
	 * @throws ParserException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ParserConfigurationException
	 */
	public FileObject parserXmlFile(InputStream receMsg) throws SimpleEspException{
		FileObject new_fo = new FileObject();
		log.info("start to parse the message");
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document xmlDocument = builder.parse(receMsg);

			String new_packageId = this.idGenerator();
			new_fo.setPackage_id(new_packageId);
			// domainDao.insertFileId(new_fo.getPackage_id());

			// get all image nodes via parser
			//List<Image> Image_collection = parseImages(xmlDocument,new_packageId);

			//new_fo.setImages(Image_collection);
			new_fo.setImages(parseImages(xmlDocument));
			log.info("***************************************************************************");

			//List<Video> Video_collection = parseVideos(xmlDocument, new_packageId);
			new_fo.setVideos(parseVideos(xmlDocument));
			log.info("********************************************************************");
			new_fo.setCcs(parseCCs(xmlDocument));

		} catch (DOMException e) {
			throw new SimpleEspException("The file has some errors.please fix it",e);
		} catch (ParserConfigurationException e) {
			throw new SimpleEspException("The file has some errors.please fix it",e);
		} catch (SAXException e) {
			throw new SimpleEspException("The file has some errors.please fix it",e);
		} catch (IOException e) {
			throw new SimpleEspException("The file has some errors.please fix it",e);
		}
		return new_fo;
	}

	/**
	 * parser the xml to get all images objects, put into a List
	 * @throws JsonGenerationException 
	 * 
	 * @throws XPathExpressionException
	 */
	public List<Image> parseImages(Document xmlDocument) throws JsonGenerationException {
		List<Image> parseredImages = null;
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			XPathExpression expr_image = xPath.compile("//images//image");
			Object result_image = expr_image.evaluate(xmlDocument,
					XPathConstants.NODESET);
			NodeList nodes_image = (NodeList) result_image;

			parseredImages = new ArrayList<Image>();

			for (int i = 0; i < nodes_image.getLength(); i++) {
				Image new_Image = new Image();
				//new_Image.setPackage_id(new_packageId);
				log.info("The new image type is: "
						+ nodes_image.item(i).getAttributes().getNamedItem("type")
								.getNodeValue());
				new_Image.setType(nodes_image.item(i).getAttributes()
						.getNamedItem("type").getNodeValue());

				// get all image child nodes
				for (int j = 0; j < nodes_image.item(i).getChildNodes().getLength(); j++) {
					Node childNode_image = nodes_image.item(i).getChildNodes()
							.item(j);
					if (childNode_image.getNodeName().equals("unique-id")) {
						log.info("unique-id: " + childNode_image.getTextContent());
						new_Image.setUniqueId(childNode_image.getTextContent());
					} else if (childNode_image.getNodeName().equals("filename")) {
						log.info("filename: " + childNode_image.getTextContent());
						new_Image.setFileName(childNode_image.getTextContent());

						// use regex to split String filename to get each value
						// acquired
						String fileName = childNode_image.getTextContent();
						String[] spilt_result = fileName.split("/");
						String length = new String();
						String path = new String();
						int array_length = spilt_result.length;

						for (String single : spilt_result) {
							if (array_length == 1) {
								length = single;

							} else {
								path += single + "/";
								array_length--;
							}
						}
						new_Image.setPath(path);
						new_Image.setStatus("NEW");

						// use regex to split String filename to get each value
						// acquired
						String[] length_split1 = length.split("_");
						String[] length_split2 = length_split1[1].split("\\.");
						String[] length_split3 = length_split2[0].split("x");
						new_Image.setLengthx(length_split3[0]);
						new_Image.setLengthy(length_split3[1]);

						parseredImages.add(new_Image);

						log.info("the path is: " + path);
						log.info("the length is: " + length);
						log.info("the lengthx is: " + length_split3[0]);
						log.info("the lengthy is: " + length_split3[1]);
					} else
						continue;
				}
				log.info("---------------------------------------------------------------------");
			}
		} catch (XPathExpressionException e) {
			throw new JsonGenerationException("Your xpath expression for parsing images is incorrect.",e);
		} 
		return parseredImages;

	}

	/**
	 * parser the xml to get all video objects, put into a List
	 * @throws XPathExpressionException 
	 */
	public List<Video> parseVideos(Document xmlDocument) throws JsonGenerationException{
		List<Video> parseredVideos = null;
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			XPathExpression expr_video = xPath.compile("//feature//video");
			Object result_video = expr_video.evaluate(xmlDocument,
					XPathConstants.NODESET);
			NodeList nodes_video = (NodeList) result_video;

			parseredVideos = new ArrayList<Video>();

			// get all video nodes
			for (int i = 0; i < nodes_video.getLength(); i++) {
				Video video = new Video();
				//video.setPackage_id(new_packageId);
				log.info("The new video type is: "
						+ nodes_video.item(i).getAttributes().getNamedItem("type")
								.getNodeValue());
				video.setType(nodes_video.item(i).getAttributes()
						.getNamedItem("type").getNodeValue());
				// get all video child nodes
				for (int j = 0; j < nodes_video.item(i).getChildNodes().getLength(); j++) {
					Node childNode_video = nodes_video.item(i).getChildNodes()
							.item(j);
					if (childNode_video.getNodeName().equals("unique-id")) {
						log.info("unique-id: " + childNode_video.getTextContent());
						video.setUniqueId(childNode_video.getTextContent());
					} else if (childNode_video.getNodeName().equals("runtime")) {
						log.info("runtime: " + childNode_video.getTextContent());
						video.setRuntime(childNode_video.getTextContent());
					} else if (childNode_video.getNodeName().equals("filename")) {
						log.info("filename: " + childNode_video.getTextContent());

						// use regex to split String filename to get each value
						// acquired
						String fileName = childNode_video.getTextContent();
						video.setFileName(childNode_video.getTextContent());
						String[] spilt_result = fileName.split("/");
						String length = new String();
						String path = new String();
						int array_length = spilt_result.length;

						for (String single : spilt_result) {
							if (array_length == 1) {
								length = single;
							} else {
								path += single + "/";
								array_length--;
							}
						}
						video.setPath(path);
						video.setStatus("NEW");

						parseredVideos.add(video);

						log.info("the path is: " + path);
						log.info("the length is: " + length);
					}
					else
						continue;
				}
				log.info("---------------------------------------------------------------------");
			}
		} catch (XPathExpressionException e) {
			throw new JsonGenerationException("Your xpath expression for parsing videos is incorrect.",e);
		} 
		return parseredVideos;
	}
	
	public List<CC> parseCCs(Document xmlDocument) throws JsonGenerationException{
		List<CC> parseredCCs = null;
		try {
			XPath xPath = XPathFactory.newInstance().newXPath();
			XPathExpression expr_cc = xPath.compile("//video//closed-caption");
			Object result_cc = expr_cc.evaluate(xmlDocument,
					XPathConstants.NODESET);
			NodeList nodes_cc = (NodeList) result_cc;

			parseredCCs = new ArrayList<CC>();
			// get all cc nodes
			for (int i = 0; i < nodes_cc.getLength(); i++) {
				CC cc = new CC();
				//cc.setPackage_id(new_packageId);
				// get all cc child nodes
				Node parent = nodes_cc.item(i).getParentNode();
				String uniqueId = parent.getChildNodes().item(1)
						.getTextContent();
				cc.setUniqueId(uniqueId);
				log.info("cc unique id is: " + uniqueId);
				for (int j = 0; j < nodes_cc.item(i).getChildNodes()
						.getLength(); j++) {
					Node childNode_cc = nodes_cc.item(i).getChildNodes()
							.item(j);
					if (childNode_cc.getNodeName().equals("filename")) {
						log.info("filename: " + childNode_cc.getTextContent());
						String fileName = childNode_cc.getTextContent();
						cc.setFileName(childNode_cc.getTextContent());

						// use regex to split String filename to get each value
						// acquired
						String[] spilt_result = fileName.split("/");
						String length = new String();
						String path = new String();
						int array_length = spilt_result.length;

						for (String single : spilt_result) {
							if (array_length == 1) {
								length = single;
							} else {
								path += single + "/";
								array_length--;
							}
						}
						cc.setStatus("NEW");
						cc.setPath(path);

						log.info("the path is: " + path);
						log.info("the length is: " + length);

					} else if (childNode_cc.getNodeName().equals("languages")) {
						ArrayList<String> listLanguage = new ArrayList<String>();
						NodeList languages = (NodeList) childNode_cc;
						for (int m = 0; m < languages.getLength(); m++) {
							Node language = languages.item(m);
							if (language.getNodeName().equals("language")) {
								log.info("language is: "
										+ language.getTextContent());
								listLanguage.add(language.getTextContent());
							}
						}
						cc.setLanguages(listLanguage);
						parseredCCs.add(cc);

					} else
						continue;
				}
				log.info("---------------------------------------------------------------------");
			}
		} catch (XPathExpressionException e) {
			throw new JsonGenerationException("Your xpath expression for parsing CCs is incorrect.",e);
		}
		return parseredCCs;
	} 

	/**
	 * generate a new package id, if the new id exists,regenerate a new one
	 * until it meets the request.
	 */
	public String idGenerator() {
		String new_packageId = null;
		do {
			new_packageId = GenerateIdService.generatePackageId();

		} while (domainDao.findFileObjectId().contains(new_packageId));
		return new_packageId;
	}

	public DomainDao getDomainDao() {
		return domainDao;
	}

	public void setDomainDao(DomainDao domainDao) {
		this.domainDao = domainDao;
	}
}
