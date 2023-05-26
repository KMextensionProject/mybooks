package com.mkrajcovic.mybooks.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:db/connection.properties")
@ComponentScan("com.mkrajcovic.mybooks")
public class RootConfig {

	@Autowired
	private Environment environment;

	@Bean
	public PlatformTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

	@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource(
			environment.getProperty("db.url"),
			environment.getProperty("db.usr"),
			environment.getProperty("db.pwd"));

		dataSource.setDriverClassName(environment.getProperty("db.dcn"));
		// redirect spring security predefined model to specific schema 
		// so we don't have to configure SQL queries explicitly
		dataSource.setSchema(environment.getProperty("db.sec.schema"));
		return dataSource;
	}

	@Bean
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
