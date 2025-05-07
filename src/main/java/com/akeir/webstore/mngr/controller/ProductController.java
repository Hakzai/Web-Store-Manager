package com.akeir.webstore.mngr.controller;

import java.util.InputMismatchException;
import java.util.List;

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
	@Operation(summary = "List Products", description = "Search and retrieves all Products")
	public ResponseEntity<List<Product>> getAllProducts()
	{
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
    @GetMapping("/listByName/{name}")
    @Operation(summary = "List Products by name", description = "Search and retrieves all products by given name")
    public List<Product> getProductsByName(@PathVariable String name)
    {
    	return productService.getAllProductsByName(name);
    }
    
    @GetMapping("/listByCategory/{category}")
    @Operation(summary = "List Products by date", description = "Search and retrieves all products by given category")
    public List<Product> getProductsByCategory(@PathVariable String category)
    {
    	return productService.getAllProductsByCategory(category);
    }
    
    @GetMapping("/listByPrice/{price}")
    @Operation(summary = "List Products by unit price", description = "Search and retrieves all products for maximum given price")
    public List<Product> getProductsByPrice(@PathVariable Double price)
    {
    	return productService.getAllProductsByPrice(price);
    }
	
	@GetMapping("/find/{id}")
	@Operation(summary = "Find Product By Id", description = "Search and retrieve product for corresponding Id")
	public Product findById(@PathVariable Long id)
	{
		return productService.findProduct(id);
	}
	
	@PostMapping("/update")
	@Operation(summary = "Update Product", description = "Will edit and save Products. If Product Id is not sent on the request, it will return error. Order Ids are also mandatory.")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product)
	{
		if(String.valueOf(product.getId()).isEmpty() || String.valueOf(product.getOrderRefId()).isEmpty())
		{
			throw new InputMismatchException("ONE OR MORE INPUTS ARE WRONG");
		}
		
		productService.updateProduct(product);
		
		return ResponseEntity.ok(product);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Delete Product", description = "Will delete product for given Id. If product does not exist, will not return error.")
	public void delete(@PathVariable Long id)
	{
		productService.deleteProduct(id);
	}
}
