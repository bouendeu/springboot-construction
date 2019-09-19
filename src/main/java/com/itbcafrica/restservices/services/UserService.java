package com.itbcafrica.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itbcafrica.restservices.entities.User;
import com.itbcafrica.restservices.repository.UserRepository;

@Service
public class UserService {
//Autowire the user repository

	@Autowired
	private UserRepository userRepository;

//	get allusers method

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//CreateUser Methode
	public User createUser(User user) {
		User userToDB = userRepository.save(user);
		return userToDB;
	}

//	getUserById
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

//UpdateUserById
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

//	deleteUserById

	public void deleteUserById(Long id) {
		if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}

//	getUserByUsername
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
