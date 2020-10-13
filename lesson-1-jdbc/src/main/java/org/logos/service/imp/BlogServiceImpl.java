package org.logos.service.imp;

import org.logos.dao.Blog;
import org.logos.dao.User;
import org.logos.exception.DuplicateBlogException;
import org.logos.exception.NoSuchBlogException;
import org.logos.jdbc.MySqlConnector;
import org.logos.service.BlogService;
import org.logos.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BlogServiceImpl implements BlogService
{
    private UserService userService = new UserServiceImpl();

    @Override
    public List<Blog> getAll() throws SQLException, ClassNotFoundException
    {
        List<Blog> blogs = new ArrayList<>();

        try (
            Connection connection = MySqlConnector.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id, name FROM web.blog")
        )
        {
            while (result.next())
            {
                blogs.add(new Blog(result.getInt("id"), result.getString("name")));
            }
            return blogs;
        }
    }

    @Override
    public Blog getBlogById(int id) throws SQLException, NoSuchBlogException, ClassNotFoundException
    {
        ResultSet result = null;
        try (
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM web.blog WHERE id = ?")
        )
        {
            statement.setInt(1, id);
            result = statement.executeQuery();
            if (result.next())
            {
                return new Blog(result.getInt("id"), result.getString("name"));
            } else
            {
                throw new NoSuchBlogException("No blog with id : " + id);
            }
        } finally
        {
            if (result != null)
            {
                result.close();
            }
        }
    }

    @Override
    public void createBlog(Blog blog) throws SQLException, DuplicateBlogException, ClassNotFoundException
    {
        if (isExists(blog.getId()))
        {
            throw new DuplicateBlogException("Blog with id : " + blog.getId() + " already exists!");
        } else
        {
            System.out.println("Creating user with id : " + blog.getId());
            userService.createUser(new User(blog.getId(), "user : " + blog.getName()));
            System.out.println("Creating blog with id : " + blog.getId());
            try (
                Connection connection = MySqlConnector.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO web.blog (id, name) VALUES (?, ?)")
            )
            {
                statement.setInt(1, blog.getId());
                statement.setString(2, blog.getName());
                statement.execute();
            }
        }
    }


    private boolean isExists(int blogId) throws SQLException, ClassNotFoundException
    {
        boolean flag = false;
        for (Blog blog : getAll())
        {
            flag = blog.getId() == blogId;
        }
        return flag;
    }
}
