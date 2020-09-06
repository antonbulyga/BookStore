package com.senla.model.repository.HibernateImpl;

import com.senla.config.annotations.Component;
import com.senla.model.entity.Book;
import com.senla.model.repository.api.BookRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Component
public class BookHibernateImpl implements BookRepository {
    public Book read(Integer id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Book.class, id);
    }

    public boolean create(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(book);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean update(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(book);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean delete(Book book) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(book);
        tx1.commit();
        session.close();
        return true;
    }

    public List<Book> getAll() {
        List<Book> books = (List<Book>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Book").list();
        return books;
    }
}
