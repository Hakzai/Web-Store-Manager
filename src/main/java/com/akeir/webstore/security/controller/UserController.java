package com.akeir.webstore.security.controller;

import java.util.InputMismatchException;
import java.util.List;

import javax.management.OperationsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akeir.webstore.infra.utils.PasswordUtils;
import com.akeir.webstore.security.model.User;
import com.akeir.webstore.security.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/users")
@Tag(name = "User Controller", description = "Supports operations: list, create, edit, find by id, delete")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "403", description = "User is not allowed to perform action", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	@Operation(summary = "List Users", description = "Search and retrieves all Users")
	public List<User> list()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Find User By Id", description = "Search and retrieve User for corresponding Id")
	public User findById(@PathVariable Integer id)
	{
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}
	
	@PostMapping("/create")
	@Operation(summary = "Create Users", description = "Will create and save Users. User Ids are auto incremented so if Id is sent on the request, it will return error.")
	public User create(@RequestBody User user) throws OperationsException
	{
		if(user.getUsername().isEmpty() || user.getUsername().length() < 5 || user.getPassword().isEmpty() || user.getPassword().length() < 8)
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		else if(user.getId() != null)
		{
			throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
		}
		
		encryptPassword(user);
		return userRepository.save(user);
	}

	@PostMapping("/edit")
	@Operation(summary = "Edit Users", description = "Will edit and save Users. If User Id is not sent on the request, it will return error.")
	public User edit(@RequestBody User user)
	{
		if(user.getUsername().isEmpty() || user.getUsername().length() < 5 || user.getPassword().isEmpty() || user.getPassword().length() < 8)
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		if(!user.getPassword().equals(findById(user.getId()).getPassword())) encryptPassword(user);
		return userRepository.save(user);
	}
	
	/**
	 * Calls Password API to generate BCrypt passwords
	 * 
	 * @param user
	 */
	private void encryptPassword(User user) 
	{
		user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Users", description = "Will delete User for given Id. If User does not exist, will not return error.")
	public void delete(@PathVariable Integer id)
	{
		userRepository.deleteById(id);
	}
}
