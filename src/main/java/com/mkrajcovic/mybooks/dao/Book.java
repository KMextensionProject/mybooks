package com.mkrajcovic.mybooks.dao;

import java.time.LocalDateTime;

import com.mkrajcovic.mybooks.db.DirectlyUpdatableDatabaseObject;
import com.mkrajcovic.mybooks.db.TypeMap;

// pridat ku knihe obrazok?
// pridat ku knihe obsah? / citatelsky dennik?
public class Book extends DirectlyUpdatableDatabaseObject<Book> {

	private Integer bookId;
	private String isbn;
	private String title;
	private Integer pages;
	private Integer bindingTypeId;
	private Integer formatId;
	private String publisher;
	private Integer languageId;
	private Integer seriesNumber;
	private LocalDateTime from;
	private LocalDateTime to;

	@Override
	protected void initTableMetadata() {
		this.sourceTable = "library.t_book";
//		this.sourceView = "library.v_book";
		this.identifier = "n_book_id";
	}

	/**
	 * Performs mutable operation on current object.<br>
	 * Rewrites internal fields that are contained in the map.
	 */
	@Override
	public Book setByData(TypeMap data) {
		if (data == null || data.isEmpty()) {
			return this;
		}
		TypeMap map = toTypeMap();
		map.putAll(data);

		bookId = map.getInteger("book_id");
		isbn = map.getString("isbn");
		title = map.getString("title");
		pages = map.getInteger("pages");
		bindingTypeId = map.getInteger("binding_type_id");
		formatId = map.getInteger("format_id");
		publisher = map.getString("publisher");
		languageId = map.getInteger("language_id");
		seriesNumber = map.getInteger("series_number");
		from = map.getLocalDateTime("from");
		to = map.getLocalDateTime("to");

		return this;
	}

	@Override
	public TypeMap toTypeMap() {
		return new TypeMap(
			"book_id", bookId,
			"isbn", isbn,
			"title", title,
			"pages", pages,
			"binding_type_id", bindingTypeId,
			"format_id", formatId,
			"publisher", publisher,
			"language_id", languageId,
			"series_number", seriesNumber,
			"from", from,
			"to", to);
	}

	public void setBookId(Integer id) {
		this.bookId = id;
	}

	public Integer getBookId() {
		return bookId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getBindingTypeId() {
		return bindingTypeId;
	}

	public void setBindingTypeId(Integer bindingTypeId) {
		this.bindingTypeId = bindingTypeId;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Integer getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

}
