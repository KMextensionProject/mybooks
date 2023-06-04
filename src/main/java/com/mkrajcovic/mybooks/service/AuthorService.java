package com.mkrajcovic.mybooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkrajcovic.mybooks.dao.Author;
import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.TypeMap;

@Service
public class AuthorService {

	@Autowired
	private Database db;

	@Transactional
	public Integer createAuthor(TypeMap authorMap) {
		Author author = new Author();
		author.setByData(authorMap);
		author.insert();

		return author.getAuthorId();
	}

	// incorporate searching
	public List<TypeMap> listAuthors() {
		return db.select()
			.from("library.t_author")
			.asList();
	}

	public TypeMap getAuthor(Integer id) {
		return db.select()
			.from("library.t_author")
			.where("n_author_id", id)
			.asMap();
	}

	@Transactional
	public void deleteAuthor(Integer id) {
		Author author = new Author();
		author.setAuthorId(id);
		author.delete();

		// delete relationship with book for that author id
		// this is done automatically by database, because of
		// ON DELETE CASCADE setting on the FK
	}
}
