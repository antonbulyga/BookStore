package com.senla.model.repository.HibernateImpl;

import com.senla.config.annotations.Component;
import com.senla.model.entity.Customer;
import com.senla.model.repository.api.CustomerRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Component
public class CustomerHibernateImpl implements CustomerRepository {
    public Customer read(Integer id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(Customer.class, id);
    }

    public boolean create(Customer customer) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(customer);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean update(Customer customer) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(customer);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean delete(Customer customer) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(customer);
        tx1.commit();
        session.close();
        return true;
    }

    public List<Customer> getAll() {
        List<Customer> customers = (List<Customer>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From Customer").list();
        return customers;
    }

}
