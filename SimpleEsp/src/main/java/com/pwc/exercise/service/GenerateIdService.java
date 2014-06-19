package com.pwc.exercise.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GenerateIdService {
	private static final Log log = LogFactory.getLog(GenerateIdService.class);
	/**
	 * for random num generated
	 * */
	public static String generatePackageId() {
//		String s = "";
//		while (s.length() < 6)
//			s += (int) (Math.random() * 10);
		StringBuilder s = new StringBuilder();
		while(s.length() < 6)
			s.append((int) (Math.random() * 10));
		log.info("the generated ID is " + s.toString());
		return s.toString();
	}

}
