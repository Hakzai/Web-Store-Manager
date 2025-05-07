package com.akeir.webstore.mngr.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	@Schema(accessMode = AccessMode.READ_ONLY)
	private Integer id;
	
	@Column(nullable = false)
	private String title;
	
	@Column
	private String genre;
	
	@Column(nullable = false)
	private String publisher;
	
	@Column
	private String pageQuantity;
	
	@ManyToOne
	@JoinColumn(name = "author_id", nullable = false)
	private Author author;

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getTitle() 
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public String getGenre() 
	{
		return genre;
	}

	public void setGenre(String genre) 
	{
		this.genre = genre;
	}

	public String getPublisher() 
	{
		return publisher;
	}

	public void setPublisher(String publisher) 
	{
		this.publisher = publisher;
	}

	public String getPageQuantity() 
	{
		return pageQuantity;
	}

	public void setPageQuantity(String pageQuantity) 
	{
		this.pageQuantity = pageQuantity;
	}
	
	public Author getAuthor()
	{
		return author;
	}
	
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	
	public int retrieveAuthorId()
	{
		return author.getId();
	}
	
	@Override
	public String toString()
	{
		return this.getTitle();
	}
}
