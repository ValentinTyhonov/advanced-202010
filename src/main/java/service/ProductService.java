package service;

import exception.AlreadyExistException;
import exception.NotFoundException;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {

    List<Product> readAll() throws SQLException;

    Product read(int id) throws SQLException, NotFoundException;

    void create(Product product) throws SQLException, AlreadyExistException;

    void delete(int id) throws SQLException, NotFoundException;

    void update(int id, Product current) throws SQLException, AlreadyExistException;
}
