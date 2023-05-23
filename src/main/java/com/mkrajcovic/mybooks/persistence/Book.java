package com.mkrajcovic.mybooks.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(name = "n_book_id")
	private Integer id;

	@Column(name = "s_title")
	private String title;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
}
