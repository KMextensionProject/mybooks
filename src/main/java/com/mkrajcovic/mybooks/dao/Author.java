package com.mkrajcovic.mybooks.dao;

import java.util.List;

public class Author {

	private Integer id;

	private String name;

	private List<Book> books;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}
}
