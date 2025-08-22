package org.example.lld.practice.design_car_rental_system.naive_approach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalSystem {
    private final List<Car> cars;
    private final List<Customer> customers;
    private final List<Rental> rentals;

    public RentalSystem() {
        this.cars = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void rentCar(Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        if (car.isAvailable()) {
            car.setAvailability(false);
            Rental rental = new Rental(customer, car, startDate, endDate);
            rentals.add(rental);
            System.out.println("Success! " + customer.getName() + " has rented the " + car + " from " + startDate + " to " + endDate + ".");
        } else {
            System.out.println("Sorry, the " + car + " is not available for rental.");
        }
    }

    public void returnCar(Car car) {
        car.setAvailability(true);
        // In a real system, you would also remove the rental record from the list
        System.out.println("The " + car + " has been returned and is now available.");
    }

    public void displayAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println("- " + car);
            }
        }
    }

}
