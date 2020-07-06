package com.allianz.coreader.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class COTwoMeasureDTO {
	
	private double coTwoMeasure;
	private String districtId;
	private Date timeStamp;

}
