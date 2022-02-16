package com.alessio.springdb.daos.greeting;

@SuppressWarnings("unused")
public class GreetingTest {

	private final long id;
	private final String text;

	public GreetingTest(long id, String text) {
		this.id = id;
		this.text = text;
	}

	public long getId() {
		return id;
	}

	public String getText() {
		return text;
	}
}
