package com.allianz.coreader.controllers;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import com.allianz.coreader.service.COReaderService;

@RestController
@RequestMapping(value = "/CO2Readings")
public class COReaderController {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderController.class);

	@Autowired
	private Validator beanValidator;


	@Autowired
	COReaderService coReaderService;

	@PostMapping(headers = "Accept=application/json", produces="application/text", consumes="application/json")
	public ResponseEntity<String> coTwoReader(@RequestBody final COTwoMeasureDTO coTwoMeasureDTO,
			final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {

		ResponseEntity<String> response = null;
		Set<ConstraintViolation<COTwoMeasureDTO>> errors = beanValidator.validate(coTwoMeasureDTO);
			
		if (errors.isEmpty() && !coReaderService.doesSensorExist(coTwoMeasureDTO.getSensorId())) { 
			StringBuilder errormessages = new StringBuilder("The district ")
					.append(coTwoMeasureDTO.getSensorId()).append(" is not stored in the database");
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errormessages.toString());
		}
		
		if (response == null && !errors.isEmpty()) {
			StringBuilder errormessages = new StringBuilder();
			errors.forEach((error) -> errormessages.append(" | ").append(error.getMessage()));
			LOG.debug("there were errors trying to save C02 Reading {}",errormessages);
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errormessages.toString());
		}
		
		
		if (response == null && errors.isEmpty()) {
			coReaderService.processCOValue(coTwoMeasureDTO);
			response = new ResponseEntity<>(HttpStatus.CREATED);
		} 
		
		return response;
	}
}
