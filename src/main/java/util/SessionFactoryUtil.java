package util;

import model.Product;
import model.User;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {

    public static Session createSession(){

        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Product.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory.openSession();
    }
}
