package org.logos.service;

import org.logos.dao.Blog;
import org.logos.exception.DuplicateBlogException;
import org.logos.exception.NoSuchBlogException;

import java.sql.SQLException;
import java.util.List;

public interface BlogService
{

    List<Blog> getAll() throws SQLException, ClassNotFoundException;

    Blog getBlogById(int id) throws SQLException, NoSuchBlogException, ClassNotFoundException;

    void createBlog(Blog blog) throws SQLException, NoSuchBlogException, DuplicateBlogException, ClassNotFoundException;
}
