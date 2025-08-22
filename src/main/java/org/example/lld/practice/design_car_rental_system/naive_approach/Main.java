package org.example.lld.practice.design_car_rental_system.naive_approach;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        // 1. Add some cars to the system
        Car car1 = new Car("1A2B3C4", "Toyota", "Camry");
        Car car2 = new Car("5D6E7F8", "Honda", "Accord");
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);

        // 2. Add a customer to the system
        Customer customer1 = new Customer("John Doe");
        rentalSystem.addCustomer(customer1);

        // 3. Display initially available cars
        rentalSystem.displayAvailableCars();
        // 4. Demonstrate a successful rental
        System.out.println("\n--- Demo: Renting a car ---");
        rentalSystem.rentCar(customer1, car1, LocalDate.of(2023, 10, 26), LocalDate.of(2023, 10, 30));

        // 5. Display available cars after the rental
        rentalSystem.displayAvailableCars();

        // 6. Demonstrate a failed rental (trying to rent the same car again)
        System.out.println("\n--- Demo: Attempting to rent an unavailable car ---");
        rentalSystem.rentCar(customer1, car1, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 5));

        // 7. Demonstrate returning a car
        System.out.println("\n--- Demo: Returning a car ---");
        rentalSystem.returnCar(car1);

        // 8. Display available cars after the return
        rentalSystem.displayAvailableCars();

    }
}
