package com.akeir.webstore.mngr.controller;

import com.akeir.webstore.mngr.model.Order;
import com.akeir.webstore.mngr.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Controller", description = "Supports operations: list, create, edit, find by id, list by status, list by creation date, list by value, delete")
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
    
    @GetMapping("/listByStatus/{status}")
    @Operation(summary = "List Orders by status", description = "Search and retrieves all orders by given status")
    public List<Order> getOrdersByStatus(@PathVariable String status)
    {
    	return orderService.getAllOrdersByStatus(status.toUpperCase());
    }
    
    @GetMapping("/listByDate/{creationDate}")
    @Operation(summary = "List Orders by date", description = "Search and retrieves all orders by given date")
    public List<Order> getOrdersByCreationDate(@PathVariable String creationDate)
    {
    	return orderService.getAllOrdersByCreationDate(creationDate);
    }
    
    @GetMapping("/listByTotalPrice/{total}")
    @Operation(summary = "List Orders by total price amount", description = "Search and retrieves all orders for maximum given value")
    public List<Order> getOrdersByTotalPrice(@PathVariable Double total)
    {
    	return orderService.getAllOrdersByTotal(total);
    }
    
	@GetMapping("/find/{id}")
	@Operation(summary = "Find Order By Id", description = "Search and retrieve Order for corresponding Id")
	public Order findById(@PathVariable Long id)
	{
		return orderService.findOrder(id);
	}
    
    @PostMapping("/create")
    @Operation(summary = "Create Orders", description = "This is for creating Orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) 
    {
        orderService.createProducts(order);
        orderService.createOrder(order);
        orderService.updateProductsRefIds(order);
        // TEMP DISABLED KAFKA TO TEST ONLY DB
        // orderService.sendOrder(order);
    	
    	return ResponseEntity.ok(order);
    }
    
	@PostMapping("/update")
	@Operation(summary = "Update Orders", description = "Will edit and save Orders. If Order Id is not sent on the request, it will return error.")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order)
	{
		if(null == order.getId() || order.getId().toString().isEmpty())
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		orderService.updateOrder(order);
		
		return ResponseEntity.ok(order);
	}
    
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Order", description = "Will delete Order for given Id. If Order does not exist, will not return error.")
	public void delete(@PathVariable Long id)
	{
		orderService.deleteOrder(id);
	}
}
