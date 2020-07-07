package com.allianz.coreader.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class COTwoMeasureDTO {
	
	@Positive(message = "CO2 Reading must be included and bo a positive float number") 
	private double coTwoMeasure;
	
	@NotNull(message =  "sensorId must be included")
	@NotEmpty(message = "sensorId must be a valid id and registrated in database")
	@NotBlank(message = "sensorId must be a valid id and registrated in database") 
	private String sensorId;

	@Override
	public String toString() {
		return new StringBuilder().append("COTwoMeasureDTO [coTwoMeasure=")
				.append(coTwoMeasure).append(", sensorId=")
				.append(sensorId).append("]").toString();
	}
}
