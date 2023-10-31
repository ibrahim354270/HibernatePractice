package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
public class Room {//many(hotel)

    @Id
    private Long id;

    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private int capacity;

    @ManyToOne//room tablosunda FK:hotel_id
    @JoinColumn(name = "hotel_id",nullable = false)//opsiyonel
    private Hotel hotel;

    //TODO: one-to-many
//    private List<Reservation> reservations=new ArrayList<>();

    //default const
    public Room() {
    }

    //param const
    public Room(Long id, String number, int capacity, Hotel hotel) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
        this.hotel = hotel;
    }

        //getter-setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

        /*    public List<Reservation> getReservations() {
                return reservations;
            }

            public void setReservations(List<Reservation> reservations) {
                this.reservations = reservations;
            }*/

    //toString
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}