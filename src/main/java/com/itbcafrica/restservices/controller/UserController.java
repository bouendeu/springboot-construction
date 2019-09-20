package com.itbcafrica.restservices.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.itbcafrica.restservices.entities.User;
import com.itbcafrica.restservices.exceptions.UserExistsException;
import com.itbcafrica.restservices.exceptions.UserNotFoundException;
import com.itbcafrica.restservices.services.UserService;

@RestController
public class UserController {

//	autowired the UserService
	@Autowired
	private UserService userService;

//	getAllUsers method
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

//	createUser Method

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		try {
			return userService.createUser(user);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
//getUserById

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

//	updateUserId
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

//	deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}

//	getUserByUsername

	@GetMapping("/users/byusername/{username}")
	public User getUserByUsername(@PathVariable("username") String username) {
		return userService.getUserByUsername(username);
	}
}
