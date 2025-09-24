package com.example.demo.services;

import com.example.demo.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

	User save(User user);

	List<User> findAll();

	Optional<User> findById(Integer id);

	void deleteById(Integer id);
}