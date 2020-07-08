package com.allianz.coreader.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.allianz.coreader.dtos.SensorDTO;
import com.allianz.coreader.service.SensorService;

@SpringBootTest
public class SensorControllerTest {

	
	@InjectMocks private SensorController sensorController;
	
	@Mock
	private SensorService manager;

	@Mock
	private Validator beanValidator;
	
	private SensorDTO sensorDTO;
	private HttpServletRequest  httpRequest ; 
	private HttpServletResponse httpResponse;  
	
	
	@Test
	public void isControllerInjectedTest() {
		Assert.assertNotNull(sensorController);
	}
	
	@BeforeEach
	public void setInitialValues() {
		this.sensorDTO     = new SensorDTO();
		this.httpRequest   =  mock(HttpServletRequest.class);
		this.httpResponse  = mock(HttpServletResponse.class);
	}
	
	@Test
	public void saveCOSensorWithValidDTOTest() {
		
		this.sensorDTO.setDescription("This is a valid Sensor");
		this.sensorDTO.setDistrictId("1");
		
		when(manager.exists(anyString())).thenReturn(false);
		when(manager.addSensor(any())).thenReturn(true);
		
		Assert.assertEquals(new ResponseEntity<>(HttpStatus.CREATED), sensorController.addSensor(this.sensorDTO, this.httpRequest, this.httpResponse));
	}
	
	
	@Test
	public void saveCOSensorWithInvalidDTOTest() {
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator realBeanValidator = factory.getValidator();
		Set<ConstraintViolation<SensorDTO>> errors = realBeanValidator.validate(sensorDTO);
		
		
		doReturn(errors).when(beanValidator).validate(sensorDTO);
		when(manager.exists(anyString())).thenReturn(false);
		when(manager.addSensor(any())).thenReturn(true);
		
		Assert.assertEquals(sensorController.addSensor(this.sensorDTO, this.httpRequest, this.httpResponse).getStatusCode(),
				HttpStatus.BAD_REQUEST);
	}
	
	
	@Test
	public void saveCOSenserWithValidDTOAndNotSavedTest() {

		this.sensorDTO.setDescription("This is a valid Sensor");
		this.sensorDTO.setDistrictId("1");

		when(manager.exists(anyString())).thenReturn(false);
		when(manager.addSensor(any())).thenReturn(false);
		
		Assert.assertEquals(sensorController.addSensor(this.sensorDTO, this.httpRequest, this.httpResponse).getStatusCode(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@Test
	public void saveCOSenserWithValidDTOAndAlreadySensorStoredTest() {

		this.sensorDTO.setDescription("This is a valid Sensor");
		this.sensorDTO.setDistrictId("1");

		when(manager.exists(anyString())).thenReturn(true);
		verify(manager, never()).addSensor(any());
		
		Assert.assertEquals(sensorController.addSensor(this.sensorDTO, this.httpRequest, this.httpResponse).getStatusCode(),
				HttpStatus.FOUND);
	}
}

