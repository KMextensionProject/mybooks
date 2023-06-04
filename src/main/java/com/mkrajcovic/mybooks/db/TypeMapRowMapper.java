package com.mkrajcovic.mybooks.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class TypeMapRowMapper implements RowMapper<TypeMap> {

	private ColumnTranslator translator;

	public TypeMapRowMapper() {
		this.translator = new ColumnTranslator();
	}

	@Override
	public TypeMap mapRow(ResultSet rs, int rowNum) throws SQLException {
		ResultSetMetaData rsMeta = rs.getMetaData();
		int columnCount = rsMeta.getColumnCount();
		TypeMap mapOfColumnValues = new TypeMap();
		for (int i = 1; i <= columnCount; i++) {
			String name = translator.removeColumnPrefix(rsMeta.getColumnName(i));
			Object value = rs.getObject(i);
			mapOfColumnValues.put(name, resolveDateTimeValueType(value));
		}
		return mapOfColumnValues;
	}

	private Object resolveDateTimeValueType(Object value) {
		if (value instanceof java.sql.Timestamp) {
			return ((java.sql.Timestamp) value).toLocalDateTime();
		} else if (value instanceof java.sql.Date) {
			return ((java.sql.Date) value).toLocalDate();
		} else {
			return value;
		}
	}
}
