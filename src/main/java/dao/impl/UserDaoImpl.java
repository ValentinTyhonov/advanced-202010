package dao.impl;

import dao.UserDao;
import exception.AlreadyExistException;
import exception.NotFoundException;
import model.User;
import util.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> readAll() throws SQLException {

        List<User> users = new ArrayList<>();
        try (
                Connection connection = MySQLConnection.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM user"
                )
        ){
            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("role"));

                users.add(user);
            }
        }
        return users;
    }

    @Override
    public User read(int id) throws SQLException, NotFoundException {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT id,email,password,first_name,last_name,role FROM user WHERE id = ?"
                )
                ){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            User user = new User();
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setRole(resultSet.getString("role"));
            }
        }
        return null;
    }

    @Override
    public void create(User user) throws SQLException, AlreadyExistException {
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO user(email,password,first_name,last_name,role) VALUES (?,?,?,?,?)"
                )){
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getRole());
            statement.execute();

        }
    }

    @Override
    public boolean delete(int id) throws SQLException {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "DELETE FROM user WHERE id = ?"
                )
                ) {
            statement.setInt(1, id);
            return statement.executeUpdate()>0;
        }
    }

    @Override
    public boolean exists(int id) throws SQLException {

        ResultSet resultSet = null;
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE id = ?"
                )
                ){
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return resultSet.next();
        }
        finally {
            if (resultSet != null){
                resultSet.close();
            }
        }
    }

    @Override
    public boolean exists(String email) throws SQLException {

        ResultSet resultSet = null;
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE email = ?"
                )
                ){
            statement.setString(1, "email");
            resultSet = statement.executeQuery();
            return resultSet.next();
        }
        finally {
            if(resultSet != null){
                resultSet.close();
            }
        }
    }

    @Override
    public User readByEmail(String email) throws SQLException {
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM user WHERE email=?"
                )){
            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("role"));
                return user;
            }
        }
        return null;
    }
}
