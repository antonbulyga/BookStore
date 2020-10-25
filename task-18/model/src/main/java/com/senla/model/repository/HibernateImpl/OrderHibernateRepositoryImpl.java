package com.senla.model.repository.HibernateImpl;

import com.senla.model.entity.Order;
import com.senla.model.entity.Order_;
import com.senla.model.repository.api.OrderRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderHibernateRepositoryImpl implements OrderRepository {
    private static final Logger logger = Logger.getLogger(OrderHibernateRepositoryImpl.class);

    @Override
    public Order read(Integer orderId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> root = cq.from(Order.class);
        cq.select(root);
        root.fetch(Order_.customer, JoinType.LEFT);
        root.fetch(Order_.listOfRequestForBooks, JoinType.LEFT);
        cq.where(cb.equal(root.get("id"), orderId));
        TypedQuery<Order> q = session.createQuery(cq);
        return q.getSingleResult();
    }

    @Override
    public boolean create(Order order) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.save(order);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean update(Order order) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.update(order);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean delete(Order order) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.delete(order);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public List<Order> getAll() {
        List<Order> results = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> cr = cb.createQuery(Order.class);
            Root<Order> root = cr.from(Order.class);
            cr.select(root);
            root.fetch(Order_.customer, JoinType.LEFT);
            root.fetch(Order_.listOfRequestForBooks, JoinType.LEFT);
            Query<Order> query = session.createQuery(cr);
            results = query.getResultList();
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }
}
