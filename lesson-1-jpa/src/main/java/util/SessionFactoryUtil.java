package util;

import model.Capital;
import model.City;
import model.Country;
import model.Market;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil
{
    public static Session createSession()
    {
        Configuration configuration = new Configuration();

        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(Capital.class);
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Market.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        return sessionFactory.openSession();
    }
}
