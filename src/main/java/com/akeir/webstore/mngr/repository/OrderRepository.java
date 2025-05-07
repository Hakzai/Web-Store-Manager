package com.akeir.webstore.mngr.repository;

import com.akeir.webstore.mngr.model.Order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Optional<Order> findByName(String string); //TODO: UPDATE AND DELETE
}
