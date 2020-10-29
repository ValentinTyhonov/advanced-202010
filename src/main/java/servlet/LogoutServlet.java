package servlet;

import lombok.extern.log4j.Log4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Log4j
@WebServlet("/logout")

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();

        if (session != null){
            String user = (String) session.getAttribute("userName");
            log.info("User " + user + " is trying to logout");
            session.invalidate();

            resp.setContentType("text/plain");
            resp.setCharacterEncoding("UTF-8");

            try (PrintWriter writer = resp.getWriter()){
                writer.write("Success");
            }
        }
    }
}
