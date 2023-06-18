package com.mkrajcovic.mybooks.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.net.URI;
import java.util.List;

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
import com.mkrajcovic.mybooks.service.AuthorService;

@Controller
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping(path = "/author")
	@PreAuthorize("hasRole('USER')")
	public String getAuthorGrid() {
		return "authorGrid";
	}

	@PostMapping(path = "/author", consumes = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Object> createAuthor(@RequestBody TypeMap authorMap) {
		Integer id = authorService.createAuthor(authorMap);
		// HttpHeaders setLocation()
		return ResponseEntity.created(URI.create("/author/" + id)).build();
	}

	@GetMapping(path = "/author/", produces = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public List<TypeMap> listAuthors() {
		return authorService.listAuthors();
	}

	@GetMapping(path = "/author/{id}", produces = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	@ResponseBody
	public TypeMap getAuthor(@PathVariable Integer id) {
		return authorService.getAuthor(id);
	}

	@PostMapping(path = "/author/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN')")
	public TypeMap updateAuthor(@PathVariable Integer id, @RequestBody TypeMap data) {
		return authorService.updateAuthor(id, data);
	}

	@DeleteMapping(path = "/author/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAuthor(@PathVariable Integer id) {
		authorService.deleteAuthor(id);
	}
}
