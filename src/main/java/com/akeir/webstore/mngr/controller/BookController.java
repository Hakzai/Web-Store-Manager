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

import com.akeir.webstore.mngr.model.Book;
import com.akeir.webstore.mngr.repository.BookRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Controller", description = "Supports operations: list, create, edit, find by id, find by author, delete")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "403", description = "User is not allowed to perform action", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/list")
	@Operation(summary = "List Books", description = "Search and retrieves all books")
	public List<Book> list()
	{
		return bookRepository.findAll();
	}
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Find Book By Id", description = "Search and retrieve book for corresponding Id")
	public Book findById(@PathVariable Integer id)
	{
		return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}
	
	@GetMapping("/findBooksByAuthorId/{id}")
	@Operation(summary = "Find Books By Author", description = "Search and retrieve all books for corresponding Author Id")
	public List<Book> findAllBooksByAuthorId(@PathVariable Integer id)
	{
		return bookRepository.findAll().stream()
				.filter(book -> book.retrieveAuthorId() == id)
				.toList();
	}
	
	@PostMapping("/create")
	@Operation(summary = "Create Books", description = "Will create and save Books. Book Ids are auto incremented so if Id is sent on the request, it will return error. "
			+ "Author Ids are mandatory, in case they are not sent, it will return error. Author name can be ignored, it'll be mapped through Id")
	public Book create(@RequestBody Book book) throws OperationsException
	{
		if(book.getId() != null) throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
		
		return bookRepository.save(book);
	}
	
	@PostMapping("/edit")
	@Operation(summary = "Edit Books", description = "Will edit and save Books. If Book Id is not sent on the request, it will return error. Author Ids are also mandatory.")
	public Book edit(@RequestBody Book book)
	{
		if(book.getTitle().isEmpty() || book.getPublisher().isEmpty())
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		findById(book.getId());
		return bookRepository.save(book);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Books", description = "Will delete book for given Id. If book does not exist, will not return error.")
	public void delete(@PathVariable Integer id)
	{
		bookRepository.deleteById(id);
	}
}
