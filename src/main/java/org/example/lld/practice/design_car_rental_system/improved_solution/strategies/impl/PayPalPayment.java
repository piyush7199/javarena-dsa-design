package org.example.lld.practice.design_car_rental_system.improved_solution.strategies.impl;

import org.example.lld.practice.design_car_rental_system.improved_solution.strategies.PaymentStrategy;

public class PayPalPayment implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("💳 Processing PayPal payment of $" + String.format("%.2f", amount));
        return true;
    }
}
