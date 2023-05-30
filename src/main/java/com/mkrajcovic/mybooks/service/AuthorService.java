package com.mkrajcovic.mybooks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mkrajcovic.mybooks.dao.Author;
import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.TypeMap;

@Service
public class AuthorService {

	@Autowired
	private Database database;

	// incorporate searching
	public List<TypeMap> listAuthors() {
		return database.select()
			.from("library.t_author")
			.asList();
	}

	public Integer createAuthor(TypeMap authorMap) {
		Author author = new Author();
		author.setByData(authorMap);
		author.insert();

		return author.getAuthorId();
	}

}
