package servlet;

import com.google.gson.Gson;
import dto.ErrorMessage;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import model.Product;
import service.ProductService;
import service.impl.ProductServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLException;

@Log4j
@WebServlet("/product")
public class ProductServlet extends HttpServlet
{
    private ProductService productService;

    public ProductServlet()
    {
        productService = new ProductServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String description = req.getParameter("description");

            Product product = new Product(name, description, price);

            log.info(product);

            productService.create(product);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = resp.getWriter())
            {
                writer.write(new Gson().toJson(product));
            }
        } catch (SQLException e)
        {
            ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = resp.getWriter())
            {
                writer.write(new Gson().toJson(errorMessage));
            }
        }
    }
}
