package org.example.lld.practice.design_car_rental_system.improved_solution.strategies;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;

import java.time.LocalDateTime;

public interface BillingStrategy {
    double calculateCost(Car car, LocalDateTime startDate, LocalDateTime endDate);
}
