package org.logos.web;

import lombok.extern.log4j.Log4j;
import org.logos.dao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        log.info("Inside doGet method.");
//        super.doGet(req, resp);
        req.getRequestDispatcher("redirect.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        log.info("Inside doPost method.");
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));

        log.info(user);

        resp.setContentType("text/html");
        try (PrintWriter printWriter = resp.getWriter())
        {
            printWriter.write("Welcome " + user.getFirstName() + " " + user.getLastName());
        } catch (IOException e)
        {
            log.error(e.getMessage(), e);
        }
    }
}