package com.akeir.webstore.infra.initializers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.akeir.webstore.mngr.model.Author;
import com.akeir.webstore.mngr.model.Book;
import com.akeir.webstore.mngr.repository.AuthorRepository;
import com.akeir.webstore.mngr.repository.BookRepository;

@Component
public class DatabaseInitializer implements CommandLineRunner{

//	@Autowired
//	private AuthorRepository authorRepository;
//	
//	@Autowired
//	private BookRepository bookRepository;
//	
	@Override
	public void run(String... args) throws Exception 
	{
//		createFirstAuthor();
//		createFirstBook();
	}
//
//	private void createFirstAuthor() 
//	{
//		Author author = new Author();
//		author.setName("Fyodor Dostoevsky");
//		
//		if(Optional.empty().equals(authorRepository.findByName(author.getName())))
//		{
//			authorRepository.save(author);
//			System.out.println("DatabaseInitializer.createFirstAuthor :: Saved author Fyodor Dostoevski");
//		}
//		else
//		{
//			System.out.println("DatabaseInitializer.createFirstAuthor :: Fyodor Dostoevski already registered");
//		}
//	}
//	
//	private void createFirstBook()
//	{
//		Book book = new Book();
//		book.setTitle("The Brothers Karamazov");
//		book.setPublisher("Farrar Straus Giroux");
//		book.setGenre("Philosophical novel");
//		book.setPageQuantity("824");
//		book.setAuthor(authorRepository.findByName("Fyodor Dostoevsky").get());
//		
//		if(bookRepository.findByTitle(book.getTitle()).get().isEmpty())
//		{
//			bookRepository.save(book);
//			System.out.println("DatabaseInitializer.createFirstBook :: Saved book The Brothers Karamazov");
//		}
//		else
//		{
//			System.out.println("DatabaseInitializer.createFirstBook :: The Brothers Karamazov was already registered");
//		}
//	}

}
