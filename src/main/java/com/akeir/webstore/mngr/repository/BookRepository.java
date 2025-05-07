package com.akeir.webstore.mngr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akeir.webstore.mngr.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Optional<List<Book>> findByTitle(String title);

}
