package repository;

import config.HibernateUtils;
import domain.Guest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GuestRepository {

    private Session session;

    public Guest findById(Long id) {
        try{
            session = HibernateUtils.getSessionFactory().openSession();
            return session.get(Guest.class,id);

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public List<Guest> findAll() {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            String hql = "FROM Guest";
            List<Guest> guests = session.createQuery(hql, Guest.class).getResultList();
            HibernateUtils.closeSession(session);
            return guests;
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
        return null;
    }

    public void save(Guest guest) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.save(guest);

            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void deleteById(Guest guest) {
        try  {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(guest);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }

    }
}
