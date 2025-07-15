package org.example.lld.patterns.behavioural.strategy;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        cart.checkout(100.0);

        // Switch to PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(50.0);
    }
}
