package com.senla.model.repository.HibernateImpl;

import com.senla.config.annotations.Component;
import com.senla.model.entity.RequestForBook;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class RequestForBookHibernateRepositoryImpl implements RequestForBookRepository {
    static final Logger logger = Logger.getLogger(OrderHibernateRepositoryImpl.class);
    @Override
    public RequestForBook read(Integer requestForBookId) {
        RequestForBook result = new RequestForBook();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.get(RequestForBook.class, requestForBookId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean create(RequestForBook requestForBook) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(requestForBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean update(RequestForBook requestForBook) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(requestForBook);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean delete(RequestForBook requestForBook) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(requestForBook);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return true;
    }

    @Override
    public List<RequestForBook> getAll() {
        List<RequestForBook> results = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RequestForBook> cr = cb.createQuery(RequestForBook.class);
            Root<RequestForBook> root = cr.from(RequestForBook.class);
            cr.select(root);
            Query<RequestForBook> query = session.createQuery(cr);
            results = query.getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return results;
    }
}
