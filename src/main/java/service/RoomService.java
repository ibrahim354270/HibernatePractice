package service;

import domain.Hotel;
import domain.Room;
import exceptions.HotelNotFoundException;
import exceptions.RoomNotFoundException;
import repository.RoomRepository;

import java.util.List;
import java.util.Scanner;

public class RoomService {

    private Scanner scanner=new Scanner(System.in);

    private final RoomRepository roomRepository;

    private final HotelService hotelService;

    public RoomService(RoomRepository roomRepository, HotelService hotelService) {
        this.roomRepository = roomRepository;
        this.hotelService=hotelService;
    }

    //save
    public void saveRoom(){
        Room room=new Room();
        System.out.println("Enter room id : ");
        room.setId(scanner.nextLong());
        scanner.nextLine();
        System.out.println("Enter room number: ");
        room.setNumber(scanner.nextLine());
        System.out.println("Enter room capacity: ");
        room.setCapacity(scanner.nextInt());
        System.out.println("Enter hotel id :");//hangi otele ait
        Long hotelId=scanner.nextLong();

        //id si verilen hotelı bulalım

        try {
            Hotel foundHotel=hotelService.findHotelById(hotelId);
            if (foundHotel!=null){
                room.setHotel(foundHotel);//odanın ait olduğu hotel set edildi.
                //otelin listesine odayı eklememize gerek yok.mapped by ekler.
                //foundHotel.getRooms().add(room);
                roomRepository.saveRoom(room);//oda tabloya eklendi.
                System.out.println("Room saved successfully. Room id : "+room.getId());
            }

        }catch (HotelNotFoundException e){
            System.out.println(e.getMessage());
        }

    }

        //findRoomById:ÖDEV
        //findAllRooms:ÖDEV
        public Room findRoomById(Long id) {
            try {
                Room foundRoom = roomRepository.findRoomById(id);
                if (foundRoom != null) {
                    System.out.println("---------------------------------");
                    System.out.println(foundRoom);

                    return foundRoom;
                } else {
                    throw new RoomNotFoundException(" Room  not found with ID: " + id);
                }
            } catch (RoomNotFoundException e) {
                System.out.println(e.getMessage());
                return null;
            }
        }


    public List<Room> findAllRooms() {

        List<Room> rooms = roomRepository.findAllRoom();
        if (!rooms.isEmpty()) {
            for (Room room : rooms) {
                System.out.println(room);
            }
        } else {
            throw new RuntimeException("No rooms found.");
        }
        return rooms;
    }

}