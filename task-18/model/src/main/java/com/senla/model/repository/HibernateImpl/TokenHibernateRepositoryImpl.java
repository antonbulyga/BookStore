package com.senla.model.repository.HibernateImpl;

import com.senla.model.entity.Token;
import com.senla.model.entity.Token_;
import com.senla.model.entity.User;
import com.senla.model.repository.api.TokenRepository;
import com.senla.model.utils.HibernateSessionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class TokenHibernateRepositoryImpl implements TokenRepository {
    private static final Logger logger = Logger.getLogger(User.class);

    @Override
    public Boolean findByToken(String tokenNumber) {
        Token token = new Token();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Token> cq = cb.createQuery(Token.class);
            Root<Token> root = cq.from(Token.class);
            cq.select(root);
            cq.where(cb.equal(root.get(Token_.tokenNumber), tokenNumber));
            token = session.createQuery(cq).getSingleResult();
            if(token != null){
                return true;
            }
        } catch (Exception e) {
            logger.info("User not found");
            return false;
        }
        return true;
    }

    @Override
    public void addToken(String tokenNumber) {
        Token token = new Token();
        token.setTokenNumber(tokenNumber);
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.save(token);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
