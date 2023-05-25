package com.mkrajcovic.mybooks.persistence.enums;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "library_enum", name = "e_binding_type")
public class BindingType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "n_binding_type_id")
	private Integer id;

	@Column(name = "s_name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "BindingType [name=" + name + "]";
	}
}
