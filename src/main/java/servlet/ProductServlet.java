package servlet;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import service.ProductService;
import service.UserService;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);

    private ProductService productService;

    public ProductServlet(){
        productService = new ProductServiceImpl();
    }



    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");

            Product product = new Product(name,description,price);

            productService.create(product);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = resp.getWriter()){
                writer.write(new Gson().toJson(product));
            }

        }
        catch (SQLException e){
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = resp.getWriter()){
                writer.write("{ \"message\" : \"" + e.getMessage() + "\"");
            }
        }

    }
}
