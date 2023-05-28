package com.mkrajcovic.mybooks.db;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

// TODO: add documentation with hints
public final class Select {

	private static final Logger LOG = Logger.getAnonymousLogger();

	private JdbcTemplate jdbcTemplate;
	private StringBuilder selectBuilder;
	private StringBuilder whereBuilder;
	private boolean isWhereClauseApplied;
	private boolean distinctAlreadyApplied;
	private int requestedColumnsCount;
	private int limit;
	private Paging pagingBound;
	private Set<OrderBy> orderByColumns;
	private RowMapper<TypeMap> cMapRowMapper;
	private ColumnTranslator fieldTranslator;
	private DatabaseVendor databaseVendor;

	Select(JdbcTemplate jdbcTemplate, RowMapper<TypeMap> rowMapper, DatabaseVendor vendor, String... columns) {
		this.jdbcTemplate = jdbcTemplate;
		this.selectBuilder = new StringBuilder("SELECT ");
		this.whereBuilder = new StringBuilder();
		this.requestedColumnsCount = columns.length;
		this.cMapRowMapper = rowMapper;
		this.orderByColumns = new LinkedHashSet<>();
		this.fieldTranslator = new ColumnTranslator();
		this.databaseVendor = vendor;
		select(columns);
	}

	// TODO: test this
	public Select(Select otherSelect) {
		this.jdbcTemplate = otherSelect.jdbcTemplate;
		this.selectBuilder = new StringBuilder(otherSelect.selectBuilder);
		this.whereBuilder = new StringBuilder(otherSelect.whereBuilder);
		this.isWhereClauseApplied = otherSelect.isWhereClauseApplied;
		this.requestedColumnsCount = otherSelect.requestedColumnsCount;
	}

	private Select select(String... columns) {
		if (columns.length != 0) {
			selectBuilder.append(String.join(", ", columns));
		} else {
			selectBuilder.append("*");
		}
		return this;
	}

	public Select distinct() {
		if (distinctAlreadyApplied) {
			return this;
		}
		distinctAlreadyApplied = true;
		selectBuilder.insert(7, "DISTINCT ");
		return this;
	}

	public Select from(String tableName) {
		selectBuilder.append(" FROM ")
					 .append(tableName);
		return this;
	}

	public Select where(String column, Object value) {
		if (value instanceof QueryOperator) {
			QueryOperator operator = (QueryOperator) value;
			if (QueryOperator.IS_NOT_NULL.equals(operator) 
				|| QueryOperator.IS_NULL.equals(operator)) {
				return where(column, operator, null);
			} else {
				throw new IllegalArgumentException("Cannot use " + operator + " without right-hand side value");
			}
		}
		return where(column, QueryOperator.EQUALS, value);
	}

	/*
	 * TODO: sanitize the inner select = must contain one return column
	 * must return one column even if there is "WHERE IN"
	 * 
	 * ... or let the db propagate the error?
	 * ... I think we should not call db if we can find out there is 
	 * something wrong with the statement that we can check
	 * 
	 * select * from ref.t_formular where n_ziadost_id = (one column + where)
	 * select * from ref.t_formular where n_ziadost_id in (one column)
	 */
	public Select where(String column, Select innerSelect) {
		appendWhereClauseConnector();
		whereBuilder.append(column)
					.append(" = (")
					.append(innerSelect.selectBuilder)
					.append(")");
		return this;
	}

	public Select where(String customWhere) {
		appendWhereClauseConnector();
		if (customWhere.contains("WHERE ")) {
			customWhere = customWhere.replace("WHERE ", "");
		}
		whereBuilder.append(customWhere);
		return this;
	}

	public Select where(String column, QueryOperator queryOperator, Object value) {
		appendWhereClauseConnector();
		whereBuilder.append(column)
					.append(queryOperator.applyOperationOnValue(value));
		return this;
	}

