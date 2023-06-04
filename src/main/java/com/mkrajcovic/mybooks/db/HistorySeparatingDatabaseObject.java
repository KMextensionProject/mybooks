package com.mkrajcovic.mybooks.db;

// TODO: this will be used on Book model, since we want to maintain the history for these objects.. maybe
public abstract class HistorySeparatingDatabaseObject<T> extends DatabaseObject<T> {

	// if history table will be null, then throw error
	protected String historyTable;
//	protected String updateProcedure;
//	protected String deleteProcedure;

	// add select with history


	// add option to provide procedure that does this insert update on database side
	// if procedure not provided, default to insert update by hand
	@Override
	public void update() {
		// insert invalidated record into history table
		
		
		// na toto mozem pouzit stavajucu metodu v DirectoryUpdatableDatabaseObject
		// update existing record new record with the same ID into this source table having d_to still infinity
		// 
	}

	@Override
	public void delete() {
		// therefore no view table is required since all unactual data will reside in history table
		// update existing record to invalidate it
		// insert it to a history table..
		// then perform delete from on this source table.
	}
}
