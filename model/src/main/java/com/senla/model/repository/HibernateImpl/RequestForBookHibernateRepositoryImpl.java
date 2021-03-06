package com.senla.model.repository.HibernateImpl;

import com.senla.model.entity.RequestForBook;
import com.senla.model.entity.RequestForBook_;
import com.senla.model.repository.api.RequestForBookRepository;
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
public class RequestForBookHibernateRepositoryImpl implements RequestForBookRepository {
    private static final Logger logger = Logger.getLogger(OrderHibernateRepositoryImpl.class);
    @Override
    public RequestForBook read(Integer requestForBookId) {
        RequestForBook result = new RequestForBook();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            result = session.get(RequestForBook.class, requestForBookId);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean create(RequestForBook requestForBook) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.save(requestForBook);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean update(RequestForBook requestForBook) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.update(requestForBook);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean delete(RequestForBook requestForBook) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.delete(requestForBook);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public List<RequestForBook> getAll() {
        List<RequestForBook> results = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<RequestForBook> cr = cb.createQuery(RequestForBook.class);
            Root<RequestForBook> root = cr.from(RequestForBook.class);
            cr.select(root);
            root.fetch(RequestForBook_.order, JoinType.LEFT);
            Query<RequestForBook> query = session.createQuery(cr);
            results = query.getResultList();
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }
}
