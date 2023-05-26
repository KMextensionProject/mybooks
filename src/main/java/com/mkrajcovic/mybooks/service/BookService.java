package com.mkrajcovic.mybooks.service;

import static java.util.Collections.singletonMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.TypeMap;

@Service
public class BookService {

	@Autowired
	private Database db;

	public Map<String, String> ping() {
		return singletonMap("Status", "OK");
	}

	public String getHomePage() {
		return "home";
	}

	public List<TypeMap> getBooks() { // add paging through http request headers
		return db.select()
			.from("library.t_book")
			.asList();
	}

//	@Transactional
	public TypeMap getBook(Integer id) {
		return db.select()
			.from("library.t_book")
			.where("n_book_id", id)
			.asMap();
	}

	@Transactional
	public TypeMap insertAndGetBook() {
		return null;
	}
}
