package com.senla.model.repository.HibernateImpl;


import com.senla.model.entity.Book;
import com.senla.model.entity.Book_;
import com.senla.model.repository.api.BookRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookHibernateRepositoryImpl implements BookRepository {
    private static final Logger logger = Logger.getLogger(OrderHibernateRepositoryImpl.class);
    @Override
    public Book read(Integer bookId) {
        Book result = new Book();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            result = session.get(Book.class, bookId);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean create(Book book) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.save(book);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean update(Book book) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.update(book);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean delete(Book book) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.delete(book);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public List<Book> getAll() {
        List<Book> results = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cr = cb.createQuery(Book.class);
            Root<Book> root = cr.from(Book.class);
            cr.select(root);
            root.fetch(Book_.order, JoinType.LEFT);
            Query<Book> query = session.createQuery(cr);
            results = query.getResultList();
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    public List<Book> sortBookByPrice(){
        List<Book> results = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cr = cb.createQuery(Book.class);
        Root<Book> root = cr.from(Book.class);
        cr.orderBy(cb.asc(root.get("price")));
        Query<Book> query = session.createQuery(cr);
        results = query.getResultList();
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }
}
