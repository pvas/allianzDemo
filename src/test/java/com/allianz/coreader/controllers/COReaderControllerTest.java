package com.allianz.coreader.controllers;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import com.allianz.coreader.service.COReaderService;

@SpringBootTest
public class COReaderControllerTest  {
	
	@Mock Validator beanValidator;

	@Mock COReaderService coReaderService;
	
	@InjectMocks private COReaderController controller;

	@Test
	public void controllerInjectedTest() {
		Assert.assertNotNull(controller);
	}

	@Test
	public void saveCOTwoReadingWithValidDTOTest() {
		COTwoMeasureDTO coTwoMeasureDTO = new COTwoMeasureDTO();
		final HttpServletRequest httpRequest = null;
		final HttpServletResponse httpResponse = null;
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId("1");
		
		doReturn(true).when(coReaderService).doesSensorExist("1");
		
		Assert.assertEquals(controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse),
				new ResponseEntity<>(HttpStatus.CREATED));
	}
	
	
	@Test
	public void saveCOTwoReadingWithInValidDTOTest() {
		COTwoMeasureDTO coTwoMeasureDTO = new COTwoMeasureDTO();
		final HttpServletRequest httpRequest = null;
		final HttpServletResponse httpResponse = null;
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId(null);
		Assert.assertEquals(controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse).getStatusCode(),
				HttpStatus.BAD_REQUEST);
	}
	
	
	@Test
	public void validateMethodsExecutionsTest() {
	        
		COTwoMeasureDTO coTwoMeasureDTO = new COTwoMeasureDTO();
		final HttpServletRequest httpRequest = null;
		final HttpServletResponse httpResponse = null;
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId(null);
		controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse);
		
		verify(beanValidator, atLeastOnce()).validate(coTwoMeasureDTO);
	}

}