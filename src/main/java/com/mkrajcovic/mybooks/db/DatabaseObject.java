package com.mkrajcovic.mybooks.db;

import static com.mkrajcovic.mybooks.db.AccessibleContext.getBean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class DatabaseObject<T> {

	protected static final Logger LOG = Logger.getAnonymousLogger();

	protected String sourceTable;
	protected String identifier;

	protected final Database database;
	protected final JdbcTemplate jdbcTemplate;

	private ColumnTranslator columnTranslator;

	protected DatabaseObject() {
		database = getBean(Database.class);
		jdbcTemplate = getBean(JdbcTemplate.class);
		columnTranslator = new ColumnTranslator();
	}

	protected abstract void update();

	protected abstract void delete();

	protected abstract T setByData(TypeMap data);

	protected abstract TypeMap toTypeMap();

	protected TypeMap getAsDbRow() {
		return columnTranslator.toColumnNames(toTypeMap());
	}

	public T selectById(Integer id) {
		return setByData(selectByIdInternal(id));
	}

	public T selectByIdWithCheckExistence(Integer id) {
		 TypeMap record = selectByIdInternal(id);
		 if (record.isEmpty()) {
			 throw new IllegalArgumentException("The requested record with id " + id + " does not exist");
		 }
		 return setByData(record);
	}

	private TypeMap selectByIdInternal(Integer id) {
		return database.select()
			.from(sourceTable)
			.where(identifier, id)
			.asMap();
	}

	public void insert() {
		String insertStatement = buildInsertStatement();
		LOG.info(insertStatement);
		// https://stackoverflow.com/questions/201887/primary-key-from-inserted-row-jdbc
		try {
			PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection()
					.prepareStatement(insertStatement);
			preparedStatement.executeUpdate(insertStatement, Statement.RETURN_GENERATED_KEYS);

			ResultSet result = preparedStatement.getGeneratedKeys();
			if (result.next()) {
				TypeMap data = toTypeMap();
				// add new ID to model after insert
				data.put(columnTranslator.removeColumnPrefix(identifier), result.getInt(1));
				setByData(data);
			}
		} catch (SQLException e) {
			LOG.severe(() -> "insert statement failed with " + e.getMessage());
		}
	}

	private String buildInsertStatement() {
		TypeMap data = getAsDbRow();
		data.remove(identifier); // PK assigned by DB

		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO ");
		insert.append(sourceTable);

		StringBuilder columns = new StringBuilder(" (");
		StringBuilder values  = new StringBuilder(" VALUES (");

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			columns.append(entry.getKey());
			columns.append(",");

			Object value = entry.getValue();
			// TODO: centralize this transformation
			if (value instanceof CharSequence) {
				value = value.toString()
					.replace('\n', ' ')
//					.replace("'", "\'")
					.replace('"', '\"'); // regex?!

				value = "'" + value + "'";
			}
			values.append(value);
			values.append(",");
		}
		columns.deleteCharAt(columns.length() - 1);
		columns.append(")");

		values.deleteCharAt(values.length() - 1);
		values.append(")");

		return insert.append(columns)
				.append(values)
				.toString();
	}

	// can be reused by childs
	protected String buildUpdateStatement(TypeMap data) {
		Integer id = data.getInteger(identifier);
		data.remove(identifier);

		StringBuilder update = new StringBuilder("UPDATE ");
		update.append(sourceTable)
			  .append(" SET ");

		for (Map.Entry<String, Object> entry : data.entrySet()) {
			update.append(entry.getKey())
				  .append(" = ");

			Object value = entry.getValue();
			if (value instanceof CharSequence) {
				update.append("'")
					  .append(value)
					  .append("'");
			} else {
				update.append(value);
			}
			update.append(", ");
		}
		update.deleteCharAt(update.length() - 2)
			  .append(" WHERE ")
			  .append(identifier)
			  .append(" = ")
			  .append(id);

		return update.toString();
	}
}
