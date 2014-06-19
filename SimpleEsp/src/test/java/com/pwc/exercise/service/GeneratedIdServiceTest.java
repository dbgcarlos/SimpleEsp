package com.pwc.exercise.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.pwc.exercise.service.GenerateIdService;

public class GeneratedIdServiceTest {

	@Test
	public void testGeneratedId() {
		char[] generated = GenerateIdService.generatePackageId().toCharArray();
		assertEquals(6, generated.length);
	}

}
