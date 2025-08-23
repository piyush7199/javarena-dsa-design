package org.example.lld.practice.design_car_rental_system.improved_solution.services;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Booking;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.CarStatus;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.User;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.BillingStrategy;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.PaymentStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalService {
    private final UserService userService;
    private final CarService carService;
    private final BookingService bookingService;
    private final BillingStrategy billingStrategy;

    public RentalService(UserService userService, CarService carService, BookingService bookingService, BillingStrategy billingStrategy) {
        this.userService = userService;
        this.carService = carService;
        this.bookingService = bookingService;
        this.billingStrategy = billingStrategy;
    }

    public Booking bookCar(String userId, String carId, LocalDateTime startDate, LocalDateTime endDate, PaymentStrategy paymentMethod) {
        User user = userService.findById(userId);
        Car car = carService.findById(carId);

        if (user == null || car == null) {
            System.out.println("Error: User or car not found.");
            return null;
        }

        if (car.getStatus() != CarStatus.AVAILABLE) {
            System.out.println("Error: Car is not available for rental.");
            return null;
        }

        if (bookingService.isCarBooked(carId, startDate, endDate)) {
            System.out.println("Error: Car is already booked for the selected dates.");
            return null;
        }

        Booking newBooking = new Booking(userId, carId, startDate, endDate);
        double totalCost = billingStrategy.calculateCost(car, startDate, endDate);

        if (paymentMethod.processPayment(totalCost)) {
            bookingService.save(newBooking);
            car.setStatus(CarStatus.RENTED);
            carService.save(car);
            System.out.println("Successfully booked car " + car.getModel() + " for user " + user.name());
            return newBooking;
        } else {
            System.out.println("Payment failed. Booking canceled.");
            return null;
        }
    }

    public void returnCar(String bookingId) {
        Booking booking = bookingService.findById(bookingId);
        if (booking == null) {
            System.out.println("Error: Booking not found.");
            return;
        }

        Car car = carService.findById(booking.getCarId());
        if (car != null) {
            car.setStatus(CarStatus.AVAILABLE);
            carService.save(car);
            System.out.println("Car " + car.getModel() + " successfully returned.");
        }
    }
}
