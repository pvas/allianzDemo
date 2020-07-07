package com.allianz.coreader.controllers;


import com.allianz.coreader.dtos.COTwoMeasureDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import com.allianz.coreader.config.AbstractTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class COReaderControllerTest extends AbstractTest {

	@Autowired
	COReaderController controller;

	@Test
	public void post() {
		Assert.assertNotNull(controller);
	}

	@Test
	public void coTwoReaderOk() {
		COTwoMeasureDTO coTwoMeasureDTO = new COTwoMeasureDTO();
		final HttpServletRequest httpRequest = null;
		final HttpServletResponse httpResponse = null;
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId("1");
		Assert.assertEquals(controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse), new ResponseEntity<>(HttpStatus.OK));
	}
}