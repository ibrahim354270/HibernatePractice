package repository;

import config.HibernateUtils;
import domain.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomRepository {

    private Session session;

    public void saveRoom(Room room) {

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.persist(room);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);

        }
    }


    public Room findRoomById(Long roomId) {
        session = HibernateUtils.getSessionFactory().openSession();
        Room room=session.get(Room.class, roomId);
        HibernateUtils.closeSession(session);
        return room;
    }


    public List<Room> findAllRoom() {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            List<Room> rooms = session.createQuery("FROM Room", Room.class).getResultList();
            return rooms;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

    public void deleteById(Room room ) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(room);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            HibernateUtils.closeSession(session);
        }
    }

}
