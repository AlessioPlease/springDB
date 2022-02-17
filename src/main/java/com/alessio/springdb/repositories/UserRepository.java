package com.alessio.springdb.repositories;

import com.alessio.springdb.models.user.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository  extends CrudRepository<User, Integer> {
	@NonNull
	User findByUsernameEquals(@NonNull String username);

	long countByUsernameEquals(@NonNull String username);
}
