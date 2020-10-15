package org.logos.service;

import org.logos.dao.User;

import java.sql.SQLException;

public interface UserService
{
    void createUser(User user) throws SQLException, ClassNotFoundException;
}
