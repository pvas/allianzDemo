package com.allianz.coreader.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping(value = "/read")
public class COReaderController {

	private static final Logger LOG = LoggerFactory.getLogger(COReaderController.class);

	@Autowired
	COReaderService coReaderService;


	@PostMapping( headers="Accept=application/json")
	public ResponseEntity<Void> coTwoReader(@RequestBody final COTwoMeasureDTO coTwoMeasureDTO,
			final HttpServletRequest httpRequest,
			final HttpServletResponse httpResponse) {
		LOG.info("hablame");
		coReaderService.processCOValue(coTwoMeasureDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}