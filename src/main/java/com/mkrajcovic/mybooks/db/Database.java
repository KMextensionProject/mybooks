package com.mkrajcovic.mybooks.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Database {

	private static final Logger LOG = Logger.getAnonymousLogger();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TypeMapRowMapper typeMapRowMapper;

	private boolean autoCommitOn;
	private String databaseName;

	@PostConstruct
	private void init() {
		try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
			databaseName = con.getMetaData().getDatabaseProductName();
			autoCommitOn = con.getAutoCommit();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		LOG.info(toString());
	}

	public boolean isAutoCommitOn() {
		return this.autoCommitOn;
	}

	public String getDatabaseName() {
		return this.databaseName;
	}

	public Select select(String... columns) {
		return new Select(jdbcTemplate, typeMapRowMapper, columns);
	}

//	public Call call(String procedure) {
//		return new Call(procedure);
//	}

	@Override
	public String toString() {
		return "Database [dbName="+ databaseName +", autoCommitOn=" + autoCommitOn + "]";
	}
}
