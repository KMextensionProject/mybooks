package com.mkrajcovic.mybooks.db;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.JdbcTemplate;

@Configurable
public class Call {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String schema;
	private String procedure;

	public Call(String procedure) {
		int index;
		if ((index = procedure.indexOf('.')) != -1) {
			schema = procedure.substring(0, index);
			this.procedure = procedure.substring(index + 1);
		}
		this.procedure = procedure;
	}

	public Call(String schema, String procedure) {
		this.schema = schema;
		this.procedure = procedure;
	}
	
	public Call using(TypeMap paramMap) {

		return this;
	}

	public Call using(String paramName, Object paramValue, Object... more) {

		if (more.length % 2 != 0) {
			throw new IllegalArgumentException("Bad argument count for procedure named parameters");
		}
		return this;
	}

	public void asVoid() {
		// execute
	}
	
	public List<TypeMap> asList() {

		return Collections.emptyList();
	}
	
	public TypeMap asMap() {

		return new TypeMap();
	}
}
