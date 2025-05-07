package com.akeir.webstore.mngr.repository;

import com.akeir.webstore.mngr.model.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Optional<Product> findByName(String name);
	List<Product> findAllByName(String name);
	
	List<Product> findAllByCategory(String category);
	
	@Query("SELECT p FROM Product p where p.unitPrice <= :price")
	List<Product> findAllByPrice(@Param("price") Double price);

}
