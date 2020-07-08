package com.allianz.coreader.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class SensorDTO {

	@NotNull(message =  "description must be included")
	@NotEmpty(message = "description cannot be empty")
	@NotBlank(message = "description cannot be blank") 
	private String description;

	@NotNull(message =  "districtId must be included")
	@NotEmpty(message = "districtId cannot be empty")
	@NotBlank(message = "districtId cannot be blank") 
	private String districtId;
}
