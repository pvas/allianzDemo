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

import com.allianz.coreader.dtos.SensorDTO;
import com.allianz.coreader.services.SensorService;
import com.allianz.coreader.models.Sensor;

@RestController
@RequestMapping(value = "/sensor")
public class SensorController {

	@Autowired
	private SensorService sensorService;

	@Autowired
	private Validator beanValidator;
	
	private static final Logger LOG = LoggerFactory.getLogger(SensorController.class);

	@PostMapping(headers = "Accept=application/json", produces="application/text", consumes="application/json")
	public ResponseEntity<String> addSensor(@RequestBody final SensorDTO sensorDTO,
			final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {

		ResponseEntity<String> responseEntity = null;
		Set<ConstraintViolation<SensorDTO>> errors = beanValidator.validate(sensorDTO);
		
		if (!errors.isEmpty()) {
			StringBuilder errormessages = new StringBuilder();
			errors.forEach((error) -> errormessages.append(" | ").append(error.getMessage()));
			LOG.debug("there were errors trying to save C02 Sensor {}",errormessages);
			responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errormessages.toString());
		}
		
		if (errors.isEmpty() && sensorService.exists(sensorDTO.getDescription())) {
			responseEntity = new ResponseEntity<>(HttpStatus.FOUND);
		}

		if (errors.isEmpty() && responseEntity == null) {
			Sensor newSensor = new Sensor();
			newSensor.setDescription(sensorDTO.getDescription());
			newSensor.setDistrictId(sensorDTO.getDistrictId());
			
			responseEntity = (sensorService.addSensor(newSensor)) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

}