package com.senla.model.repository.HibernateImpl;

import com.senla.model.entity.Role;
import com.senla.model.entity.Role_;
import com.senla.model.repository.api.RoleRepository;
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
public class RoleHibernateRepository implements RoleRepository {
    private static final Logger logger = Logger.getLogger(Role.class);

    @Override
    public List<Role> getAll() {
        List<Role> results = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Role> cr = cb.createQuery(Role.class);
            Root<Role> root = cr.from(Role.class);
            cr.select(root);
            root.fetch(Role_.users, JoinType.LEFT);
            Query<Role> query = session.createQuery(cr);
            results = query.getResultList();

        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    @Override
    public Role findByName(String name) {
        Role role = new Role();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Role> cq = cb.createQuery(Role.class);
            Root<Role> root = cq.from(Role.class);
            cq.select(root);
            root.fetch(Role_.users, JoinType.LEFT);
            cq.where(cb.equal(root.get(Role_.name), name));
            role = session.createQuery(cq).getSingleResult();
        } catch (Exception e) {
            logger.info("User " + role.getName() + " not found");
        }
        return role;
    }

    @Override
    public Role findById(Long id) {
        Role result = new Role();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            result = session.get(Role.class, id);
        } catch (Exception e) {
            logger.error(e);
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Role role = findById(id);
            session.delete(role);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public Role update(Role role) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.update(role);
        } catch (Exception e) {
            logger.error(e);
        }
        return role;
    }
}
