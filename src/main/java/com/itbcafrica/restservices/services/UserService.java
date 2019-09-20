package com.itbcafrica.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.itbcafrica.restservices.entities.User;
import com.itbcafrica.restservices.exceptions.UserExistsException;
import com.itbcafrica.restservices.exceptions.UserNotFoundException;
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
	public User createUser(User user) throws UserExistsException {
//		check if user exist using username
		User userFromDb = this.getUserByUsername(user.getUsername());
		if (userFromDb != null) {
			throw new UserExistsException("we have the user in the repository");
		}
		User userToDB = userRepository.save(user);
		return userToDB;
	}

//	getUserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found in user Repository");
		}
		return user;

	}

//UpdateUserById
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> userfrondb = userRepository.findById(id);
		if (!userfrondb.isPresent()) {
			throw new UserNotFoundException("User not found in user Repository");
		}
		user.setId(id);
		return userRepository.save(user);
	}

//	deleteUserById

	public void deleteUserById(Long id) {

		Optional<User> userfrondb = userRepository.findById(id);
		if (!userfrondb.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in user Repository");
		}
		userRepository.deleteById(id);
	}

//	getUserByUsername
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
