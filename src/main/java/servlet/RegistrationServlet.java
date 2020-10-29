package servlet;

import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import model.User;
import org.apache.log4j.Logger;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);

    private UserService userService;

    public RegistrationServlet(){
        userService = new UserServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){

//        logger.info("Starting user registration.");
        System.out.println("Starting user registration.");


        String email = req.getParameter("email");

        if (Objects.isNull(userService.readByEmail(email))){

//            logger.info("Registration for new user");
            System.out.println("Registration for new user");

            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String password = req.getParameter("password");
//            String confirmPassword = req.getParameter("confirmPassword");

//            if (!email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()
//                    && password.equals(confirmPassword))
//            {
                String role = "user";
                User user = new User(email, password, firstName, lastName, role);
                userService.create(user);
//                logger.info("User was registered : " + user);
                System.out.println("User was registered : " + user);

                HttpSession session = req.getSession(true);
                session.setAttribute("userName",firstName);
                session.setAttribute("userEmail",email);

                resp.setContentType("text/plain");
                resp.setCharacterEncoding("UTF-8");

                try (PrintWriter writer = resp.getWriter()){
                    writer.write("Success");
                }
//                req.getRequestDispatcher("cabinet.jsp").forward(req,resp);
//            }
        }
        else {
//            logger.info("User with email : " + email + " already registered! Redirection to login page ...");
            System.out.println("User with email : " + email + " already registered! Redirection to login page ...");

            //TODO : redirect user on  login page if email is already in use
        }
    }
}
