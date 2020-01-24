package br.com.jcsistemas.wallter.service.impl;

import br.com.jcsistemas.wallter.entity.User;
import br.com.jcsistemas.wallter.repository.UserRepository;
import br.com.jcsistemas.wallter.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
