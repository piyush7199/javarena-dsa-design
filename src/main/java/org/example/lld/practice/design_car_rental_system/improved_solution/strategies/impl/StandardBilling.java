package org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.BillingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class StandardBilling implements BillingStrategy {
    private static final double HOURLY_RATE = 15.0;

    @Override
    public double calculateCost(Car car, LocalDateTime startDate, LocalDateTime endDate) {
        long hours = Duration.between(startDate, endDate).toHours();
        System.out.println("Using standard billing strategy.");
        return hours * HOURLY_RATE;
    }
}
