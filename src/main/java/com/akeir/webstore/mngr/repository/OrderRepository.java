package com.akeir.webstore.mngr.repository;

import com.akeir.webstore.mngr.model.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Optional<Order> findByStatus(String status);
	List<Order> findAllByStatus(String status);
	
	@Query("SELECT o FROM Order o WHERE o.registerDate >= :date AND o.registerDate < :datePlusOne")
    List<Order> findAllByRegisterDate(@Param("date") LocalDateTime date, @Param("datePlusOne") LocalDateTime datePlusOne);
	
	@Query("SELECT o FROM Order o where o.total <= :total")
	List<Order> findAllByTotal(@Param("total") Double total);
}
