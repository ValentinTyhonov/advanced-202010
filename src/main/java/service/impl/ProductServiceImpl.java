package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;

import exception.AlreadyExistException;
import exception.NotFoundException;
import lombok.extern.log4j.Log4j;
import model.Product;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;
@Log4j
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl(){
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public List<Product> readAll() throws SQLException {

        log.info("Trying to get all product.");
        return productDao.readAll();
    }

    @Override
    public Product read(int id) throws SQLException, NotFoundException {

        log.info("Trying to get product.");
        Product product = productDao.read(id);
        if(product == null){
            throw new NotFoundException("Product with id " + id + " doesn't exist.");
        }
        return product;
    }

    @Override
    public void create(Product product) throws AlreadyExistException {

        log.info("Trying to create produck.");
        try {
            productDao.create(product);

        }
        catch (SQLException e) {
            log.error("Exception in SQL ", e);
        }
    }

    @Override
    public void delete(int id) throws SQLException, NotFoundException {

//        if(!productDao.delete(id)){
//            throw new NotFoundException("Produck with id " + id + " doesn't exist.");
//        }

        productDao.delete(id);
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
