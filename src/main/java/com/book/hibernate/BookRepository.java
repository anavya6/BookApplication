package com.book.hibernate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.book.entity.Book;

@SuppressWarnings("deprecation")
public class BookRepository {
	private static SessionFactory sessionFactory = null;
	private static ServiceRegistry serviceRegistry = null;

	private Session session = null;
	private Transaction tx = null;

	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();

		Properties properties = configuration.getProperties();

		serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		return sessionFactory;
	}

	public Long createBook(Book book) {

		// Configure the session factory
		configureSessionFactory();

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			String sql = "Select bookId FROM Book ORDER BY bookId DESC";

			Long bookId = (Long) session.createQuery(sql).setMaxResults(1).uniqueResult();

			DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date(book.getPublicationDate());
			//
			Book toBeSaved = new Book(bookId + 1, book.getName(), book.getIsbn(), book.getGenre(), book.getType(),
					book.getAuthor(), sdf.format(date), book.getEdition(), book.getPrice());

			Long result = (Long) session.save(toBeSaved);

			// Committing the change in the database.
			session.flush();
			tx.commit();
			return result;

		} catch (Exception ex) {
			ex.printStackTrace();

			tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return null;

	}

	public void createBooks(List<Book> book) {

	}

	public void delete(Long bookId) {

		// Configure the session factory
		configureSessionFactory();

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Book deleteObject = new Book();
			deleteObject.setBookId(bookId);

			session.delete(deleteObject);

			// Committing the change in the database.
			session.flush();
			tx.commit();

		} catch (Exception ex) {
			ex.printStackTrace();

			tx.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public List<Book> searchBook(Book book) throws Exception {

		// Configure the session factory
		configureSessionFactory();

		session = sessionFactory.openSession();
		tx = session.beginTransaction();

		String sql = "SELECT * FROM Book WHERE name =:name or isbn =:isbn or genre =:genre or type =:type or author =:author or publicationDate =:publicationDate or edition =:edition or price =:price";

		SQLQuery query = session.createSQLQuery(sql);
		query.addEntity(Book.class);
		query.setParameter("name", book.getName());
		query.setParameter("isbn", book.getIsbn());
		query.setParameter("genre", book.getGenre());
		query.setParameter("name", book.getName());
		query.setParameter("type", book.getType());
		query.setParameter("author", book.getAuthor());
		query.setParameter("publicationDate", book.getPublicationDate());
		query.setParameter("edition", book.getEdition());
		query.setParameter("price", book.getPrice());

		List<Book> bookList = query.list();

		for (Book book1 : bookList) {
			System.out.println("Name:" + book1.getName() + book1.getIsbn());
		}

		return bookList;
	}
}
