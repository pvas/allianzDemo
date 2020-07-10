package com.allianz.coreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@ComponentScan("com.allianz.coreader")
@SpringBootApplication
@EnableSwagger2
public class CoreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreaderApplication.class, args);
	}
	
	@Bean
	public Validator beanValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		return factory.getValidator();
	}

	@Bean
	public Docket apiDocket() {

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
						.description("RESTful server application which provides this backend service to store CO2 emission from sensor")
						.title("Allianz CO2 Reader")
						.version("1.0.0")
						.build())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.allianz.coreader.controllers"))
				.paths(PathSelectors.any())
				.paths(regex("(/api/v1.*)|(/register.*)"))
				.build();
	}
}
