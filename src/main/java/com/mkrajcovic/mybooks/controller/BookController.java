package com.mkrajcovic.mybooks.controller;

import static com.mkrajcovic.mybooks.enums.ContentType.APPLICATION_JSON;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mkrajcovic.mybooks.db.TypeMap;
import com.mkrajcovic.mybooks.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;

	@GetMapping(path = "/ping", produces = APPLICATION_JSON)
	@ResponseBody
	public Map<String, String> ping() {
		return bookService.ping();
	}

	// TODO: make grid the home page
	@GetMapping(path = "/home")
	@PreAuthorize("hasRole('USER')")
	public String home() {
		return bookService.getHomePage();
	}

	// TODO: make grid the home page
	// TODO: display currently signed user
	@GetMapping(path = "/grid")
	@PreAuthorize("hasRole('USER')")
	public String grid() {
		return "bookGrid";
	}

	@PostMapping(path = "/book", consumes = APPLICATION_JSON)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> createBook(@RequestBody TypeMap bookData) {
		Integer id = bookService.createBook(bookData);
		return ResponseEntity.created(URI.create("/book/" + id)).build();
	}

	@GetMapping(path = "/book/", produces = APPLICATION_JSON)
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public List<TypeMap> listBooks() {
		return bookService.getBooks();
	}

	@GetMapping(path = "/book/{id}", produces = APPLICATION_JSON)
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public TypeMap getBook(@PathVariable("id") Integer id) {
		return bookService.getBook(id);
	}

	@PostMapping(path = "/book/{id}", consumes = APPLICATION_JSON)
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> updateBook(@PathVariable Integer id, @RequestBody TypeMap book) {
		bookService.updateBook(id, book);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(path = "/book/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Integer id) {
		bookService.deleteBook(id);
	}
}
