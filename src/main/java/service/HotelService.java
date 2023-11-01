package service;

import domain.Hotel;
import exceptions.HotelNotFoundException;
import repository.HotelRepository;

import java.util.List;
import java.util.Scanner;

public class HotelService {

    private Scanner scanner=new Scanner(System.in);

    //private HotelRepository hotelRepository=new HotelRepository();
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public void saveHotel(){

        Hotel hotel=new Hotel();

        System.out.println("Enter hotel ID: ");
        hotel.setId(scanner.nextLong());
        scanner.nextLine();
        System.out.println("Enter hotel name :");
        hotel.setName(scanner.nextLine());
        System.out.println("Enter hotel location: ");
        hotel.setLocation(scanner.nextLine());

        hotelRepository.save(hotel);

        System.out.println("Hotel saved successfully. Hotel ID : "+hotel.getId());

    }


    public Hotel findHotelById(Long id) {
        Hotel foundHotel=hotelRepository.findById(id);
        try {
            if (foundHotel != null) {
                System.out.println("--------------------------------------");
                System.out.println(foundHotel);
                System.out.println("--------------------------------------");
                return foundHotel;
            } else {
                throw new HotelNotFoundException("Hotel not found by id: " + id);
            }
        }catch (HotelNotFoundException e){
            System.out.println( e.getMessage());
        }
        return null;
    }

    public List<Hotel> findAllHotels() {

        List<Hotel> hotelList=hotelRepository.findAll();
        if (!hotelList.isEmpty()){
            System.out.println("------------------List of Hotels-----------------------");
            for (Hotel hotel:hotelList){
                System.out.println(hotel);
                System.out.println("-------------------------------------");
            }
        }else {
            System.out.println("Hotel list is empty!");
        }
        return hotelList;
    }
    public void deleteHotelById(Long id) {
        //idsi verilen hotelı bulalım
        Hotel foundHotel=findHotelById(id);
        if (foundHotel!=null){
            System.out.println(foundHotel);
            System.out.println("Are you sure want to delete hotel by id : "+id);
            System.out.println("Please answer with Y or N");
            String confirmation=scanner.nextLine();
            if (confirmation.equalsIgnoreCase("Y")){
                hotelRepository.deleteById(foundHotel);
                System.out.println("Hotel is deleted successfully...");
            }else {
                System.out.println("Delete operation is cancelled...");
            }

        }
    }
    public void updateHotelById(Long id) {
        Hotel existingHotel=findHotelById(id);
        if (existingHotel!=null){
            System.out.println("Enter the hotel name to update : ");
            String name=scanner.nextLine();
            System.out.println("Enter the hotel location to update : ");
            String location=scanner.nextLine();
            existingHotel.setName(name);
            existingHotel.setLocation(location);
            hotelRepository.updateById(existingHotel);
            System.out.println("Hotel is updated successfully...");
        }
    }
}
