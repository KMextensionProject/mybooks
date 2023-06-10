package com.mkrajcovic.mybooks.db;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Updates and soft deletes are performed on the single database table directly.
 * Delete performs invalidation of records by updating rowValidityEndDateColumn 
 * property. Update causes executing direct update statement on the database row.
 * This class also provides a method to perform a hard delete on the record.
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
		data.put(rowValidityStartDateColumn, Timestamp.valueOf(LocalDateTime.now()));
		data.remove(rowValidityEndDateColumn); // not needed, stays infinity
		String updateStatement = buildUpdateStatement(data);
		Collection<Object> values = data.values();
		LOG.info("execute: " + updateStatement + " with values " + values);
		jdbcTemplate.update(updateStatement, values.toArray());
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
			rowValidityEndDateColumn, Timestamp.valueOf(LocalDateTime.now()));
		String invalidationStatement = buildUpdateStatement(data);
		Collection<Object> values = data.values();
		LOG.info("execute: " + invalidationStatement + " with values " + values);
		jdbcTemplate.update(invalidationStatement, values.toArray());
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
			.append(" = ?").toString();
		LOG.info("execute: " + deleteStatement + " with values " + id);
		jdbcTemplate.update(deleteStatement, id);
	}
}
