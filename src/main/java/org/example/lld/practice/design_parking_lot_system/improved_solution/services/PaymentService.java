package org.example.lld.practice.design_parking_lot_system.improved_solution.services;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Ticket;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.PricingStrategy;

import java.time.LocalDateTime;

public class PaymentService {
    private final PricingStrategy pricingStrategy;

    public PaymentService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public double processPayment(Ticket ticket) {
        double fee = pricingStrategy.calculateFee(ticket, LocalDateTime.now());
        System.out.printf("Calculating fee for ticket %s. Total: $%.2f%n", ticket.getTicketId(), fee);
        // Logic to interact with a payment gateway goes here
        System.out.println("Payment processed successfully.");
        return fee;
    }
}
