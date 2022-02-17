package com.alessio.springdb.models.authentication;

public class AuthenticationResponse {

	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

	@Override
	public String toString() {
		return jwt;
	}
}
