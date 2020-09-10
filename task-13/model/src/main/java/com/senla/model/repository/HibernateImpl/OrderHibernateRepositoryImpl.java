package com.senla.model.repository.HibernateImpl;

import com.senla.config.annotations.Component;
import com.senla.model.entity.Order;
import com.senla.model.repository.api.OrderRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderHibernateRepositoryImpl implements OrderRepository {
    static final Logger logger = Logger.getLogger(OrderHibernateRepositoryImpl.class);
    @Override
    public Order read(Integer orderId) {
        Order result = new Order();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.get(Order.class, orderId);
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
    public boolean create(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
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
    public boolean update(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(order);
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
    public boolean delete(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(order);
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
    public List<Order> getAll() {
        List<Order> results = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Order> query = session.createQuery("select b from Order b left join fetch b.books " +
                    "left join fetch b.listOfRequestForBooks left join fetch b.customer left join fetch b.listOfRequestForBooks");
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
