package service.impl;

import dao.UserDao;

import exception.AlreadyExistException;
import exception.NotFoundException;
import lombok.extern.log4j.Log4j;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserService;
import util.SessionFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
@Log4j

public class UserServiceImpl implements UserService {

    UserDao userDao;

    EntityManager entityManager;

    Session session;

    public UserServiceImpl(){
        this.session = SessionFactoryUtil.createSession();
    }

    @SuppressWarnings({"unchecket","JpaQlInspection"})
    @Override
    public List<User> readAll() throws SQLException {

        log.info("Trying to get all users.");
        return session.createQuery("SELECT u FROM User u").list();
    }

    @Override
    public User read(int id) throws NotFoundException, SQLException {

        log.info("Trying to get user.");
        return session.get(User.class, id);
    }

    @Override
    public void create(User user) throws AlreadyExistException, SQLException {

        log.info("Trying to create user");

        Transaction transaction = session.beginTransaction();

        try {
            session.persist(user);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }

        log.info("New user with email " + user.getEmail() + " was create.");
    }

    @Override
    public void delete(int id) throws NotFoundException, SQLException {

        Transaction transaction = session.beginTransaction();

        try {
            User user = this.read(id);
            session.delete(user);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public User readByEmail(String email) throws SQLException {

        Query query = session.createQuery("FROM User u WHERE u.email = :email");
        query.setParameter("email", email);

        return (User) query.getSingleResult();
    }
}
