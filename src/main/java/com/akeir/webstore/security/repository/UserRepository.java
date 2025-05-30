package com.akeir.webstore.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akeir.webstore.security.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByUsername(String username);

}
