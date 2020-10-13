package org.logos;

import org.logos.dao.Blog;
import org.logos.dao.User;
import org.logos.exception.DuplicateBlogException;
import org.logos.exception.NoSuchBlogException;
import org.logos.service.BlogService;
import org.logos.service.UserService;
import org.logos.service.imp.BlogServiceImpl;
import org.logos.service.imp.UserServiceImpl;

import java.sql.SQLException;

public class Main
{
    private static BlogService blogService = new BlogServiceImpl();
    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException, NoSuchBlogException, DuplicateBlogException, ClassNotFoundException
    {
        System.out.println(" ----------- GET all ---------------- ");
        blogService.getAll().forEach(System.out::println);

        System.out.println(" ------------- GET by ID ----------------");
        System.out.println(blogService.getBlogById(4));

        System.out.println(" ---------- CREATE User --------------");
        userService.createUser(new User(9, "New"));

        System.out.println(" ---------- CREATE Blog with User --------------");
        blogService.createBlog(new Blog(7, "Window"));
        blogService.createBlog(new Blog(8, "Window"));

        System.out.println(" ----------- GET all ---------------- ");
        blogService.getAll().forEach(System.out::println);
    }
}
