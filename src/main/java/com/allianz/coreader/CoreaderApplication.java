package com.allianz.coreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



@SpringBootApplication
@ComponentScan("com.allianz.coreader")
public class CoreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreaderApplication.class, args);
	}
	
	@Bean
	public Validator coTwoValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

}
