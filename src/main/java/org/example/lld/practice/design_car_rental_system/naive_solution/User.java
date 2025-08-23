package org.example.lld.practice.design_car_rental_system.naive_solution;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final int id;
    private final String name;
    private final List<Booking> bookings;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
}
