package com.akeir.webstore.mngr.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akeir.webstore.mngr.model.Order;
import com.akeir.webstore.mngr.model.Product;
import com.akeir.webstore.mngr.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void setOrderRefIds(Order order)
	{
		for(Product product : order.getItems())
		{
			product.setOrderRefId(order.getId());
		}
	}
	
	public void saveProducts(Order order)
	{
		for(Product product : order.getItems())
		{
			productRepository.save(product);
		}
	}
	
	public Product updateProduct(Product product)
	{
		findProduct(product.getId());
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts()
	{
		return productRepository.findAll();
	}
	
	public List<Product> getAllProductsByName(String name) 
	{
		return productRepository.findAllByName(name);
	}

	public List<Product> getAllProductsByCategory(String category) 
	{
		return productRepository.findAllByCategory(category);
	}

	public List<Product> getAllProductsByPrice(Double price) 
	{
		return productRepository.findAllByPrice(price);
	}

	public Product findProduct(long id) 
	{
		return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ENTITY NOT FOUND FOR ID " + id));
	}

	public void deleteProduct(Long id) 
	{
		productRepository.deleteById(id);
	}
}
