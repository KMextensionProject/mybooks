package com.mkrajcovic.mybooks.service;

import static com.mkrajcovic.mybooks.db.Select.OrderByDirection.DESC;
import static java.util.Collections.singletonMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkrajcovic.mybooks.dao.Book;
import com.mkrajcovic.mybooks.dao.BookAuthor;
import com.mkrajcovic.mybooks.db.Database;
import com.mkrajcovic.mybooks.db.Paging;
import com.mkrajcovic.mybooks.db.QueryParams;
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

	public List<TypeMap> getBooks(QueryParams queryParams, Paging paging) {
		return db.select("A.*",
				"B.s_name AS s_binding_type_label",
				"C.s_dimensions AS s_format_label",
				"D.s_code AS s_language_code",
				"E.s_author_name")
			.from("library.t_book A")
			.join("library_enum.e_binding_type B").on("A.n_binding_type_id", "B.n_binding_type_id")
			.join("library_enum.e_format C").on("A.n_format_id", "C.n_format_id")
			.join("library_enum.e_language D").on("A.n_language_id", "D.n_language_id")

			// join and select these authors separately
			// when I figure out how to display that list
			// of authors as a dropdown list in one row
			.join("library.t_book_author BA").on("A.n_book_id", "BA.n_book_id")
			.join("library.t_author E").on("BA.n_author_id", "E.n_author_id")
			.where("BA.b_lead_author")
			.where("A.d_to", "infinity") // do this automatically by calling a view
			.where(queryParams)
			.range(paging) // null safe
			.asList();
	}

	public TypeMap getBook(Integer id) {
		TypeMap book = db.select()
			.from("library.t_book")
			.where("n_book_id", id)
			.asMap();

		List<TypeMap> authors = db.select("A.*", "BA.b_lead_author")
			.from("library.t_author A")
			.join("library.t_book_author BA")
			.on("A.n_author_id", "BA.n_author_id")
			.where("BA.n_book_id", id)
			.orderBy("BA.b_lead_author", DESC) // leading author first
			.asList();

		book.put("authors", authors);
		return book;
	}

	@Transactional
	public Integer createBook(TypeMap book) {
		Book newBook = new Book();
		newBook.setByData(book);
		newBook.insert();

		// v datach mi dojdu aj id autorov.. takze si ich poselektujem
		// ak nebudu existovat, tak ich nezakladam, ale hodim validacny error
		// TODO: vystavit input schemu

		// cross tabulka nebude mat tuto knihu este, takze netreba overovat...
		// staci insertnut do nej..

		// autor by mal byt vytvoreny na zvlast screene pred tym, nez sa bude dat
		// vybrat na knihu

		return newBook.getBookId();
	}

	@Transactional
	public void deleteBook(Integer id) {
		Book book = new Book();
		book.setBookId(id);
		book.delete();

		// soft delete is performed on Book, so we must explicitly
		// delete the relationship between book and its authors
		BookAuthor bookAuthor = new BookAuthor();
		bookAuthor.setBookId(id);
		bookAuthor.delete();
	}

	@Transactional
	public TypeMap updateBook(Integer id, TypeMap bookData) {
		Book book = new Book()
			.selectByIdWithCheckExistence(id)
			.setByData(bookData);

		book.update();

		return bookData;
		// could it be meaningful to update book / author relationship?
		// change author?
	}
}
