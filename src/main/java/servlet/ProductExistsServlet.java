package servlet;

import com.google.gson.Gson;
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
import java.util.List;

@Log4j
@WebServlet("/productExists")
public class ProductExistsServlet extends HttpServlet {

    private ProductService productService;

    public ProductExistsServlet() {
        productService = new ProductServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        int id = Integer.parseInt(req.getParameter("id"));

        System.out.println("Ми в сервелеті пошуку продукту по ід. Наш Ід : " + id);

            log.info("Getting one product...");
            log.info(req.getParameter("id"));
            Product product = productService.read(id);
            String productsJson = new Gson().toJson(product);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(new Gson().toJson(productsJson));
            }
            log.info("One product are sent to UI.");
        }
    }

