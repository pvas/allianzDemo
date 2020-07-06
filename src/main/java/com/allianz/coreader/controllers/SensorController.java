package com.allianz.coreader.controllers;

import com.allianz.coreader.dtos.SensorDTO;
import com.allianz.coreader.managers.SensorManager;
import com.allianz.coreader.models.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/sensor")
public class SensorController {

	@Autowired
	private SensorManager manager;

	@PostMapping
	public ResponseEntity<Void> addSensor(@RequestBody final SensorDTO sensorDTO,
			final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {

		ResponseEntity<Void> responseEntity;

		if(sensorDTO == null || sensorDTO.getDescription().isEmpty()) {
			responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else if(manager.exists(sensorDTO.getDescription())) {
			responseEntity = new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			Sensor newSensor = new Sensor();
			newSensor.setDescription(sensorDTO.getDescription());
			if(manager.addSensor(newSensor)) {
				responseEntity = new ResponseEntity<>(HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return responseEntity;
	}

}