package com.mkrajcovic.mybooks.db;

import static com.mkrajcovic.mybooks.utils.TypeMapCollector.toTypeMap;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public class ColumnTranslator {

	private static final LocalDateTime INFINITY = LocalDateTime.parse("+292278994-08-17T00:00");

	// this would be great to have mapped from database
	public String toColumnName(String field, Object value) {
		if (value instanceof CharSequence) {
			return "s_" + field;
		} else if (value instanceof Number) {
			return "n_" + field;
		} else if (value instanceof LocalDateTime || value instanceof LocalDate) {
			if ("write".equals(field)) {
				return "t_" + field;
			}
			return "d_" + field;
		} else if(value instanceof Boolean) {
			return "b_" + field;
		} else if (value instanceof UUID) {
			return "u_" + field;
		}
		return field;
	}

	public String removeColumnPrefix(String columnName) {
		int colNameStartIndex = columnName.indexOf('_') + 1;
		if (colNameStartIndex < 0) {
			return columnName;
		}
		return columnName.substring(colNameStartIndex);
	}

	public TypeMap toColumnNames(TypeMap data) {
		return data.entrySet()
			.stream()
			.filter(entry -> entry.getValue() != null)
			.collect(toTypeMap(e -> toColumnName(e.getKey(), e.getValue()), this::adjustTimestampValues));
	}

	private Object adjustTimestampValues(Map.Entry<String, Object> entry) {
		Object value = entry.getValue();
		if (value instanceof LocalDateTime) {
			LocalDateTime dateTime = (LocalDateTime) value;
			if (INFINITY.equals(dateTime)) {
				return "infinity";
			}
			return Timestamp.valueOf(dateTime).toString();
		}
		return value;
	}
}
