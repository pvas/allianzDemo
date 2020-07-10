package com.allianz.coreader.controllers;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import com.allianz.coreader.services.COReaderService;

@SpringBootTest
public class COReaderControllerTest  {
	
	@Mock Validator beanValidator;

	@Mock COReaderService coReaderService;
	
	@Mock Set<ConstraintViolation<COTwoMeasureDTO>> errors;
	
	@InjectMocks private COReaderController controller;
	
	private COTwoMeasureDTO coTwoMeasureDTO;
	private HttpServletRequest httpRequest;
    private HttpServletResponse httpResponse;
    		
	@BeforeEach
	public void setInitialValues() {
		this.coTwoMeasureDTO = new COTwoMeasureDTO();
		this.httpRequest   =  mock(HttpServletRequest.class);
		this.httpResponse  = mock(HttpServletResponse.class);
	}	
	
	@Test
	public void isControllerInjectedTest() {
		Assert.assertNotNull(controller);
	}

	@Test
	public void saveCOTwoReadingWithValidDTOTest() {
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId("1");
		
		doReturn(true).when(coReaderService).doesSensorExist("1");
		
		Assert.assertEquals(controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse),
				new ResponseEntity<>(HttpStatus.CREATED));
		
		verify(coReaderService, atLeastOnce()).doesSensorExist(coTwoMeasureDTO.getSensorId());
		verify(coReaderService,atLeastOnce()).processCOValue(coTwoMeasureDTO);
	}
	
	
	@Test
	public void saveCOTwoReadingWithInValidDTOTest() {
		coTwoMeasureDTO.setCoTwoMeasure(457899);
		coTwoMeasureDTO.setSensorId(null);
		Assert.assertEquals(controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse).getStatusCode(),
				HttpStatus.BAD_REQUEST);
	}

	
	@Test
	public void saveCOTwoReadingsWithAEmptyDTOTest() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator realBeanValidator = factory.getValidator();
		Set<ConstraintViolation<COTwoMeasureDTO>> errors = realBeanValidator.validate(coTwoMeasureDTO);
		
		doReturn(errors).when(beanValidator).validate(coTwoMeasureDTO);
		Assert.assertEquals(controller.coTwoReader(coTwoMeasureDTO, httpRequest, httpResponse).getStatusCode(),
				HttpStatus.BAD_REQUEST);
		
		verify(coReaderService, never()).doesSensorExist(null);
		verify(coReaderService, never()).processCOValue(coTwoMeasureDTO);
	}

}