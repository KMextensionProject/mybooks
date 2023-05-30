package com.mkrajcovic.mybooks.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mkrajcovic.mybooks.db.TypeMap;
import com.mkrajcovic.mybooks.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping(path = "/author", consumes = "application/json")
	public ResponseEntity<Object> createAuthor(@RequestBody TypeMap authorMap) {
		Integer id = authorService.createAuthor(authorMap);
		// HttpHeaders setLocation()
		return ResponseEntity.created(URI.create("/author/" + id)).build();
	}
	
	@GetMapping(path = "/author/", produces = "application/json")
	@ResponseBody
	public List<TypeMap> listAuthors() {
		return authorService.listAuthors();
	}
}
