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
import com.allianz.coreader.models.District;
import com.allianz.coreader.repositories.COReadingRepository;
import com.allianz.coreader.repositories.DistrictRepository;

@Service
public class COReaderService {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderService.class);

	@Autowired
	COReadingRepository coReadingRepository;

	@Autowired
	DistrictRepository districtRepository;

	public void processCOValue(COTwoMeasureDTO coTwoMeasureDTO) {
		LOG.info("DTO Content: {}", coTwoMeasureDTO);

		COReading coReading = new COReading();
		coReading.setDistrictId(coTwoMeasureDTO.getDistrictId());
		coReading.setCoTwoMeasure(coTwoMeasureDTO.getCoTwoMeasure());
		coReading.setTimeStamp(LocalDateTime.now());

		coReadingRepository.save(coReading);
	}

	public boolean doesDistrictExist(String districtId) {

		Optional<District> districtOptional = districtRepository.findById(districtId);
		District district = null;
		try {
			district = districtOptional.get();
		} catch (NoSuchElementException nsee) {
			LOG.error("The district {} is not stored in the database", districtId);
			return false;
		}
		return district != null;
	}
}
