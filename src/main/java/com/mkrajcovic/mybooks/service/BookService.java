package com.mkrajcovic.mybooks.service;

import static java.util.Collections.singletonMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkrajcovic.mybooks.persistence.Book;
import com.mkrajcovic.mybooks.persistence.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Map<String, String> ping() {
		return singletonMap("Status", "OK");
	}

	public String getHomePage() {
		return "home";
	}

	public List<Book> getBooks() { // add paging through http request headers
		List<Book> books = bookRepository.findAll(PageRequest.of(0, 20)).getContent();
		books.forEach(b -> System.out.println(b.getAuthors()));
		return books;
	}

	@Transactional
	public Book getBook(Integer id) {
		return bookRepository.findById(id).orElse(null);
	}
}
