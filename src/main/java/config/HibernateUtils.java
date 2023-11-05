package config;

import domain.Guest;
import domain.Hotel;
import domain.Reservation;
import domain.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory;
    //uygulama çalıştığında config yapılsın
    static{
        try {
            Configuration config = new Configuration().
                    configure("hibernate.cfg.xml").//parametre zorunlu değil
                            addAnnotatedClass(Hotel.class).
                    addAnnotatedClass(Room.class).
                    addAnnotatedClass(Reservation.class).
                    addAnnotatedClass(Guest.class);

            sessionFactory = config.buildSessionFactory();
        }catch (Exception e){
            System.err.println("Initial Session Factory is failed!");
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    //sf kapatalım
    public static void shutDown(){
        getSessionFactory().close();
    }

    //sessionı da kapatalım
    public static void closeSession(Session session){
        if (session!=null && session.isOpen()){
            session.close();
        }
    }



}
