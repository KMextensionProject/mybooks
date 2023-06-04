package com.mkrajcovic.mybooks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import static com.mkrajcovic.mybooks.enums.ContentType.APPLICATION_JSON;

import com.mkrajcovic.mybooks.db.TypeMap;
import com.mkrajcovic.mybooks.service.EnumService;

@Controller
@RequestMapping("/book/enum")
public class EnumController {

	@Autowired
	private EnumService enumService;

	@GetMapping(path = "/bindingType/", produces = APPLICATION_JSON)
	@ResponseBody
	public List<TypeMap> getBindingTypes() {
		return enumService.getBindingTypes();
	}

	@GetMapping(path = "/format/", produces = APPLICATION_JSON)
	@ResponseBody
	public List<TypeMap> getFormats() {
		return enumService.getFormats();
	}

	@GetMapping(path = "/language/", produces = APPLICATION_JSON)
	@ResponseBody
	public List<TypeMap> getLanguages() {
		return enumService.getLanguages();
	}

	@PostMapping(path = "/dropCache")
	@ResponseStatus(HttpStatus.OK)
	public void dropEnumCache() {
		enumService.dropCache();
	}
}
