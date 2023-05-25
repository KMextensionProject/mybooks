package com.mkrajcovic.mybooks.persistence.enums;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "library_enum", name = "e_language")
public class Language {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_language_id")
	private Integer id;

	@Column(name = "s_code")
	private String code;

	@Column(name = "s_name")
	private String name;

	@Column(name = "d_from")
	private LocalDateTime from;

	@Column(name = "d_to")
	private LocalDateTime to;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Language [code=" + code + ", name=" + name + "]";
	}

}
