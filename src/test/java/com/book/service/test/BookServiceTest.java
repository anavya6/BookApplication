package com.book.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.book.entity.Book;
import com.book.hibernate.BookRepository;

public class BookServiceTest {

	private BookRepository bookRepository;

	@Before
	public void setUp() throws Exception {

		bookRepository = new BookRepository();
	}

	@Test
	public void testSearchBook() {
		Book book = new Book(null, "name3", "", null, null, null, null, null, 0);
		List<Book> bookList;
		try {
			bookList = bookRepository.searchBook(book);
			for (Book bookIter : bookList)
				assertEquals("name3", bookIter.getName());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}

	}

	@Test
	public void testDeleteBook() {

		Long bookId = new Long(2);
		try {
			bookRepository.delete(bookId);

			Book book = new Book();
			book.setBookId(bookId);

			List<Book> bookList = bookRepository.searchBook(book);
			assertEquals(0, bookList.size());

		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

	@Test
	public void testCreateBook() {

		Book book = new Book("name6", "isbn6", "genre6", "type6", "author6", "2017/05/06 12:06:21", "edition", 220);

		try {
			bookRepository.createBook(book);

			Book searchBook = new Book();
			searchBook.setName("name6");

			List<Book> bookList = bookRepository.searchBook(searchBook);
			assertNotNull(bookList.size());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}

	}

}
