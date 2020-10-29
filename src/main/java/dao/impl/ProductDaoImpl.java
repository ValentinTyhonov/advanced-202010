package dao.impl;

import dao.ProductDao;
import model.Product;
import util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> readAll() throws SQLException {

        List<Product> products = new ArrayList<>();
        try (
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT id,name,description,price FROM product"
                )
                ){
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product read(int id) throws SQLException {

        ResultSet resultSet = null;
        Product product = null;
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id,name,description,price FROM product WHERE id=?"
                )
                ){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            product = new Product();
            while (resultSet.next()){
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("Name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
            }
        }
        finally {
            resultSet.close();
        }
        return product;
    }

    @Override
    public void create(Product product) throws SQLException {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO product(id,name,description,price) VALUES(?,?,?,?)"
                )
                ){
            statement.setInt(1,product.getId());
            statement.setString(2,product.getName());
            statement.setString(3,product.getDescription());
            statement.setDouble(4,product.getPrice());
            statement.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM product WHERE id=?"
                )
                ){
            statement.setInt(1,id);
            statement.execute();
        }
    }

    @Override
    public void update(int id, Product product) throws SQLException {

        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE product SET name=?, description=?, price=? WHERE id=?"
                )){
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, id);
            statement.execute();
        }
    }

    @Override
    public boolean exists(int id) throws SQLException {

        ResultSet resultSet = null;
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id,name,description,price FROM product WHERE id=?"
                )
                ){
            statement.setInt(1,id);
            resultSet = statement.executeQuery();

            System.out.println("Ми знайшли цей продукт в базі (Блок exists v DAO):" + resultSet.toString());

            return resultSet.next();
            }
        finally {
            if(resultSet != null){
                resultSet.close();
            }
        }
    }
}
