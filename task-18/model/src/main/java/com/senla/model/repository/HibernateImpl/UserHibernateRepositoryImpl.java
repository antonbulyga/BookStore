package com.senla.model.repository.HibernateImpl;

import com.senla.model.entity.User;
import com.senla.model.entity.User_;
import com.senla.model.repository.api.UserRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserHibernateRepositoryImpl implements UserRepository {
    private static final Logger logger = Logger.getLogger(User.class);

    @Override
    public List<User> findAll() {
        List<User> results = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root);
            root.fetch(User_.roles, JoinType.LEFT);
            Query<User> query = session.createQuery(cr);
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

    @Override
    public User findByUsername(String userName) {
        User user = new User();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);
            root.fetch(User_.roles, JoinType.LEFT);
            cq.where(cb.equal(root.get(User_.userName), userName));
            user = session.createQuery(cq).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            logger.info("User not found");
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        User result = new User();
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            result = session.get(User.class, id);
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = findById(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
    }

    @Override
    public User update(User user) {
        Transaction transaction = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(e);
        }
        return user;

    }
}
