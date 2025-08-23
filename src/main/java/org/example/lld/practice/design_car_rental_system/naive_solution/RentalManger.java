package org.example.lld.practice.design_car_rental_system.naive_solution;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RentalManger {
    private final List<Car> cars;
    private final List<User> users;
    private final List<Booking> bookings;
    private int bookingCounter;

    public RentalManger() {
        this.cars = new ArrayList<>();
        this.users = new ArrayList<>();
        this.bookings = new ArrayList<>();
        bookingCounter = 1;
    }

    public List<Car> searchCar(String location) {
        return cars.stream().filter((c -> c.isAvailable() && location.equals(c.getLocation()))).toList();
    }

    public Booking rentCar(User user, Car car, Date startDate, Date endDate) {
        if (!car.isAvailable()) {
            System.out.println("Car not available!");
            return null;
        }
        Booking booking = new Booking(bookingCounter++, user, car, startDate, endDate);
        bookings.add(booking);
        user.addBooking(booking);
        car.setAvailable(false);
        return booking;
    }

    public void returnCar(Booking booking) {
        booking.getCar().setAvailable(true);
        booking.setActive(false);
        System.out.println("Car returned successfully!");
    }

    public User registerUser(String name) {
        User user = new User(users.size() + 1, name);
        users.add(user);
        return user;
    }

    public Car addCar(String make, String model, int year, String location) {
        Car car = new Car(cars.size() + 1, make, model, year, location);
        cars.add(car);
        return car;
    }
}
