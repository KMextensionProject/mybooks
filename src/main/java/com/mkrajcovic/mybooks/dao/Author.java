package com.mkrajcovic.mybooks.dao;

import com.mkrajcovic.mybooks.db.DirectlyUpdatableDatabaseObject;
import com.mkrajcovic.mybooks.db.TypeMap;

public class Author extends DirectlyUpdatableDatabaseObject<Author> {

	private Integer authorId;
	private String authorName;

	@Override
	protected void initTableMetadata() {
		sourceTable = "library.t_author";
		sourceView = "library.v_author";
		identifier = "n_author_id";
	}

	public String getName() {
		return authorName;
	}

	public void setName(String name) {
		this.authorName = name;
	}

	public void setAuthorId(Integer id) {
		this.authorId = id;
	}
	
	public Integer getAuthorId() {
		return authorId;
	}

	@Override
	public Author setByData(TypeMap data) {
		if (data == null || data.isEmpty()) {
			return this;
		}
		TypeMap map = toTypeMap();
		map.putAll(data);

		authorId = map.getInteger("author_id");
		authorName = map.getString("author_name");

		return this;
	}

	@Override
	public TypeMap toTypeMap() {
		return new TypeMap(
			"book_id", authorId,
			"isbn", authorName);
	}

	@Override
	public String toString() {
		return "Author [id=" + authorId + ", name=" + authorName + "]";
	}
}
