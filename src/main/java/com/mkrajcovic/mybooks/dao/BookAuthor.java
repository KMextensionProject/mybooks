package com.mkrajcovic.mybooks.dao;

import com.mkrajcovic.mybooks.db.DirectlyUpdatableDatabaseObject;
import com.mkrajcovic.mybooks.db.TypeMap;

public class BookAuthor extends DirectlyUpdatableDatabaseObject<BookAuthor> {

	private Integer bookId;
	private Integer authorId;
	private Boolean isLeadAuthor;

	@Override
	protected void initTableMetadata() {
		identifier = "n_book_id";
		sourceTable = "library.t_book_author";
//		sourceView = "library.v_book_author";
	}

	@Override
	public BookAuthor setByData(TypeMap data) {
		if (data == null || data.isEmpty()) {
			return this;
		}
		TypeMap map = toTypeMap();
		map.putAll(data);

		bookId = map.getInteger("book_id");
		authorId = map.getInteger("author_id");
		isLeadAuthor = map.getBoolean("lead_author");

		return this;
	}

	@Override
	public TypeMap toTypeMap() {
		return new TypeMap(
			"book_id", this.bookId,
			"author_id", this.authorId,
			"lead_author", this.isLeadAuthor);
	}

	/**
	 *
	 */
	@Override
	public void update() {
		LOG.warning(() -> "updating cross table has no effect, you must "
			+ "recreate this relationship by deleting it first");
		// throw error (bad request) with above message
	}

	/**
	 *
	 */
	@Override
	public void delete() {
		this.hardDelete();
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public void setLeadAuthor(Boolean isLeadAuthor) {
		this.isLeadAuthor = isLeadAuthor;
	}

	public Boolean isLeadAuthor() {
		return this.isLeadAuthor;
	}
}
