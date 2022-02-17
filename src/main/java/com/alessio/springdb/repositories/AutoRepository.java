package com.alessio.springdb.repositories;

import com.alessio.springdb.models.auto.Auto;

import org.springframework.data.repository.CrudRepository;

public interface AutoRepository extends CrudRepository<Auto, Integer> {
	
}
