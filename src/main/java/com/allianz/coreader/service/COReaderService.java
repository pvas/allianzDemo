package com.allianz.coreader.service;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import com.allianz.coreader.models.COReading;
import com.allianz.coreader.repositories.COReadingRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class COReaderService {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderService.class);  
	
	@Autowired
	COReadingRepository coReadingRepository;
	
	public void processCOValue(COTwoMeasureDTO coTwoMeasureDTO) {
		LOG.info("DTO Content: {}",coTwoMeasureDTO);
		
		
		COReading coReading = new COReading();
		coReading.setDistrictId(coTwoMeasureDTO.getDistrictId());
		coReading.setCoTwoMeasure(coTwoMeasureDTO.getCoTwoMeasure());
		coReading.setTimeStamp(LocalDateTime.now());
		
		coReadingRepository.save(coReading);
	}
}
