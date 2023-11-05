package service;

import domain.Guest;
import domain.Reservation;
import domain.Room;
import exceptions.GuestNotFoundException;
import exceptions.ReservationNotFoundException;
import exceptions.RoomNotFoundException;
import repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ReservationService {

    private Scanner scanner=new Scanner(System.in);

    private final ReservationRepository reservationRepository;

    private final GuestService guestService;

    private final RoomService roomService;

    public ReservationService(ReservationRepository reservationRepository,GuestService guestService,RoomService roomService) {
        this.reservationRepository = reservationRepository;
        this.guestService=guestService;
        this.roomService=roomService;
    }

    public Reservation findReservationById(Long id) {
        Reservation foundReservation = reservationRepository.findById(id);
        try {
            if (foundReservation != null) {
                System.out.println(foundReservation);
            } else {
                throw new ReservationNotFoundException("Reservation not found with ID: " + id);
            }
        }catch (ReservationNotFoundException e){
            System.out.println(e.getMessage() );
        }
        return foundReservation;
    }

    public List<Reservation> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        if (!reservations.isEmpty()) {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        } else {
            System.out.println("There is no reservation...");
        }
        return reservations;
    }

    public void saveReservation() {

        System.out.println("Enter guest id");
        Long guestId= scanner.nextLong();
        scanner.nextLine();


        System.out.println("Enter room id");
        Long roomId=scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter check-in date (yyyy-MM-dd) :");
        LocalDate checkIn=LocalDate.parse(scanner.nextLine());

        System.out.println("Enter check-out date (yyyy-MM-dd) :");
        LocalDate checkOut=LocalDate.parse(scanner.nextLine());

        try {
            Reservation reservation = new Reservation();
            Guest guest = guestService.findGuestById(guestId);
            Room room= roomService.findRoomById(roomId);
            if (guest!=null && room!=null){

                reservation.setCheckIn(checkIn);
                reservation.setCheckOut(checkOut);
                reservation.setGuest(guest);
                reservation.setRoom(room);

                reservationRepository.save(reservation);
                System.out.println("Reservation is created successfully...");
            }

        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }catch (RoomNotFoundException e){
            System.out.println(e.getMessage());
        }

    }


    public void deleteReservationById(Long id) {
        try {
            Reservation existingReservation = findReservationById(id);
            reservationRepository.deleteById(existingReservation);
            System.out.println("Reservation deleted successfully. ID: " + id);
        } catch (ReservationNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
