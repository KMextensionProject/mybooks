package com.mkrajcovic.mybooks.db;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Updates and soft deletes are performed on the single database table directly.
 * Delete performs invalidation of records by updating rowValidityEndDateColumn 
 * property. Update causes executing direct update statement on the database row.
 *
 * @author martin
 */
public abstract class DirectlyUpdatableDatabaseObject<T> extends DatabaseObject<T> {

	/**
	 * Performs update operation on underlying record in database with no record
	 * duplication like it is usually used for storing history records.<br>
	 * Although the value as specified by rowValidityStartDateColumn is updated
	 * to {@code LocalDateTime.now()} value.
	 */
	@Override
	public void update() {
		TypeMap data = getAsDbRow();
		data.put(rowValidityStartDateColumn, Timestamp.valueOf(LocalDateTime.now()).toString());
		String updateStatement = buildUpdateStatement(data);
		LOG.info(updateStatement);
		jdbcTemplate.update(updateStatement);
	}

	/**
	 * Directly updates the database source table by setting the
	 * rowValidityEndDateColumn field to current timestamp, which 
	 * invalidates the record from the moment of this method call.
	 */
	@Override
	public void delete() {
		TypeMap data = new TypeMap(
			identifier, getAsDbRow().getInteger(identifier),
			rowValidityEndDateColumn, Timestamp.valueOf(LocalDateTime.now()).toString());
		String invalidationStatement = buildUpdateStatement(data);
		LOG.info(invalidationStatement);
		jdbcTemplate.update(invalidationStatement);
	}

	/**
	 * Permanently deletes the record from database by the identifier 
	 * value of the underlying object.
	 */
	public void hardDelete() {
		Integer id = getAsDbRow().getInteger(identifier);
		String deleteStatement = new StringBuilder("DELETE FROM ")
			.append(sourceTable)
			.append(" WHERE ")
			.append(identifier)
			.append(" = ")
			.append(id).toString();
		LOG.info(deleteStatement);
		jdbcTemplate.update(deleteStatement);
	}
}
