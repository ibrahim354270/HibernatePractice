package service;

import domain.Address;
import domain.Guest;
import exceptions.GuestNotFoundException;
import repository.GuestRepository;

import java.util.List;
import java.util.Scanner;

public class GuestService {

    private Scanner scanner=new Scanner(System.in);

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest findGuestById(Long id) {
        try {
            Guest foundGuest = guestRepository.findById(id);
            if(foundGuest!=null){
                System.out.println("*-----------------------------*");
                System.out.println(foundGuest);
                return foundGuest;
            }else{
                throw new GuestNotFoundException("Guest not found with id : "+id);
            }
        }catch (GuestNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Guest> findAllGuest() {
        List<Guest> guests = guestRepository.findAll();
        if (!guests.isEmpty()) {
            for (Guest guest : guests) {
                System.out.println(guest);
            }
        } else {
            System.out.println("Guest not found");
        }
        return guests;
    }

    public void saveGuest() {
        //id otomatik generate edilecek
        Guest guest=new Guest();
        System.out.println("Enter guest name : ");
        guest.setName(scanner.nextLine());

        Address address=new Address();
        System.out.println("Enter street :");
        address.setStreet(scanner.nextLine());

        System.out.println("Enter city : ");
        address.setCity(scanner.nextLine());

        System.out.println("Enter country :");
        address.setCountry(scanner.nextLine());

        System.out.println("Enter zipcode :");
        address.setZipcode(scanner.nextInt());
        scanner.nextLine();

        guest.setAddress(address);

        guestRepository.save(guest);
        System.out.println("Guest is saved successfully...Guest id"+guest.getId());
    }

    public void deleteGuestById(Long id) {
        try {
            Guest existingGuest =findGuestById(id);
            if (existingGuest != null) {
                guestRepository.deleteById(existingGuest);
                System.out.println("Guest  delete successfully  with ID: " + id);
            }
        } catch (GuestNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