	public Select where(QueryParams queryParams) {
		String field;
		Object value;
		for (Map.Entry<String, Object> entry : queryParams.getQueryEntries()) {
			value = entry.getValue();
			field = fieldTranslator.toColumnName(entry.getKey(), value); // timestamp passed by UI?

			where(field, value);
		}
		return this;
	}

	public <V> Select whereIn(String column, List<V> values) {
		return whereIn(column, values, false);
	}

	public <V> Select whereIn(String column, List<V> values, boolean negate) {
		if (values.isEmpty()) {
			// TODO: log that the method is not going to do anything on empty values
			return this;
		}
		if (values.size() < 2) {
			// TODO: log that the method is using basic equality where (or do something else?)
			return where(column, values.get(0));
		}

		appendWhereClauseConnector();
		whereBuilder.append(column)
					.append(negate ? " NOT" : "")
					.append(" IN (");

		Stream<String> valuesJoiningStream = values.stream().map(String::valueOf);
		if (values.get(0) instanceof CharSequence) {
			valuesJoiningStream = valuesJoiningStream
					.map(val -> val.replace('\n', ' '))
					.map(val -> val.replace("'", "\'"))
					.map(val -> val.replace('"', '\''))
					.map(val -> "\"".concat(val).concat("\"")); // is this right to use double quotes?
		}
		whereBuilder.append(valuesJoiningStream.collect(Collectors.joining(",")))
					.append(")");
		return this;
	}

	// TODO: implement
	public Select whereBetween(String column, Object leftValue, Object rightValue) {
		return this;
	}

	private void appendWhereClauseConnector() {
		if (isWhereClauseApplied) {
			whereBuilder.append(" AND ");
		} else {
			whereBuilder.append(" WHERE ");
			isWhereClauseApplied = true;
		}
	}

	public Join join(String table) {
		return new Join(table);
	}

	public Join leftJoin(String table) {
		return new Join(table, Join.LEFT);
	}

	public Join leftOuterJoin(String table) {
		return new Join(table, Join.LEFT_OUTER);
	}

	public Join rightJoin(String table) {
		return new Join(table, Join.RIGHT);
	}

	public Join rightOuterJoin(String table) {
		return new Join(table, Join.RIGHT_OUTER);
	}

	public class Join {

		static final String INNER_JOIN = " JOIN ";
		static final String INNER = " INNER";
		static final String LEFT = " LEFT";
		static final String RIGHT = " RIGHT";
		static final String LEFT_OUTER = " LEFT OUTER";
		static final String RIGHT_OUTER = " RIGHT OUTER";

		public Join(String table) {
			this(table, "");
		}

		public Join(String table, String joinType) {
			selectBuilder.append(joinType)
						 .append(INNER_JOIN)
						 .append(table);
		}

		public Select on(String leftColumn, String rightColumn) {
			selectBuilder.append(" ON ")
						 .append(leftColumn)
						 .append(" = ")
						 .append(rightColumn);
			return Select.this;
		}

		public Select on(String leftColumn, String rightColumn, String... moreColumns) {
			on(leftColumn, rightColumn);

			int moreColumnsLength = moreColumns.length;
			if (moreColumnsLength != 0 && moreColumnsLength % 2 == 0) {
				for (int i = 0, j = 1; j < moreColumnsLength; i++, j++) {
					selectBuilder.append(" AND ")
								 .append(moreColumns[i])
								 .append(" = ")
								 .append(moreColumns[j]);
				}
			}
			// add some exception if moreColumns is not empty?
			return Select.this;
		}
	}

	public Select limit(int howMany) {
		this.limit = howMany;
		return this;
	}

	public Select range(Paging pagingBound) {
		this.pagingBound = pagingBound;
		return this;
	}

	public class OrderBy {

		private String column;
		private OrderByDirection direction;

		public OrderBy(String column, OrderByDirection direction) {
			this.column = column;
			this.direction = direction;
		}

		public String getColumn() {
			return this.column;
		}

		public OrderByDirection getDirection() {
			return this.direction;
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(column);
		}

