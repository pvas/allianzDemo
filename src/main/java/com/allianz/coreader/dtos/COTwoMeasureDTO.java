package com.allianz.coreader.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class COTwoMeasureDTO {
	
	private double coTwoMeasure;
	private String districtId;
	private LocalDateTime timeStamp;

}
