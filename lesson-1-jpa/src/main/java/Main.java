import model.Capital;
import model.City;
import model.Country;
import model.Market;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.SessionFactoryUtil;

import java.util.Arrays;
import java.util.HashSet;


public class Main
{

    public static void main(String[] args)
    {
        Session session = SessionFactoryUtil.createSession();
//        Transaction transaction = session.beginTransaction();
//
//        Country ukraine = new Country();
//        ukraine.setName("Ukraine");
//        ukraine.setPopulation(48000000L);
//        Capital kyiv = new Capital();
//        kyiv.setName("Kyiv");
//        ukraine.setCapital(kyiv);
//
//        session.save(ukraine);
//
//        transaction.commit();

        Query getCountry = session.createQuery("FROM Country c WHERE c.name = :name");
        getCountry.setParameter("name", "Ukraine");
        Country retrieved = (Country) getCountry.getSingleResult();

        System.out.println(retrieved.getName());
        System.out.println(retrieved.getCapital().getName());

        City city1 = new City();
        city1.setName("Lviv");
        city1.setSquare(1241342L);
        city1.setCountry(retrieved);

        City city2 = new City();
        city2.setName("Kharkiv");
        city2.setSquare(23434L);
        city2.setCountry(retrieved);

        City city3 = new City();
        city3.setName("Odesa");
        city3.setSquare(134144L);
        city3.setCountry(retrieved);

//        Transaction transaction1 = session.beginTransaction();
//        session.save(city1);
//        session.save(city2);
//        session.save(city3);
//        transaction1.commit();

        Market market1 = new Market();
        market1.setName("Adidas");
        market1.setCities(Arrays.asList(city1, city2));

        Market market2 = new Market();
        market2.setName("Puma");
        market2.setCities(Arrays.asList(city2, city3));

        Market market3 = new Market();
        market3.setName("Reebok");
        market3.setCities(Arrays.asList(city1, city3));

        Transaction transaction2 = session.beginTransaction();
        session.save(market1);
        session.save(market2);
        session.save(market3);
        transaction2.commit();

    }
}
