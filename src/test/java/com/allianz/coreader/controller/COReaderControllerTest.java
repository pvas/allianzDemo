package com.allianz.coreader.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.allianz.coreader.config.AbstractTest;
 
public class COReaderControllerTest extends AbstractTest {

	@Autowired
	COReaderController controller;
	
	@Test
	public void controllerInstatiation() {
		assertNotNull(controller);
	}
}