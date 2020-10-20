import exception.AlreadyExistException;
import exception.NotFoundException;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, NotFoundException, AlreadyExistException {
        //UserDaoImpl service = new UserDaoImpl();
        //System.out.println(service.read(1));
        //service.create(new User(5, "Test@gmail.com", "Test_CREATE_5", "Test_2", "test_role"));
        //service.update(4, new User(3, "Test@gmail.com", "Test_4", "Test_2", "test_role"));
        //service.delete(0);
        //ПРИ create and update ID field is REQUIRED!!

        //ProductDaoImpl productDao = new ProductDaoImpl();
        //productDao.readAll().forEach(System.out::println);
        //System.out.println(productDao.read(6));
        //productDao.create(new Product("CheeseCake", "Thia is cheeese cake", 15.5));
        //productDao.update(12, new Product("Airpods", "Thia is Apple airpods", 480));
        //productDao.delete(11);

        //BucketDaoImpl bucketDao = new BucketDaoImpl();
        //bucketDao.readAll().forEach(System.out::println);
        //System.out.println(bucketDao.read(1));
        //bucketDao.create(new Bucket(3, new Timestamp(System.currentTimeMillis())));
        //bucketDao.delete(3);

        //UserServiceImpl userService = new UserServiceImpl();
        //userService.readAll().forEach(System.out::println);
        //userService.read(7);
        //userService.create(new User(8, "email-8","pass-8", "FN-8", "LN-8", "test_role"));
        //userService.update(8, new User(9, "test@yahoo.com", "test_pass", "10-First_name", "10-last-name", "admin"));
        //userService.delete(9);
    }
}
