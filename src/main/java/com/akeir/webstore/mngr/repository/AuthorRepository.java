package com.akeir.webstore.mngr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akeir.webstore.mngr.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	Optional<Author> findByName(String string);

}
