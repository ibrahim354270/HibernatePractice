package repository;

import config.HibernateUtils;
import domain.Hotel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HotelRepository {

    private Session session;

    public void save(Hotel hotel){
        try {
            session= HibernateUtils.getSessionFactory().openSession();
            Transaction transaction =session.beginTransaction();

            session.save(hotel);

            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public Hotel findById(Long id) {

        try {
            session= HibernateUtils.getSessionFactory().openSession();
            return session.get(Hotel.class,id);

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }

        return null;
    }

    public List<Hotel> findAll() {
        try {
            session=HibernateUtils.getSessionFactory().openSession();
            List<Hotel> hotelList=session.createQuery("FROM Hotel", Hotel.class).getResultList();
            return hotelList;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void deleteById(Hotel hotel) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.delete(hotel);

            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();

        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void updateById(Hotel hotel) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.update(hotel);

            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();

        }finally {
            HibernateUtils.closeSession(session);
        }
    }
    }
}
