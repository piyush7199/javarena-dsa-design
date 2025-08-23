package org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Booking;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class InMemoryBookingRepository implements Repository<Booking, String> {
    private final Map<String, Booking> bookingDb = new HashMap<>();

    @Override
    public void save(Booking booking) {
        bookingDb.put(booking.getBookingId(), booking);
    }

    @Override
    public Booking findById(String bookingId) {
        return bookingDb.get(bookingId);
    }


    public boolean isCarBooked(String carId, LocalDateTime start, LocalDateTime end) {
        return bookingDb.values().stream()
                .filter(booking -> booking.getCarId().equals(carId))
                .anyMatch(booking -> !start.isAfter(booking.getStartDate()) && !end.isBefore(booking.getStartDate()));
    }
}
