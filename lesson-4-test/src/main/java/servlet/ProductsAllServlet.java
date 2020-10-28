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
import java.util.List;

@Log4j
@WebServlet("/products")
public class ProductsAllServlet extends HttpServlet
{
    private ProductService productService;

    public ProductsAllServlet()
    {
        productService = new ProductServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    {
        log.info("Getting all products...");
        log.info(req.getParameter("id"));
        List<Product> products = productService.readAll();
        String productsJson = new Gson().toJson(products);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter())
        {
            writer.write(new Gson().toJson(productsJson));
        }
        log.info("All products are sent to UI.");
    }
}
