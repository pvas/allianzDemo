package com.allianz.coreader.service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import com.allianz.coreader.models.COReading;
import com.allianz.coreader.models.Sensor;
import com.allianz.coreader.repositories.COReadingRepository;
import com.allianz.coreader.repositories.SensorRepository;

@Service
public class COReaderService {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderService.class);

	@Autowired
	COReadingRepository coReadingRepository;

	@Autowired
	SensorRepository districtRepository;

	public void processCOValue(COTwoMeasureDTO coTwoMeasureDTO) {
		LOG.info("DTO Content: {}", coTwoMeasureDTO);

		COReading coReading = new COReading();
		coReading.setSensorId(coTwoMeasureDTO.getSensorId());
		coReading.setCoTwoMeasure(coTwoMeasureDTO.getCoTwoMeasure());
		coReading.setTimeStamp(LocalDateTime.now());

		coReadingRepository.save(coReading);
	}

	public boolean doesSensorExist(String sensorId) {

		Optional<Sensor> sensorOptional = districtRepository.findById(sensorId);
		Sensor sensor = null;
		try {
			sensor = sensorOptional.get();
		} catch (NoSuchElementException nsee) {
			LOG.error("The district {} is not stored in the database", sensorId);
			return false;
		}
		return sensor != null;
	}
}
