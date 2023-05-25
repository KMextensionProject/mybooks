package com.mkrajcovic.mybooks.persistence.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "library_enum", name = "e_format")
public class Format {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_format_id")
	private Integer id;

	@Column(name = "s_code")
	private String code;

	@Column(name = "s_dimensions")
	private String dimensions;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Format [code=" + code + ", dimensions=" + dimensions + "]";
	}

}
