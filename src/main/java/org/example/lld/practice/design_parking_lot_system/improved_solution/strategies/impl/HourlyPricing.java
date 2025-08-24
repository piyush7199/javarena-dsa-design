package org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.impl;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Ticket;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.PricingStrategy;

import java.time.Duration;
import java.time.LocalDateTime;

public class HourlyPricing implements PricingStrategy {
    private final double rate;

    public HourlyPricing(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateFee(Ticket ticket, LocalDateTime exitTime) {
        long hours = Duration.between(ticket.getEntryTime(), exitTime).toHours();
        hours = hours == 0 ? 1 : hours;
        return hours * rate;
    }
}
