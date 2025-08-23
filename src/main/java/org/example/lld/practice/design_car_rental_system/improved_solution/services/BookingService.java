package org.example.lld.practice.design_car_rental_system.improved_solution.services;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Booking;
import org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories.InMemoryBookingRepository;

import java.time.LocalDateTime;

public class BookingService {
    private final InMemoryBookingRepository bookingRepository;

    public BookingService() {
        this.bookingRepository = new InMemoryBookingRepository();
    }

    public Booking findById(String userId) {
        return bookingRepository.findById(userId);
    }

    public boolean isCarBooked(String carId, LocalDateTime startDate, LocalDateTime endDate) {
        return bookingRepository.isCarBooked(carId, startDate, endDate);
    }

    public void save(Booking booking) {
        bookingRepository.save(booking);
    }
}
