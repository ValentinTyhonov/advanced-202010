package dao;

import exception.AlreadyExistException;
import exception.NotFoundException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao
{
    List<User> readAll() throws SQLException;

    User read(int id) throws SQLException, NotFoundException;

    void create(User user) throws SQLException, AlreadyExistException;

    boolean delete(int id) throws SQLException;

    boolean exists(int id) throws SQLException;

    boolean exists(String email) throws SQLException;

    User readByEmail(String email) throws SQLException;
}
