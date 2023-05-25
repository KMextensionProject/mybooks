package com.mkrajcovic.mybooks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkrajcovic.mybooks.persistence.Book;
import com.mkrajcovic.mybooks.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(path = "/ping", produces = "application/json")
	@ResponseBody
	public Map<String, String> ping() {
		return bookService.ping();
	}

	@GetMapping(path = "/home")
	@PreAuthorize("hasRole('USER')")
	public String home() {
		return bookService.getHomePage();
	}

	@GetMapping(path = "/grid")
	@PreAuthorize("hasRole('USER')")
	public String grid() {
		return "bookGrid";
	}

	@GetMapping(path = "/books/", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public List<Book> listBooks() {
		return bookService.getBooks();
	}

	@GetMapping(path = "/books/{id}", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public Book getBook(@PathVariable("id") Integer id) {
		return bookService.getBook(id);
	}
}
