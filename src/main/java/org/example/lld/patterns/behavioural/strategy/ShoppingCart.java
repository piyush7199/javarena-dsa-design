package org.example.lld.patterns.behavioural.strategy;

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    // Set strategy dynamically
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(double amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy selected!");
        } else {
            paymentStrategy.pay(amount);
        }
    }
}
