package org.logos;

import lombok.extern.log4j.Log4j;
import org.logos.dao.Blog;
import org.logos.dao.User;
import org.logos.exception.DuplicateBlogException;
import org.logos.exception.NoSuchBlogException;
import org.logos.service.BlogService;
import org.logos.service.UserService;
import org.logos.service.imp.BlogServiceImpl;
import org.logos.service.imp.UserServiceImpl;

import java.sql.SQLException;

@Log4j
public class Main
{
    private static BlogService blogService = new BlogServiceImpl();
    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException, NoSuchBlogException, DuplicateBlogException, ClassNotFoundException
    {
        log.info(" ----------- GET all ---------------- ");
        blogService.getAll().forEach(System.out::println);

        log.info(" ------------- GET by ID ----------------");
        System.out.println(blogService.getBlogById(4));

        log.info(" ---------- CREATE User --------------");
        userService.createUser(new User(9, "New"));

        log.info(" ---------- CREATE Blog with User --------------");
        blogService.createBlog(new Blog(7, "Window"));
        blogService.createBlog(new Blog(8, "Window"));

        log.info(" ----------- GET all ---------------- ");
        blogService.getAll().forEach(System.out::println);
    }
}
