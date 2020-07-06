package com.allianz.coreader.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.allianz.coreader.dto.COTwoMeasureDTO;

@Service
public class COReaderService {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderService.class);
	
	public void processCOValue(COTwoMeasureDTO urlsToreview) {
		LOG.info("DTO Content: {}",urlsToreview);
	}
}
