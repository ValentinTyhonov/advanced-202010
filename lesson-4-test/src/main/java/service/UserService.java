package service;

import exception.AlreadyExistException;
import exception.NotFoundException;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService
{

    List<User> readAll() throws SQLException;

    User read(int id) throws NotFoundException, SQLException;

    void create(User user) throws AlreadyExistException;

    void delete(int id) throws NotFoundException, SQLException;

    User readByEmail(String email) throws SQLException;
}
