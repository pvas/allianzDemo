package com.allianz.coreader.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class COTwoMeasureDTO {
	private double coTwoMeasure;
	private String districtId;
	private Date timeStamp;


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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	

	@Override
	public String toString() {
		return new StringBuilder().append("COTwoMeasureDTO [coTwoMeasure=").append(coTwoMeasure).append(", districtId=")
				.append(districtId).append(", timeStamp=").append(timeStamp).append("]").toString();
	}
}
