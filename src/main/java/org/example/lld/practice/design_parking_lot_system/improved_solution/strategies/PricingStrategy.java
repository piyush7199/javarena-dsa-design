package org.example.lld.practice.design_parking_lot_system.improved_solution.strategies;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Ticket;

import java.time.LocalDateTime;

public interface PricingStrategy {
    double calculateFee(Ticket ticket, LocalDateTime time);
}
