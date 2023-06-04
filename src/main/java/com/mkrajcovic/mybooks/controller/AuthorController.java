package com.mkrajcovic.mybooks.controller;

import static com.mkrajcovic.mybooks.enums.ContentType.APPLICATION_JSON;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mkrajcovic.mybooks.db.TypeMap;
import com.mkrajcovic.mybooks.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@PostMapping(path = "/author", consumes = APPLICATION_JSON)
	public ResponseEntity<Object> createAuthor(@RequestBody TypeMap authorMap) {
		Integer id = authorService.createAuthor(authorMap);
		// HttpHeaders setLocation()
		return ResponseEntity.created(URI.create("/author/" + id)).build();
	}

	@GetMapping(path = "/author/", produces = APPLICATION_JSON)
	@ResponseBody
	public List<TypeMap> listAuthors() {
		return authorService.listAuthors();
	}

	@GetMapping(path = "/author/{id}", produces = APPLICATION_JSON)
	@ResponseBody
	public TypeMap getAuthor(@PathVariable Integer id) {
		return authorService.getAuthor(id);
	}

	@DeleteMapping(path = "/author/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAuthor(@PathVariable Integer id) {
		authorService.deleteAuthor(id);
	}
}
