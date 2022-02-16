package com.alessio.springdb.daos.greeting;

@SuppressWarnings("unused")
public class GreetingExercise {

	private final String name;
	private final String surname;

	public GreetingExercise(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getGreeting() {
		return String.format("Ciao %s %s!", name, surname);
	}
}
