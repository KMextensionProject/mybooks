package com.mkrajcovic.mybooks.db;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Updates and soft deletes are performed on the single database table directly.
 * Delete performs invalidation of records by updating the d_to property.
 * Update causes executing direct update statement on the row.
 *
 * @author martin
 */
public abstract class DirectlyUpdatableDatabaseObject<T> extends DatabaseObject<T> {

	/**
	 * Performs update operation on underlying record with no duplication to
	 * maintain history records.
	 */
	@Override
	public void update() {
		TypeMap data = getAsDbRow();
		data.put("d_from", Timestamp.valueOf(LocalDateTime.now()).toString()); // toto je na kokot
		String updateStatement = buildUpdateStatement(data);
		LOG.info(updateStatement);
		jdbcTemplate.update(updateStatement);
	}

	// TODO: make d_to generic / user defined field
	/**
	 * Directly updates the database source table by setting the d_to system field
	 * to current timestamp, which invalidates the record from the moment of this
	 * method call.
	 */
	@Override
	public void delete() {
		TypeMap data = new TypeMap(
			identifier, getAsDbRow().getInteger(identifier),
			"d_to", Timestamp.valueOf(LocalDateTime.now()).toString());
		String invalidationStatement = buildUpdateStatement(data);
		LOG.info(invalidationStatement);
		jdbcTemplate.update(invalidationStatement);
	}
}
