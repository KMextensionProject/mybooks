package com.mkrajcovic.mybooks.dao;

import com.mkrajcovic.mybooks.db.DatabaseObject;
import com.mkrajcovic.mybooks.db.TypeMap;

public class BookAuthor extends DatabaseObject<BookAuthor> {

	private Integer bookId;
	private Integer authorId;

	@Override
	protected void initTableMetadata() {
		identifier = ""; // toto moze poukazovat na knihu a zmaze vsetky zaznamy s knihami v kross tabulke
		sourceTable = "library.t_book_author";
//		sourceView = "library.v_book_author";
	}

	@Override
	protected BookAuthor setByData(TypeMap data) {
		return null;
	}

	@Override
	protected TypeMap toTypeMap() {
		return null;
	}

	/**
	 * 
	 */
	@Override
	protected void update() {
		// do nothing
		
	}

	/**
	 * 
	 */
	@Override
	protected void delete() {
		// by book id when the book is deleted
		// if we were deleting books by hard delete, then this would be cascaded to bookauthor table by database.
	}

}
