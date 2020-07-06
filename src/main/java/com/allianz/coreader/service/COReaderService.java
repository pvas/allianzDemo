package com.allianz.coreader.service;

import com.allianz.coreader.dtos.COTwoMeasureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class COReaderService {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderService.class);
	
	public void processCOValue(COTwoMeasureDTO coTwoMeasureDTO) {
		LOG.info("DTO Content: {}",coTwoMeasureDTO);
	}
}
