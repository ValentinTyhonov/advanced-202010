package service.impl;

import dao.ProductDao;
import dao.imp.ProductDaoImpl;
import exception.AlreadyExistException;
import exception.NotFoundException;
import model.Product;
import org.apache.log4j.Logger;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    @Override
    public List<Product> readAll() throws SQLException {
        logger.info("Read all products request");
        return productDao.readAll();
    }

    @Override
    public Product read(int id) throws SQLException, NotFoundException {
        Product product = productDao.read(id);
        if (product == null) {
            logger.error("Product with id : " + id + " not found");
            throw new NotFoundException("Product with id : " + id + " not found");
        } else {
            logger.info("Getting product with id : " + id);
            logger.info(product);
            return product;
        }
    }

    @Override
    public void create(Product product) throws SQLException, AlreadyExistException {
        if (isExists(product.getId())) {
            logger.error("Product with id : " + product.getId() + " already exists and can`t be created");
            throw new AlreadyExistException("Product with id : " + product.getId() + " already exists");
        } else {
            logger.info("Creating product : " + product);
            productDao.create(product);
        }
    }

    @Override
    public void update(int id, Product current) throws SQLException, AlreadyExistException, NotFoundException {
        if (isExists(id)) {
            productDao.update(id, current);
            logger.info("Product with id : " + id + " was updated by product : " + current);
        } else {
            logger.error("Product with id : " + id + " not found and can`t be updated");
            throw new NotFoundException("Product with id : " + id + " not found");
        }
    }

    @Override
    public void delete(int id) throws SQLException, NotFoundException {
        if (!isExists(id)) {
            logger.error("Product with id : " + id + " not found and can`t be deleted");
            throw new NotFoundException("Product with id : " + id + " not found");
        } else {
            productDao.delete(id);
            logger.info("Product with id : " + id + " was deleted");
        }
    }

    private List<Product> getAll() throws SQLException {
        return productDao.readAll();
    }

    private boolean isExists(int productId) throws SQLException {
        boolean flag = false;
        for (Product product : getAll()) {
            if (product.getId() == productId) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
