package org.example.lld.practice.design_car_rental_system.improved_solution;

import org.example.lld.practice.design_car_rental_system.improved_solution.factories.CarFactory;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.Booking;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.CarType;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.User;
import org.example.lld.practice.design_car_rental_system.improved_solution.services.BookingService;
import org.example.lld.practice.design_car_rental_system.improved_solution.services.CarService;
import org.example.lld.practice.design_car_rental_system.improved_solution.services.RentalService;
import org.example.lld.practice.design_car_rental_system.improved_solution.services.UserService;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl.CreditCardPayment;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl.LoyaltyMemberBilling;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl.PayPalPayment;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl.StandardBilling;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        CarService carService = new CarService();
        BookingService bookingService = new BookingService();

        RentalService standardService = new RentalService(userService, carService, bookingService, new StandardBilling());
        RentalService loyaltyService = new RentalService(userService, carService, bookingService, new LoyaltyMemberBilling());

        // Populate data
        User alice = new User("user-1", "Alice");
        User bob = new User("user-2", "Bob");
        userService.save(alice);
        userService.save(bob);

        Car sedan = CarFactory.createCar("Honda", "Civic", CarType.SEDAN);
        Car suv = CarFactory.createCar("Toyota", "RAV4", CarType.SUV);
        carService.save(sedan);
        carService.save(suv);

        System.out.println("--- Test Case 1: Successful Booking for Standard User ---");
        LocalDateTime start1 = LocalDateTime.of(2025, 12, 1, 10, 0);
        LocalDateTime end1 = LocalDateTime.of(2025, 12, 5, 10, 0);
        Booking booking1 = standardService.bookCar("user-1", sedan.getCarId(), start1, end1, new CreditCardPayment());

        System.out.println("\n--- Test Case 2: Booking an already rented car ---");
        LocalDateTime start2 = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime end2 = LocalDateTime.of(2025, 12, 6, 12, 0);
        standardService.bookCar("user-2", sedan.getCarId(), start2, end2, new PayPalPayment()); // Should fail

        System.out.println("\n--- Test Case 3: Booking for Loyalty User with Discount ---");
        LocalDateTime start3 = LocalDateTime.of(2026, 1, 1, 9, 0);
        LocalDateTime end3 = LocalDateTime.of(2026, 1, 3, 9, 0);
        loyaltyService.bookCar("user-2", suv.getCarId(), start3, end3, new CreditCardPayment());

        System.out.println("\n--- Test Case 4: Finding available cars ---");
        List<Car> availableCars = carService.findAvailableCars();
        System.out.println("Available cars: " + availableCars.stream().map(Car::getModel).collect(Collectors.joining(", ")));

    }
}
