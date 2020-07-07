package com.allianz.coreader.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;

@Getter
public class COTwoMeasureDTO {
	
	@Positive(message = "CO2 Reading must be included and bo a positive float number") 
	private double coTwoMeasure;
	
	@NotNull(message = "districtId must be included")
	@NotEmpty(message = "districtId must be a valid id and registrated in database")
	@Positive(message = "districtId must be a valid id and registrated in database")
	@NotBlank(message = "districtId must be a valid id and registrated in database") 
	private String districtId;


	public double getCoTwoMeasure() {
		return coTwoMeasure;
	}

	public void setCoTwoMeasure(double coTwoMeasure) {
		this.coTwoMeasure = coTwoMeasure;
	}

	public String getDistrictId() {
		return districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("COTwoMeasureDTO [coTwoMeasure=").append(coTwoMeasure).append(", districtId=")
				.append(districtId).append("]").toString();
	}
}
