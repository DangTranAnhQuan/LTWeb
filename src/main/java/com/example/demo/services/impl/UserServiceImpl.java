package com.example.demo.services.impl;

import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		if (!userRepository.existsById(id)) {
			throw new IllegalArgumentException("User with ID " + id + " not found.");
		}
		userRepository.deleteById(id);
	}
}