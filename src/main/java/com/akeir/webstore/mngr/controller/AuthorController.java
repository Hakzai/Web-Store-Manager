package com.akeir.webstore.mngr.controller;

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

import com.akeir.webstore.mngr.model.Author;
import com.akeir.webstore.mngr.repository.AuthorRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/authors")
@Tag(name = "Author Controller", description = "Supports operations: list, create, edit, find by id, delete")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "403", description = "User is not allowed to perform action", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;
	
	@GetMapping("/list")
	@Operation(summary = "List Authors", description = "Search and retrieves all Authors")
	public List<Author> list()
	{
		return authorRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Find Author By Id", description = "Search and retrieve Author for corresponding Id")
	public Author findById(@PathVariable Integer id)
	{
		return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}
	
	@PostMapping("/create")
	@Operation(summary = "Create Authors", description = "Will create and save Authors. Author Ids are auto incremented so if Id is sent on the request, it will return error.")
	public Author create(@RequestBody Author author) throws OperationsException
	{
		if(author.getId() != null) throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
		
		return authorRepository.save(author);
	}
	
	@PostMapping("/edit")
	@Operation(summary = "Edit Authors", description = "Will edit and save Authors. If Author Id is not sent on the request, it will return error.")
	public Author edit(@RequestBody Author author)
	{
		if(author.getName().isEmpty())
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		findById(author.getId());
		return authorRepository.save(author);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Authors", description = "Will delete Author for given Id. If Author does not exist, will not return error.")
	public void delete(@PathVariable Integer id)
	{
		authorRepository.deleteById(id);
	}
}