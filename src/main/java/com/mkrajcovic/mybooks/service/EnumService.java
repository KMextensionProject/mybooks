package com.mkrajcovic.mybooks.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.TypeMap;
import static com.mkrajcovic.mybooks.utils.CacheNames.BINDING;
import static com.mkrajcovic.mybooks.utils.CacheNames.FORMAT;
import static com.mkrajcovic.mybooks.utils.CacheNames.LANGUAGE;

@Service
public class EnumService {

	private static final Logger LOG = Logger.getAnonymousLogger();

	@Autowired
	private Database database;

	@Cacheable(cacheNames = BINDING, sync = true)
	public List<TypeMap> getBindingTypes() {
		return database.select(
				"n_binding_type_id", 
				"s_name")
			.from("library_enum.e_binding_type")
			.where("d_to", "infinity") // use views because of this
			.asList();
	}

	@Cacheable(cacheNames = FORMAT, sync = true)
	public List<TypeMap> getFormats() {
		return database.select(
				"n_format_id",
				"s_code",
				"s_dimensions")
			.from("library_enum.e_format")
			.where("d_to", "infinity")
			.asList();
	}

	@Cacheable(cacheNames = LANGUAGE, sync = true)
	public List<TypeMap> getLanguages() {
		return database.select(
				"n_language_id",
				"s_code",
				"s_name")
			.from("library_enum.e_language")
			.where("d_to", "infinity")
			.asList();
	}

	@CacheEvict(cacheNames = {BINDING, FORMAT, LANGUAGE}, beforeInvocation = true)
	public void dropCache() {
		LOG.info(() -> "the whole enum cache has been dropped");
	}
}
