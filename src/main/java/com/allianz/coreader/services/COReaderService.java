package com.allianz.coreader.services;

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
	SensorRepository sensorRepository;

	public void processCOValue(COTwoMeasureDTO coTwoMeasureDTO) {
		LOG.info("DTO Content: {}", coTwoMeasureDTO);

		COReading coReading = new COReading();
		coReading.setSensorId(coTwoMeasureDTO.getSensorId());
		coReading.setCoTwoMeasure(coTwoMeasureDTO.getCoTwoMeasure());
		coReading.setTimeStamp(LocalDateTime.now());

		coReadingRepository.save(coReading);
	}

	public Boolean doesSensorExist(String sensorId) {

		Optional<Sensor> sensorOptional = sensorRepository.findById(sensorId);
		Sensor sensor = null;
		try {
			if(sensorOptional.isPresent())
				sensor = sensorOptional.get();
		} catch (NoSuchElementException nsee) {
			LOG.error("The district {} is not stored in the database", sensorId);
			return Boolean.FALSE;
		}
		return sensor != null;
	}
}
