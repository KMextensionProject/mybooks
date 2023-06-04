package com.mkrajcovic.mybooks.service;

import static com.mkrajcovic.mybooks.enums.CacheNames.BINDING;
import static com.mkrajcovic.mybooks.enums.CacheNames.FORMAT;
import static com.mkrajcovic.mybooks.enums.CacheNames.LANGUAGE;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.TypeMap;

@Service
public class EnumService {

	private static final Logger LOG = Logger.getAnonymousLogger();

	@Autowired
	private Database db;

	// TODO: use views where data are restricted by d_to

	@Cacheable(cacheNames = BINDING, sync = true)
	public List<TypeMap> getBindingTypes() {
		return db.select(
				"n_binding_type_id", 
				"s_name")
			.from("library_enum.e_binding_type")
			.asList();
	}

	@Cacheable(cacheNames = FORMAT, sync = true)
	public List<TypeMap> getFormats() {
		return db.select(
				"n_format_id",
				"s_code",
				"s_dimensions")
			.from("library_enum.e_format")
			.asList();
	}

	@Cacheable(cacheNames = LANGUAGE, sync = true)
	public List<TypeMap> getLanguages() {
		return db.select(
				"n_language_id",
				"s_code",
				"s_name")
			.from("library_enum.e_language")
			.asList();
	}

	@CacheEvict(cacheNames = {BINDING, FORMAT, LANGUAGE}, beforeInvocation = true)
	public void dropCache() {
		LOG.info(() -> "the whole enum cache has been dropped");
	}
}
