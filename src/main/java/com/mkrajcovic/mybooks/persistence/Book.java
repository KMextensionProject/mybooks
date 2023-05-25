package com.mkrajcovic.mybooks.persistence;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mkrajcovic.mybooks.persistence.enums.Language;

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
	@Column(name = "n_binding_type_id")
	private Integer bindingType; // ID or the whole object?

	@Column(name = "n_format_id")
	private Integer formatId; // ID or the whole object?

	@Column(name = "s_publisher")
	private String publisher;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "n_language_id")
	private Language language; // ID or the whole object?

	@Column(name = "n_series_number")
	private Integer orderInSeries;

	@ManyToMany(fetch = FetchType.EAGER) // I do not want this, but this is the way it works at least
	@JoinTable(schema = "library", name = "t_book_author", 
		joinColumns = @JoinColumn(name = "n_book_id"), 
		inverseJoinColumns = @JoinColumn(name = "n_author_id")
	)
	private List<Author> authors;

	@Column(name = "d_from", insertable = false)
	private LocalDateTime from;

	@Column(name = "d_to", insertable = false)
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

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setLanguageId(Language language) {
		this.language = language;
	}

	public int getOrderInSeries() {
		return this.orderInSeries;
	}

	public List<Author> getAuthors() {
		return this.authors;
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
