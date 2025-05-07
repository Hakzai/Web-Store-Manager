package com.akeir.webstore.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
class OrderControllerTests {

//	@Mock
//	private AuthorRepository authorRepository;
	
//	@InjectMocks
//	private AuthorController authorController;
	
	@Test
	@DisplayName("Save first author in the database")
	void saveFirstAuthor()
	{
//		Author author = new Author();
//		author.setName("Fyodor Dostoevsky");
		
//		Assertions.assertThrows(DataIntegrityViolationException.class, () -> authorRepository.save(author));
		
	}
	
	@Test
	@DisplayName("Save with wrong operation: create instead of edit")
	void saveWithCreateInsteadOfEdit()
	{
//		Author author = new Author();
//		author.setId(1);
//		author.setName("Fyodor Dostoevsky");
		
	}
	
}
