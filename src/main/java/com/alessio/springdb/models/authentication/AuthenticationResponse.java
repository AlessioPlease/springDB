package com.alessio.springdb.models.authentication;

@SuppressWarnings("unused")
public class AuthenticationResponse {

	private final String response;
	private final String jwt;

	public AuthenticationResponse(String response, String jwt) {
		this.response = response;
		this.jwt = jwt;
	}

	public String getResponse() {
		return response;
	}

	public String getJwt() {
		return jwt;
	}

	@Override
	public String toString() {
		return jwt;
	}
}
