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

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private TypeMapRowMapper typeMapRowMapper;

	// rename to metadata + cache
	private DatabaseVendor databaseVendor;
	private boolean autoCommitOn;

	@PostConstruct
	private void init() {
		try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
			autoCommitOn = con.getAutoCommit();
			databaseVendor = DatabaseVendor.getByName(con.getMetaData().getDatabaseProductName());
			if (DatabaseVendor.UNKNOWN.equals(databaseVendor)) {
				LOGGER.severe("Unknown database Vendor " + databaseVendor + " - select API may not work properly");
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		LOGGER.info(toString());
	}

	public boolean isAutoCommitOn() {
		return this.autoCommitOn;
	}

	public DatabaseVendor getDatabaseVendor() {
		return this.databaseVendor;
	}

	public Select select(String... columns) {
		return new Select(jdbcTemplate, typeMapRowMapper, databaseVendor, columns);
	}

//	public Call call(String procedure) {
//		return new Call(procedure);
//	}

	@Override
	public String toString() {
		return "Database [databaseVendor=" + databaseVendor + ", autoCommitOn=" + autoCommitOn + "]";
	}
}
