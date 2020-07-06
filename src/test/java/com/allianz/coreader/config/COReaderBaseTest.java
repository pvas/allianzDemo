package com.allianz.coreader.config;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = RootAppConfigTest.class)
public abstract class COReaderBaseTest {

	protected static final String INIT_DATABASE_SQL_URL = "/db/init-db.sql";
	
	@Autowired
	private DataSource dataSource;

	@Before
	public void setUp() {
		new ResourceDatabasePopulator(new ClassPathResource(INIT_DATABASE_SQL_URL))
				.execute(dataSource);
	}
}