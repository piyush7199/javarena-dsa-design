package org.example.lld.practice.design_car_rental_system.improved_solution.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final String userId;
    private final String carId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Booking(String userId, String carId, LocalDateTime startDate, LocalDateTime endDate) {
        this.bookingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCarId() {
        return carId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
