package org.logos.service.imp;

import org.logos.dao.User;
import org.logos.jdbc.MySqlConnector;
import org.logos.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserServiceImpl implements UserService
{
    @Override
    public void createUser(User user) throws SQLException, ClassNotFoundException
    {
        try (
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO web.user (id, full_name) VALUES (?, ?)")
        )
        {
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFullName());
            statement.execute();
        }
    }
}
