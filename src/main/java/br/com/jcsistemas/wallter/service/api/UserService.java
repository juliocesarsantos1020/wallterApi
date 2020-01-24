package br.com.jcsistemas.wallter.service.api;

import br.com.jcsistemas.wallter.entity.User;

import java.util.Optional;

public interface UserService {

	User save(User user);
	Optional<User> findByEmail(String email);
}
