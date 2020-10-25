package com.senla.model.repository.HibernateImpl;

import com.senla.model.entity.Customer;
import com.senla.model.repository.api.CustomerRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerHibernateRepositoryImpl implements CustomerRepository {
    private static final Logger logger = Logger.getLogger(OrderHibernateRepositoryImpl.class);
    @Override
    public Customer read(Integer customerId) {
        Customer result = new Customer();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            result = session.get(Customer.class, customerId);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public boolean create(Customer customer) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.save(customer);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean update(Customer customer) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.update(customer);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.delete(customer);
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }
@Override
    public List<Customer> getAll() {
        List<Customer> results = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Customer> cr = cb.createQuery(Customer.class);
            Root<Customer> root = cr.from(Customer.class);
            cr.select(root);
            Query<Customer> query = session.createQuery(cr);
            results = query.getResultList();

        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

}