		@Override
		public boolean equals(Object other) {
			if (other instanceof OrderBy) {
				return this.column.equals(((OrderBy)other).getColumn());
			}
			return false;
		}
	}

	public enum OrderByDirection {
		ASC("ASC"),
		DESC("DESC");

		private String direction;

		private OrderByDirection(String direction) {
			this.direction = direction;
		}

		public String getValue() {
			return this.direction;
		}
	}

	public Select orderBy(String column) {
		return orderBy(column, OrderByDirection.ASC);
	}

	public Select orderBy(String column, OrderByDirection direction) {
		this.orderByColumns.add(new OrderBy(column, direction));
		return this;
	}

	// TODO: add wherePreparedStatement() - dynamic values
	// TODO: if it returns nothing, let it do it without throwing an exception but return empty map instead
	public TypeMap asMap() {
		appendRemainingParts();
		logSelect();

		try {
			return jdbcTemplate.queryForObject(selectBuilder.toString(), cMapRowMapper);
		} catch (EmptyResultDataAccessException emptyResult) {
//			System.out.println(emptyResult.getMostSpecificCause());
			return new TypeMap();
		}
	}

	// TODO: if it if nothing returns, let it do it without throwing an exception but an empty list
	public List<TypeMap> asList() {
		appendRemainingParts();
		logSelect();

		try {
			return jdbcTemplate.query(selectBuilder.toString(), cMapRowMapper);
		} catch (DataAccessException dae) {
			LOG.severe(() -> dae.getMessage());
			return Collections.emptyList();
		}
	}

	public Boolean asBoolean() {
		appendRemainingParts();
		logSelect();
		return queryForObject(Boolean.class);
	}

	public Long asLong() {
		appendRemainingParts();
		logSelect();
		return queryForObject(Long.class);
	}

	public String asString() {
		appendRemainingParts();
		logSelect();
		return queryForObject(String.class);
	}

	private void appendRemainingParts() {
		appendWhereStatements();

		if (!orderByColumns.isEmpty()) {
			appendOrderBy();
		}

		// prefer one or the other
		if (pagingBound != null) {
			appendPaging();
		} else if (limit > 0) {
			appendLimit();
		}
	}

	private void appendWhereStatements() {
		selectBuilder.append(whereBuilder);
	}

	private void appendOrderBy() {
		selectBuilder.append(" ORDER BY ");

		String columns = orderByColumns.stream()
			.map(e -> e.getColumn() + " " + e.getDirection().getValue())
			.collect(Collectors.joining(", "));

		selectBuilder.append(columns);
	}

	private void appendPaging() {
		selectBuilder.append(" OFFSET ")
					 .append(this.pagingBound.getFrom() - 1)
					 .append(" ROWS FETCH FIRST ")
					 .append(this.pagingBound.getTo())
					 .append(" ROWS ONLY");
	}

	private void appendLimit() {
		switch(databaseVendor) {
		case ORACLE:
			selectBuilder.append(" FETCH FIRST ")
						 .append(limit)
						 .append(" ROWS ONLY");
			break;
		case POSTGRES:
		case MYSQL:
			selectBuilder.append(" LIMIT ")
						 .append(limit);
			break;
		default :
			return;
		}
	}

	private <T> T queryForObject(Class<T> classObj) {
		// 0 means all and above 1 is also impossible to parse as one object
		if (requestedColumnsCount != 1) {
			throw new IllegalArgumentException("Exactly one column must appear in the"
				+ " select clause to directly ask for its value in this manner.");
		}
		try {
			return jdbcTemplate.queryForObject(selectBuilder.toString(), classObj);
		} catch (EmptyResultDataAccessException dae) {
			LOG.severe(() -> dae.getMessage());
			return null;
		}
	}

	private void logSelect() {
		LOG.info(() -> selectBuilder.toString());
	}

	@Override
	public String toString() {
		return this.selectBuilder.toString();
	}
}