package com.alessio.springdb.controllers;

import com.alessio.springdb.daos.auto.Auto;
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
	public String addAuto(@RequestBody Auto requestAuto) {
		if (autoRepository.existsById(requestAuto.getId())) {
			return "Failed: id already present";
		} else {
			autoRepository.save(requestAuto);
			return "Success";
		}
	}

	@DeleteMapping("/deleteAuto/{id}")
	public String deleteAuto(@PathVariable String id) {

		if (autoRepository.existsById(Integer.parseInt(id))) {
			autoRepository.delete(new Auto(Integer.parseInt(id)));
			return "Success";
		} else {
			return "Failed: could not find id";
		}
	}

	@GetMapping("/getAllAuto")
	public ArrayList<Auto> getAllAuto() {
		return ((ArrayList<Auto>) autoRepository.findAll());
	}
}















