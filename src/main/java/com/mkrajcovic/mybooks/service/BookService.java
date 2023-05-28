package com.mkrajcovic.mybooks.service;

import static java.util.Collections.singletonMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkrajcovic.mybooks.dao.Book;
import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.TypeMap;

@Service
public class BookService {

	@Autowired
	private Database db;

	public Map<String, String> ping() {
		return singletonMap("Status", "OK");
	}

	public String getHomePage() {
		return "home";
	}

	public List<TypeMap> getBooks() { // add paging through http request headers
		return db.select("A.*", 
				"B.s_name AS s_binding_type_label",
				"C.s_dimensions AS s_format_label",
				"D.s_code AS s_language_code",
				"E.s_author_name")
			.from("library.t_book A")
			.join("library_enum.e_binding_type B").on("A.n_binding_type_id", "B.n_binding_type_id")
			.join("library_enum.e_format C").on("A.n_format_id", "C.n_format_id")
			.join("library_enum.e_language D").on("A.n_language_id", "D.n_language_id")
			// join and select separately
			.join("library.t_book_author BA").on("A.n_book_id", "BA.n_book_id")
			.join("library.t_author E").on("BA.n_author_id", "E.n_author_id")
			.where("A.d_to", "infinity")
			.asList();
	}

	public TypeMap getBook(Integer id) {
		return db.select()
			.from("library.t_book")
			.where("n_book_id", id)
			.asMap();
	}

	@Transactional
	public Integer createBook(TypeMap book) {
		Book newBook = new Book();
		newBook.setByData(book);
		newBook.insert();

		// insert book id and author id into cross table
		// there will be 3 inserts totaly

		// author will already exist..if not, throw error
		// author will be created beforehand from within its own form

		return newBook.getBookId();
	}

	@Transactional
	public void deleteBook(Integer id) {
		Book book = new Book();
		book.setBookId(id);
		book.delete();

		// delete its relationship in cross table with hard delete
	}

	@Transactional
	public void updateBook(Integer id, TypeMap book) {
		new Book().selectByIdWithCheckExistence(id)
			.setByData(book)
			.update();

		// could it be meaningful to update book / author relationship?
	}
}
