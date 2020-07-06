package com.allianz.coreader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@EnableJpaRepositories(basePackages = "com.allianz.coreader.repository")
@EnableTransactionManagement
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
		configurer.enable("defaultServletName");
	}
}
