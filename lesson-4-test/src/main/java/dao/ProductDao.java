package dao;

import exception.AlreadyExistException;
import exception.NotFoundException;
import model.Product;
import shared.AbstractCrudOperations;

import java.sql.SQLException;

public interface ProductDao extends AbstractCrudOperations<Product> {

    void update(int id, Product current) throws SQLException, AlreadyExistException, NotFoundException;
}
