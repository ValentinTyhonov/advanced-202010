package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;

import exception.AlreadyExistException;
import exception.NotFoundException;
import lombok.extern.log4j.Log4j;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.ProductService;
import util.SessionFactoryUtil;

import java.sql.SQLException;
import java.util.List;
@Log4j
public class ProductServiceImpl implements ProductService {

    Session session;

    private ProductDao productDao;

    public ProductServiceImpl(){
        this.session = SessionFactoryUtil.createSession();
    }

    @SuppressWarnings({"unchecket","JpaQlInspection"})
    @Override
    public List<Product> readAll() throws SQLException {

        log.info("Trying to get all product.");
        return session.createQuery("SELECT u FROM Product u").list();
    }

    @Override
    public Product read(int id) throws SQLException, NotFoundException {

        log.info("Trying to get product.");
        return session.get(Product.class, id);
    }

    @Override
    public void create(Product product) throws AlreadyExistException {

        log.info("Trying to create product.");

        Transaction transaction = session.beginTransaction();
        try {
            session.persist(product);
            transaction.commit();
        }
        catch (Exception e) {
            transaction.rollback();
        }

        log.info("New product with name : " + product.getName() + " was create.");
    }

    @Override
    public void delete(int id) throws SQLException, NotFoundException {

        Transaction transaction = session.beginTransaction();

        try{
            Product product = this.read(id);
            session.delete(product);
            transaction.commit();
        }
        catch (Exception e){
            transaction.rollback();
        }
    }

    @Override
    public void update(int id, Product product) throws AlreadyExistException {

        log.info("Trying to update product");
        try{
            if (productDao.exists(id)){
                productDao.update(id,product);
                log.info("Product update ok");

            }
            else {
                log.info("Now product update");
            }
        }
        catch (SQLException e) {
            log.error("Exception in SQL ", e);

        }
    }
}
