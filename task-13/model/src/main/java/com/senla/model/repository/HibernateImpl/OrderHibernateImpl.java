package com.senla.model.repository.HibernateImpl;

import com.senla.config.annotations.Component;
import com.senla.model.entity.Order;
import com.senla.model.repository.api.OrderRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Component
public class OrderHibernateImpl implements OrderRepository {
    public Order read(Integer id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Order.class, id);
    }

    public boolean create(Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean update(Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(order);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean delete(Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(order);
        tx1.commit();
        session.close();
        return true;
    }

    public List<Order> getAll() {
        List<Order> orders = (List<Order>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Order").list();
        return orders;
    }
}
