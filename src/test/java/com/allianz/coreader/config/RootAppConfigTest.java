package com.allianz.coreader.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ContextConfiguration
@ComponentScans(value = { 
		@ComponentScan("com.allianz.coreader.controller")
		//, @ComponentScan("com.allianz.coreader.service"), 
		//@ComponentScan("com.allianz.coreader.model")
		})
//@EnableJpaRepositories(basePackages = "com.marfeel.web.repository")
@EnableTransactionManagement
public class RootAppConfigTest implements WebMvcConfigurer {

	
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configure) {
		configure.enable();
	}
	
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.h2.Driver");
	    dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
	    dataSource.setUsername( "sa" );
	    dataSource.setPassword( "password" );
 
	    return dataSource;
	}
	
	private Properties additionalProperties() {
	    Properties properties = new Properties();
	    properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

	    return properties;
	}
	
	
	@Bean
	   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	      LocalContainerEntityManagerFactoryBean em 
	        = new LocalContainerEntityManagerFactoryBean();
	      em.setDataSource(dataSource());
	      em.setPackagesToScan(new String[] { "com.marfeel.web.repository",
	    		  "com.marfeel.web.model" });
	 
	      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	      em.setJpaVendorAdapter(vendorAdapter);
	      em.setJpaProperties(additionalProperties());
	 
	      return em;
	   }
	/*
	@Bean
	public PlatformTransactionManager transactionManager() {
	    JpaTransactionManager transactionManager = new JpaTransactionManager();
	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
	 
	    return transactionManager;
	}*/
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	    return new PersistenceExceptionTranslationPostProcessor();
	}
}
