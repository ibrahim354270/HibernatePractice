package repository;

import config.HibernateUtils;
import domain.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReservationRepository {

    private Session session;

    public Reservation findById(Long id) {

        session= HibernateUtils.getSessionFactory().openSession();

        Reservation reservation = session.get(Reservation.class, id);
        HibernateUtils.closeSession(session);
        return reservation;

    }


    public List<Reservation> findAll() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        String hql = "FROM Reservation";
        List<Reservation> reservations = session.createQuery(hql, Reservation.class).getResultList();
        if (reservations.isEmpty()) {
            System.out.println("Reservation is empty");
        }
        HibernateUtils.closeSession(session);
        return reservations;
    }

    public void save(Reservation reservation) {
        try {
            session=HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.persist(reservation);//persist ve save aynı işlevdedir.

            transaction.commit();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void deleteById(Reservation reservation) {
        try {
            session=HibernateUtils.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            session.delete(session);

            transaction.commit();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }
}
