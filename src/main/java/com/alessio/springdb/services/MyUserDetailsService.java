package com.alessio.springdb.services;

import com.alessio.springdb.repositories.UserRepository;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.alessio.springdb.models.user.User user = userRepository.findByUsernameEquals(username);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
}
