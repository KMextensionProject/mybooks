package com.mkrajcovic.mybooks.dao;

import java.time.LocalDateTime;

import com.mkrajcovic.mybooks.db.DatabaseObject;
import com.mkrajcovic.mybooks.db.TypeMap;

public class Book extends DatabaseObject<Book> {

	private Integer id;
	private String isbn;
	private String title;
	private Integer pages;
	private String publisher;
	private Integer orderInSeries;
	private LocalDateTime from;
	private LocalDateTime to;

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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getOrderInSeries() {
		return this.orderInSeries;
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

	public Integer getId() {
		return id;
	}

	@Override
	protected Book setByData(TypeMap data) {
		
		return null;
	}

	@Override
	protected TypeMap toTypeMap() {
		
		return null;
	}

}
