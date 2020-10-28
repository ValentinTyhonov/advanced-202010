package service;

import dao.ProductDao;
import model.Product;
import shared.AbstractCrudOperations;

public interface ProductService extends AbstractCrudOperations<Product>, ProductDao {
}
