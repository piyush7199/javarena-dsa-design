package org.example.lld.practice.design_car_rental_system.naive_approach;

import java.time.LocalDate;

public record Rental(Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
}
