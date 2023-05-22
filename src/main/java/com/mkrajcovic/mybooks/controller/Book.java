package com.mkrajcovic.mybooks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkrajcovic.mybooks.db.TypeMap;
import com.mkrajcovic.mybooks.service.BookService;

@Controller
public class Book {

	@Autowired
	private BookService bookService;

	@GetMapping(path = "/ping", produces = "application/json")
	@ResponseBody
	public Map<String, String> ping() {
		return bookService.ping();
	}

	@GetMapping(path = "/home")
	@PreAuthorize("hasRole('USER')") // inject principal and check its authorities..
	public String home() {
		return bookService.getHomePage();
	}

	@GetMapping(path = "/books/", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
//	public List<TypeMap> listBooks(@RequestParam(required = false) Map<String, Object> queryParams) { // TODO: use db queryParams
	public List<TypeMap> listBooks() {
		return bookService.getBooks();
	}
}
