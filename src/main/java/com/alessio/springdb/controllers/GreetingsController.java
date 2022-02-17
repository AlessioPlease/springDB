package com.alessio.springdb.controllers;

import com.alessio.springdb.models.greeting.*;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingsController {

	private static final String base = "Hello, %s!";
	private final AtomicLong c = new AtomicLong();

	@GetMapping("/test")
	public Greeting<?> test(@RequestParam(value = "name", defaultValue = "you") String name) {
		return new Greeting<>(new GreetingTest(c.incrementAndGet(), String.format(base, name)));
	}

	@GetMapping("/greeting")
	public Greeting<?> greeting(@RequestParam(value = "name", defaultValue = "name") String name,
								@RequestParam(value = "surname", defaultValue = "surname") String surname) {
		return new Greeting<>(new GreetingExercise(name, surname));
	}

}
