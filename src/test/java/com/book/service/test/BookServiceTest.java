package com.book.service.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.book.hibernate.BookRepository;

public class BookServiceTest {

	private BookRepository bookRepository;

	@Before
	public void setUp() throws Exception {
		bookRepository = new BookRepository();
	}

	@Test
	public void testSearchBook() {
		bookRepository.searchBook(book)
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateBook() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateBooks() {
		fail("Not yet implemented");
	}

}
