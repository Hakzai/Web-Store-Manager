package com.akeir.webstore.mngr.controller;

import com.akeir.webstore.mngr.model.Order;
import com.akeir.webstore.mngr.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Author Controller", description = "Supports operations: list, create, edit, find by id, list by status, list by creation date, list by value, delete")
@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "401", description = "Not authenticated", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "403", description = "User is not allowed to perform action", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(mediaType = "application/json"))
})
public class OrderController {

	@Autowired
	private OrderService orderService;
    
    @PostConstruct
    public void init() 
    {
        System.out.println("🚀 OrderController foi carregado!");
    }
    
    @GetMapping
    public String testEndpoint() 
    {
        return "Test Order Service :: Order service is running!";
    }
    
    @GetMapping("/list")
    @Operation(summary = "List Orders", description = "Search and retrieves all Orders")
    public ResponseEntity<List<Order>> getAllOrders() 
    {
    	return ResponseEntity.ok(orderService.getAllOrders());
    }
    
    //TODO: IMPLEMENT LISTING BY STATUS
    //TODO: IMPLEMENT LISTING BY CREATION DATE
    //TODO: IMPLEMENT LISTING BY VALUE
    
    //TODO: IMPLEMENT
	@GetMapping("/find/{id}")
	@Operation(summary = "Find Author By Id", description = "Search and retrieve Author for corresponding Id")
	public Order findById(@PathVariable Integer id)
	{
//		return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
		return null;
	}
    
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) 
    {
        orderService.createProducts(order);
        orderService.createOrder(order);
        orderService.updateProductsRefIds(order);
    	orderService.sendOrder(order);
    	
    	return ResponseEntity.ok(order);
    }
    
    //TODO: IMPLEMENT
	@PostMapping("/update")
	@Operation(summary = "Edit Authors", description = "Will edit and save Authors. If Author Id is not sent on the request, it will return error.")
	public Order update(@RequestBody Order order)
	{
//		if(author.getName().isEmpty())
//		{
//			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
//		}
//		
//		findById(author.getId());
//		return authorRepository.save(author);
		return null;
	}
    
	//TODO: IMPLEMENT
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Authors", description = "Will delete Author for given Id. If Author does not exist, will not return error.")
	public void delete(@PathVariable Integer id)
	{
//		authorRepository.deleteById(id);
	}
}
