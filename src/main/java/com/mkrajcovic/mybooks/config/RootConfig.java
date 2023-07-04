package com.mkrajcovic.mybooks.config;

import static com.mkrajcovic.mybooks.enums.CacheNames.BINDING;
import static com.mkrajcovic.mybooks.enums.CacheNames.FORMAT;
import static com.mkrajcovic.mybooks.enums.CacheNames.LANGUAGE;

import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableCaching
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
		BasicDataSource pool = new BasicDataSource();
		pool.setUrl(environment.getProperty("db.url"));
		pool.setUsername(environment.getProperty("db.usr"));
		pool.setPassword(environment.getProperty("db.pwd"));
		pool.setDriverClassName(environment.getProperty("db.dcn"));
		// redirect spring security predefined model to specific schema 
		// so we don't have to configure SQL queries explicitly
		pool.setDefaultSchema(environment.getProperty("db.sec.schema"));

		pool.setMaxTotal(5);
		pool.setMinIdle(1);
		pool.setMaxIdle(2);

		return pool;
	}

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		cacheManager.setCaches(Arrays.asList(
				new ConcurrentMapCache(FORMAT),
				new ConcurrentMapCache(BINDING),
				new ConcurrentMapCache(LANGUAGE)));

		return cacheManager;
	}

}
