package domain;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "t_rezervation")
public class Reservation {

    //TODO:otomatik generate edilsin

    @Id
    private Long id;
    @Column(nullable = false)
    private LocalDate checkIn;
    @Column(nullable = false)
    private LocalDate checkOut;
    @ManyToOne
    @JoinColumn(nullable = false)//opsiyonel
    private Guest quest;
    @ManyToOne
    @JoinColumn(nullable = false)//opsiyonel
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Guest getQuest() {
        return quest;
    }

    public void setQuest(Guest quest) {
        this.quest = quest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }
}
