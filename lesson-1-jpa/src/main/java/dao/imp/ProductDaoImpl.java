package dao.imp;

import dao.ProductDao;
import model.Product;
import util.MySqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao
{
    @Override
    public List<Product> readAll() throws SQLException
    {
        List<Product> products = new ArrayList<>();
        try (
            Connection connection = MySqlConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, description, price FROM store.product")
        )
        {
            while (resultSet.next())
            {
                Product product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("description"), resultSet.getDouble("price"));
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public Product read(int id) throws SQLException
    {
        ResultSet resultSet = null;
        Product product = null;
        try (
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT id, name, description, price FROM store.product WHERE id = ?")
        )
        {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
                    resultSet.getString("description"), resultSet.getDouble("price"));
            }
        } finally
        {
            resultSet.close();
        }
        return product;
    }

    @Override
    public void create(Product product) throws SQLException
    {
        try (
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO store.product (id, name, description, price) VALUES (?, ?, ?, ?)")
        )
        {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setDouble(4, product.getPrice());
            statement.execute();
        }
    }

    @Override
    public void update(int id, Product current) throws SQLException
    {
        try (
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE store.product SET id = ?, name = ?, description = ?, price = ? WHERE (id = ?)")
        )
        {
            statement.setString(1, current.getName());
            statement.setString(2, current.getDescription());
            statement.setDouble(3, current.getPrice());
            statement.setInt(4, id);
            statement.execute();
        }
    }

    @Override
    public void delete(int id) throws SQLException
    {
        try (
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM store.product WHERE id = ?")
        )
        {
            statement.setInt(1, id);
            statement.execute();
        }
    }
}
