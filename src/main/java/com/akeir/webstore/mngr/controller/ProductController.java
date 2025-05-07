package com.akeir.webstore.mngr.controller;

import java.util.InputMismatchException;
import java.util.List;

import javax.management.OperationsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akeir.webstore.mngr.model.Product;
import com.akeir.webstore.mngr.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Product Controller", description = "Supports operations: list, create, edit, find by id, list by name,  delete")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "403", description = "User is not allowed to perform action", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostConstruct
	public void init()
	{
		System.out.println("🚀 ProductController foi carregado!");
	}
	
	@GetMapping
	public String testEndpoint()
	{
		return "Test Product Service :: Product service is running!";
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	//TODO: IMPLEMENT LIST BY NAME
	//TODO: IMPLEMENT LIST BY CATEGORY
	//TODO: IMPLEMENT LIST BY PRICE
	
	//TODO: IMPLEMENT
	@GetMapping("/find/{id}")
	@Operation(summary = "Find Product By Id", description = "Search and retrieve book for corresponding Id")
	public Product findById(@PathVariable Integer id)
	{
//		return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
		return null;
	}
	
	//TODO: IMPLEMENT
	@PostMapping("/create")
	@Operation(summary = "Create Products", description = "Will create and save Products. Product Ids are auto incremented so if Id is sent on the request, it will return error. "
			+ "Author Ids are mandatory, in case they are not sent, it will return error. Author name can be ignored, it'll be mapped through Id")
	public Product create(@RequestBody Product book) throws OperationsException
	{
//		if(book.getId() != null) throw new OperationsException("WRONG OPERATION. USE EDIT FOR SAVING EXISTING ENTITY");
//		
//		return bookRepository.save(book);
		return null;
	}
	
	//TODO: IMPLEMENT
	@PostMapping("/edit")
	@Operation(summary = "Edit Products", description = "Will edit and save Products. If Product Id is not sent on the request, it will return error. Author Ids are also mandatory.")
	public Product edit(@RequestBody Product book)
	{
//		if(book.getTitle().isEmpty() || book.getPublisher().isEmpty())
//		{
//			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
//		}
//		
//		findById(book.getId());
//		return bookRepository.save(book);
		return null;
	}
	
	//TODO: IMPLEMENT
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Products", description = "Will delete book for given Id. If book does not exist, will not return error.")
	public void delete(@PathVariable Integer id)
	{
//		bookRepository.deleteById(id);
	}
}
