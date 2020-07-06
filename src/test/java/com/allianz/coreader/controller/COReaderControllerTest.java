package com.allianz.coreader.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
 
public class COReaderControllerTest {

	@Autowired
	COReaderController controller;
	
	@Test
	public void controllerInstatiation() {
		assertNotNull(controller);
	}
}