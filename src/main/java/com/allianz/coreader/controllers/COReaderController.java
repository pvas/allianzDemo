package com.allianz.coreader.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.allianz.coreader.dtos.COTwoMeasureDTO;

@RestController
@RequestMapping(value = "/read")
public class COReaderController {

	@PostMapping
	public ResponseEntity<Void> coTwoReader(@RequestBody final COTwoMeasureDTO coTwoMeasureDTO,
			final HttpServletRequest httpRequest, final HttpServletResponse httpResponse) {

		return new ResponseEntity<>(HttpStatus.OK);
	}

}