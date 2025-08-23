package org.example.lld.practice.design_car_rental_system.naive_solution;

import java.util.Date;
import java.util.List;

public class NaiveCarRentalSystem {
    public static void main(String[] args) {
        RentalManger manager = new RentalManger();
        // Setup
        manager.addCar("Toyota", "Corolla", 2020, "Bangalore");
        manager.addCar("Honda", "Civic", 2019, "Bangalore");

        User user1 = manager.registerUser("Alice");

        List<Car> availableCars = manager.searchCar("Bangalore");
        System.out.println("Available Cars: " + availableCars.size());

        Booking booking = manager.rentCar(user1, availableCars.getFirst(), new Date(), new Date());
        System.out.println("Booking ID: " + booking.getId());

        manager.returnCar(booking);
    }
}
