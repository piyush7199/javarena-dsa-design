package org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;
import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.BillingStrategy;

import java.time.LocalDateTime;

public class LoyaltyMemberBilling implements BillingStrategy {
    private static final double DISCOUNT_RATE = 0.9;
    private final StandardBilling standardBilling = new StandardBilling();

    @Override
    public double calculateCost(Car car, LocalDateTime startDate, LocalDateTime endDate) {
        double baseCost = standardBilling.calculateCost(car, startDate, endDate);
        System.out.println("Applying 10% loyalty discount.");
        return baseCost * DISCOUNT_RATE;
    }
}
