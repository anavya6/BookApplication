package com.book.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Book")
@XmlRootElement
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long bookId;
	private String name;
	private String isbn;
	private String genre;
	private String type;
	private String author;
	private String publicationDate;
	private String edition;
	private float price;
	
	public Book(){
		
	}

	public Book(Long bookId, String name, String isbn, String genre, String type, String author, String publicationDate,
			String edition, float price) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.isbn = isbn;
		this.genre = genre;
		this.type = type;
		this.author = author;
		this.publicationDate = publicationDate;
		this.edition = edition;
		this.price = price;
	}

	public Book(String name, String isbn, String genre, String type, String author, String publicationDate, String edition,
			float price) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.genre = genre;
		this.type = type;
		this.author = author;
		this.publicationDate = publicationDate;
		this.edition = edition;
		this.price = price;
	}

	@Id
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
