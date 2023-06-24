package com.mkrajcovic.mybooks.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mkrajcovic.mybooks.db.TypeMap;
import com.mkrajcovic.mybooks.service.EnumService;

@RestController
@RequestMapping("/book/enum")
public class EnumController {

	@Autowired
	private EnumService enumService;

	@GetMapping(path = "/bindingType/", produces = APPLICATION_JSON_VALUE)
	public List<TypeMap> getBindingTypes() {
		return enumService.getBindingTypes();
	}

	@GetMapping(path = "/format/", produces = APPLICATION_JSON_VALUE)
	public List<TypeMap> getFormats() {
		return enumService.getFormats();
	}

	@GetMapping(path = "/language/", produces = APPLICATION_JSON_VALUE)
	public List<TypeMap> getLanguages() {
		return enumService.getLanguages();
	}

	@PostMapping(path = "/dropCache")
	@ResponseStatus(HttpStatus.OK)
	public void dropEnumCache() {
		enumService.dropCache();
	}
}
