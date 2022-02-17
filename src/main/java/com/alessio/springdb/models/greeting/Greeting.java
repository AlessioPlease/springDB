package com.alessio.springdb.models.greeting;

@SuppressWarnings("unused")
public class Greeting<T> {

	private final T content;

	public Greeting(T t) {
		content = t;
	}

	public T getContent() {
		return content;
	}
}
