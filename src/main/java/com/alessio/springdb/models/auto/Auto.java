package com.alessio.springdb.models.auto;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Auto {

	@Id
	private int id;

	public Auto() {}

	@JsonCreator
	public Auto(int id) {
		this.id = id;
	}


	public int getId() {
		return id;
	}
}
