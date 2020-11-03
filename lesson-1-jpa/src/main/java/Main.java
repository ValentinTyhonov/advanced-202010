import dao.UserDao;
import dao.imp.UserDaoImpl;
import exception.AlreadyExistException;
import exception.NotFoundException;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws SQLException, AlreadyExistException
    {
        UserService userService = new UserServiceImpl();

        User userToCreate = new User("email@email.com", "blablabla", "User", "User", "user");

        userService.create(userToCreate);




        List<User> users = userService.readAll();


        users.forEach(System.out::println);




    }
}
