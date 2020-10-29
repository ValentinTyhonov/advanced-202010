package servlet;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import service.UserService;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);


    private UserService userService;

    public LoginServlet(){
        userService = new UserServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email =req.getParameter("email");

        User user = userService.readByEmail(email);

        if(!Objects.isNull(user)){

            String password = req.getParameter("password");

            if (user.getPassword().equals(password)){

//            logger.info("User with email : " + email + " was logger in");
                System.out.println("User with email : " + email + " was logger in");

                HttpSession session = req.getSession(true);
                session.setAttribute("userName",user.getFirstName());
                session.setAttribute("userEmail",email);

//                req.getRequestDispatcher("cabinet.jsp").forward(req,resp);
                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");

                try (PrintWriter writer = resp.getWriter()){
                    writer.write("Success");
                }
            }
            else
            {
//                logger.info("Wrong password for user with email : " + email);
                System.out.println("Wrong password for user with email : " + email);
//                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        }
        else
        {
//            logger.info("User with email : " + email + " is not registered. Redirection to registration page.");
            System.out.println("User with email : " + email + " is not registered. Redirection to registration page.");
//            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
