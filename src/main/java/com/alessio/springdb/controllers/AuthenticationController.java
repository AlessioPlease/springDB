package com.alessio.springdb.controllers;

import com.alessio.springdb.JwtUtil;
import com.alessio.springdb.models.authentication.AuthenticationRequest;
import com.alessio.springdb.models.authentication.AuthenticationResponse;
import com.alessio.springdb.models.user.User;
import com.alessio.springdb.repositories.UserRepository;
import com.alessio.springdb.services.MyUserDetailsService;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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


	@PostMapping("/user/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

			final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AuthenticationResponse("Authentication successful", jwt));
		} catch (Exception e) {
			return new ResponseEntity<>(new AuthenticationResponse("Authentication failed: incorrect username or password", ""), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/user/register")
	public ResponseEntity<?> registerUser(@RequestBody AuthenticationRequest authenticationRequest) {

		if (userRepository.countByUsernameEquals(authenticationRequest.getUsername()) == 0) {

			String password = new BCryptPasswordEncoder().encode(authenticationRequest.getPassword());
			userRepository.save(new User(authenticationRequest.getUsername(), password));

			return ResponseEntity.ok(new AuthenticationResponse("Registration successful",
					new JSONObject(createAuthenticationToken(authenticationRequest)).getJSONObject("body").get("jwt").toString()));
		} else {
			return new ResponseEntity<>(new AuthenticationResponse("Registration failed: username already taken", ""), HttpStatus.CONFLICT);
		}
	}
}

