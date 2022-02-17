package com.alessio.springdb.controllers;

import com.alessio.springdb.models.auto.Auto;
import com.alessio.springdb.repositories.AutoRepository;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

@RestController
public class AutoController {

	private final AutoRepository autoRepository;
	public AutoController(AutoRepository autoRepository) {
		this.autoRepository = autoRepository;
	}


	@PostMapping("/addAuto")
	public String addAuto(@RequestBody Auto auto) {
		if (autoRepository.existsById(auto.getId())) {
			return "Insertion failed: id " + auto.getId() + " already present";
		} else {
			autoRepository.save(auto);
			return "Insertion successful";
		}
	}

	@DeleteMapping("/deleteAuto/{id}")
	public String deleteAuto(@PathVariable String id) {

		if (autoRepository.existsById(Integer.parseInt(id))) {
			autoRepository.delete(new Auto(Integer.parseInt(id)));
			return "Deletion successful";
		} else {
			return "Deletion failed: could not find id " + id;
		}
	}

	@GetMapping("/getAllAuto")
	public ArrayList<Auto> getAllAuto() {
		return ((ArrayList<Auto>) autoRepository.findAll());
	}
}

