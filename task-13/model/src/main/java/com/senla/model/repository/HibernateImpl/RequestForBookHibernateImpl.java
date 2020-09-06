package com.senla.model.repository.HibernateImpl;

import com.senla.config.annotations.Component;
import com.senla.model.entity.RequestForBook;
import com.senla.model.repository.api.RequestForBookRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Component
public class RequestForBookHibernateImpl implements RequestForBookRepository {
    public RequestForBook read(Integer id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(RequestForBook.class, id);
    }

    public boolean create(RequestForBook requestForBook) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(requestForBook);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean update(RequestForBook requestForBook) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(requestForBook);
        tx1.commit();
        session.close();
        return true;
    }

    public boolean delete(RequestForBook requestForBook) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(requestForBook);
        tx1.commit();
        session.close();
        return true;
    }

    public List<RequestForBook> getAll() {
        List<RequestForBook> requestForBooks = (List<RequestForBook>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From RequestForBook ").list();
        return requestForBooks;
    }
}
