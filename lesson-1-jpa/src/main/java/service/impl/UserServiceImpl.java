package service.impl;

import dao.UserDao;
import dao.imp.UserDaoImpl;
import exception.AlreadyExistException;
import exception.NotFoundException;
import lombok.extern.log4j.Log4j;
import model.User;
import service.UserService;
import util.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Log4j
public class UserServiceImpl implements UserService
{
    EntityManager entityManager;

    public UserServiceImpl()
    {
        this.entityManager = FactoryManager.getEntityManager();
    }

    @SuppressWarnings( {"unchecked", "JpaQlInspection"})
    @Override
    public List<User> readAll() throws SQLException
    {
        log.info("Trying to get all users");
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return (List<User>) query.getResultList();
    }

    @Override
    public User read(int id) throws NotFoundException, SQLException
    {
        log.info("Trying to get user");
        return entityManager.find(User.class, id);
    }

    @Override
    public void create(User user) throws AlreadyExistException
    {
        log.info("Trying to create user");

        if (!entityManager.getTransaction().isActive())
        {
            entityManager.getTransaction().begin();
        }

        entityManager.persist(user);
        entityManager.getTransaction().commit();

        log.info("New user with email " + user.getEmail() + " was create.");
    }

    @Override
    public void delete(int id) throws NotFoundException, SQLException
    {
        if (!entityManager.getTransaction().isActive())
        {
            entityManager.getTransaction().begin();
        }

        User user = this.read(id);

        entityManager.remove(user);
        entityManager.getTransaction().commit();

    }

    @Override
    public User readByEmail(String email) throws SQLException
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);

        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("email"), email));

        return entityManager.createQuery(criteriaQuery).getSingleResult();


    }
}
