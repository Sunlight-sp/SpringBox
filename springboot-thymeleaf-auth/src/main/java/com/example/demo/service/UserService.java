package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	void saveUser(User user);
	User getUserByUsername(String username);
	User getUserByEmail(String email);
	List<User> getAllUsers();
}
