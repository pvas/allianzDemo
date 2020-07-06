package com.allianz.coreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.allianz.coreader")
public class CoreaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreaderApplication.class, args);
	}

}
