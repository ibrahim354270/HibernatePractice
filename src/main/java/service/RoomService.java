package service;

import domain.Hotel;
import domain.Room;
import exceptions.HotelNotFoundException;
import repository.RoomRepository;

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

}