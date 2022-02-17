package com.alessio.springdb.controllers;

import com.alessio.springdb.JwtUtil;
import com.alessio.springdb.models.authentication.AuthenticationRequest;
import com.alessio.springdb.models.authentication.AuthenticationResponse;
import com.alessio.springdb.models.user.User;
import com.alessio.springdb.repositories.UserRepository;
import com.alessio.springdb.services.MyUserDetailsService;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;

	private final MyUserDetailsService myUserDetailsService;

	private final UserRepository userRepository;

	private final JwtUtil jwtTokenUtil;

	public AuthenticationController(AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtUtil jwtTokenUtil, UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.myUserDetailsService = myUserDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.userRepository = userRepository;
	}


	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		if (userRepository.countByUsernameEquals(authenticationRequest.getUsername()) == 0) {

			String password = new BCryptPasswordEncoder().encode(authenticationRequest.getPassword());
			userRepository.save(new User(authenticationRequest.getUsername(), password));

			return String.format("Registration successful.\nJWT= %s", new JSONObject(createAuthenticationToken(authenticationRequest)).getJSONObject("body").get("jwt").toString());
		} else {
			return "Registration failed: username already taken";
		}
	}
}

