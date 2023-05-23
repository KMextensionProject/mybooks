package com.mkrajcovic.mybooks.persistence;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_book", schema = "library")
//@AttributeOverrides({
//	@AttributeOverride(name = "id", column = @Column(name = "n_book_id")),
//	@AttributeOverride(name = "title", column = @Column(name = "s_title")),
//})
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_book_id")
	private Integer id;

	@Column(name = "s_isbn")
	private String isbn;

	@Column(name = "s_title")
	private String title;

	@Column(name = "n_pages")
	private Integer pages;

	// these enums are one to one - inner join
	private Integer bindingType; // ID or the whole object?

	private Integer formatId; // ID or the whole object?

	@Column(name = "s_publisher")
	private String publisher;

	private Integer languageId; // ID or the whole object?

	@Column(name = "d_from")
	private LocalDateTime from;

	@Column(name = "d_to")
	private LocalDateTime to;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getBindingType() {
		return bindingType;
	}

	public void setBindingType(Integer bindingType) {
		this.bindingType = bindingType;
	}

	public Integer getFormatId() {
		return formatId;
	}

	public void setFormatId(Integer formatId) {
		this.formatId = formatId;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	public Integer getId() {
		return id;
	}

}
