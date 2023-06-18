package com.mkrajcovic.mybooks.db;

import static com.mkrajcovic.mybooks.db.AccessibleContext.getBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public abstract class DatabaseObject<T> {

	protected static final Logger LOG = Logger.getAnonymousLogger();

	protected String sourceTable;
	protected String sourceView;
	protected String identifier;
	protected String rowValidityStartDateColumn;
	protected String rowValidityEndDateColumn;

	protected final Database database;
	protected final JdbcTemplate jdbcTemplate;

	private ColumnTranslator columnTranslator;

	protected DatabaseObject() {
		database = getBean(Database.class);
		jdbcTemplate = getBean(JdbcTemplate.class);
		columnTranslator = new ColumnTranslator();
		handlePropertiesSet();
	}

	private void handlePropertiesSet() {
		initTableMetadata();
		// validate
		Objects.requireNonNull(sourceTable, "sourceTable must be initialized");
		Objects.requireNonNull(identifier, "primary key/identifier must be initialized");
		if (rowValidityStartDateColumn == null) {
			rowValidityStartDateColumn = "d_from";
		}
		if (rowValidityEndDateColumn == null) {
			rowValidityEndDateColumn = "d_to";
		}
	}

	protected abstract void initTableMetadata();

	protected abstract void update();

	protected abstract void delete();

	protected abstract T setByData(TypeMap data);

	protected abstract TypeMap toTypeMap();

	protected TypeMap getAsDbRow() {
		return columnTranslator.toColumnNames(toTypeMap());
	}

	/**
	 * If the sourceView property is set, then it will be preferred over
	 * sourceTable in select statements. It is beneficial if the view is
	 * restricting data in some way e.g. selecting only valid rows based on
	 * validity label or date.
	 *
	 * @param id
	 * @return
	 */
	public T selectById(Integer id) {
		return setByData(selectByIdInternal(id));
	}

	/**
	 * If the sourceView property is set, then it will be preferred over
	 * sourceTable in select statements. It is beneficial if the view is
	 * restricting data in some way e.g. selecting only valid rows based on
	 * validity label or date.<br>
	 * This method also checks the presence of the result and throws
	 * IllegalArgumentException if there is no data found under specified id.
	 *
	 * @param id
	 * @return
	 */
	public T selectByIdWithCheckExistence(Integer id) {
		 TypeMap record = selectByIdInternal(id);
		 if (record.isEmpty()) {
			 throw new IllegalArgumentException("The requested record with id " + id + " does not exist");
		 }
		 return setByData(record);
	}

	private TypeMap selectByIdInternal(Integer id) {
		Assert.notNull(id, "selecting ID should never be null");
		return database.select()
			.from(sourceView != null ? sourceView : sourceTable)
			.where(identifier, id)
			.asMap();
	}

	/**
	 * Inserts the contents of this object into configured sourceTable and
	 * automatically fills the assigned primary key/identifier by database to
	 * this object.
	 */
	public void insert() {
		TypeMap data = getAsDbRow();
		data.remove(identifier); // PK assigned by DB

		String insertStatement = buildInsertStatement(data);
		Object[] values = data.values().toArray();
		LOG.info("execute: " + insertStatement + " with values " + Arrays.toString(values));

		// Spring provides the SimpleJdbcInsert object that could be used for this, 
		// or jdbcTemplate.update(PreparedStatementCreator, GeneratedKeyHolder) but
		// then I would need to add a callback for column name translations..
		// so I do it right here instead by JDBC API
		try {
			PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

			for (int i = 1; i <= values.length; i++) {
				preparedStatement.setObject(i, values[i - 1]);
			}
			preparedStatement.executeUpdate();

			ResultSet result = preparedStatement.getGeneratedKeys();
			if (result.next()) {
				data = toTypeMap();
				// add new ID to model after insert
				data.put(columnTranslator.removeColumnPrefix(identifier), result.getInt(1));
				setByData(data);
			}
		} catch (SQLException e) {
			LOG.severe(() -> "insert statement failed with " + e.getMessage());
		}
	}

	protected String buildInsertStatement(TypeMap data) {
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO ");
		insert.append(sourceTable);

		StringBuilder columns = new StringBuilder(" (");
		StringBuilder values  = new StringBuilder(" VALUES (");

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			columns.append(entry.getKey())
				   .append(",");

			values.append("?,");
		}
		columns.deleteCharAt(columns.length() - 1).append(")");
		values.deleteCharAt(values.length() - 1).append(")");

		return insert.append(columns)
			.append(values)
			.toString();
	}

	protected String buildUpdateStatement(TypeMap data) {
		StringBuilder ups = new StringBuilder("UPDATE ")
			.append(sourceTable)
			.append(" SET ");

		// skip updating identifier
		Integer id = (Integer) data.remove(identifier);
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			ups.append(entry.getKey())
			   .append(" = ?, ");
		}
		// put identifier back at the last position
		// for use in the WHERE clause below
		data.put(identifier, id);

		ups.deleteCharAt(ups.length() - 2)
		  .append(" WHERE ")
		  .append(identifier)
		  .append(" = ?");

		return ups.toString();
	}
}
