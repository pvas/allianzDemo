package com.allianz.coreader.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.allianz.coreader.dto.COTwoMeasureDTO;

@RestController
@RequestMapping(value = "/read")
public class COReaderController {

	@PostMapping
	public ResponseEntity<Void> crawlWebPage(@RequestBody final COTwoMeasureDTO urlsToreview,
			final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

}