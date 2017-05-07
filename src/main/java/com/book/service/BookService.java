package com.book.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.book.entity.Book;
import com.book.hibernate.BookRepository;

@Path("/book")
public class BookService {

	BookRepository bookRepository = new BookRepository();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/searchBook/{name}/{isbn}/{genre}/{type}/{author}/{publicationDate}/{edition}/{price}")
	public Response searchBook(@PathParam("name") String name, @PathParam("isbn") String isbn,
			@PathParam("genre") String genre, @PathParam("type") String type, @PathParam("author") String author,
			@PathParam("date") String date, @PathParam("edition") String edition, @PathParam("price") float price) throws Exception {

		Book book = new Book(name, isbn, genre, type, author, date, edition, price);

		List<Book> bookList = bookRepository.searchBook(book);

		return Response.status(200).entity(new GenericEntity<List<Book>>(bookList) {
		}).build();

	}

	@DELETE
	@Path("/deleteBook/{bookId}")
	public Response deleteBook(@PathParam("bookId") Long bookId) {

		String output = "";
		bookRepository.delete(bookId);

		try {
			bookRepository.delete(bookId);
			output = "Book " + bookId + " deleted successfully";

		} catch (Exception ex) {
			ex.printStackTrace();
			output = "Error occurred while deleting a book";
		}
		return Response.status(200).entity(output).build();

	}

	@POST
	@Path("/createBook/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(Book book) {

		String output = "";

		try {
			bookRepository.createBook(book);
			output = "Book :" + book.getName() + " added successfully";

		} catch (Exception ex) {
			ex.printStackTrace();
			output = "Error occurred while creating a book";
		}
		return Response.status(200).entity(output).build();

	}

}
