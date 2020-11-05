import dao.UserDao;
import dao.impl.UserDaoImpl;
import exception.AlreadyExistException;
import exception.NotFoundException;
import model.Product;
import model.User;
import service.ProductService;
import service.UserService;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, AlreadyExistException, NotFoundException {

        ProductService productService = new ProductServiceImpl();

//        List<Product> products = productService.readAll();
//        products.forEach(System.out::println);

//        System.out.println(productService.read(2));

//        productService.delete(2998426);

//        Product productToCreate = new Product(3,"Raspberry","Raspberry z Voyutuchiv",66.05);
//        productService.create(productToCreate);



//        UserService userService = new UserServiceImpl();

//        System.out.println(userService.read(4));

//        userService.delete(6);

//        System.out.println(userService.readByEmail("LadnukOleg@gmail.com"));

//        List<User> userList = userService.readAll();
//        userList.forEach(System.out::println);

//        User userToCreate = new User(8,"Khajmike8@gmail.com","11118","Khay8","Mike8","admin");
//        userService.create(userToCreate);

//        List<User> users = userService.readAll();
//
//        users.forEach(System.out::println);
    }
}
