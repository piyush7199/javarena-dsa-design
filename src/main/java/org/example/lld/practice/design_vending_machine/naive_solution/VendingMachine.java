package org.example.lld.practice.design_vending_machine.naive_solution;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    private final Map<String, Product> products;
    private final Map<Coin, Integer> coins;
    private double currentBalance;

    public VendingMachine() {
        products = new HashMap<>();
        coins = new HashMap<>();
        currentBalance = 0.0;

        // Initialize machine with some coins
        for (Coin coin : Coin.values()) {
            coins.put(coin, 10); // each coin type has 10 units initially
        }
    }

    public void addProduct(String name, double price, int quantity) {
        products.put(name, new Product(name, price, quantity));
    }

    public void insertCoin(Coin coin) {
        currentBalance += coin.getValue();
        coins.put(coin, coins.getOrDefault(coin, 0) + 1);
        System.out.printf("Inserted %s, Current Balance: $%.2f%n", coin, currentBalance);
    }

    public void selectProduct(String productName) {
        if (!products.containsKey(productName)) {
            System.out.println("Product not found.");
            return;
        }

        Product product = products.get(productName);

        if (product.getQuantity() <= 0) {
            System.out.println("Product sold out.");
            return;
        }

        if (currentBalance < product.getPrice()) {
            System.out.printf("Insufficient funds. Please insert $%.2f more.%n",
                    product.getPrice() - currentBalance);
            return;
        }

        product.reduceQuantity();
        currentBalance -= product.getPrice();
        System.out.printf("Dispensing %s%n", product.getName());

        // Dispense change (naively return leftover balance as cash)
        if (currentBalance > 0) {
            System.out.printf("Returning change: $%.2f%n", currentBalance);
            currentBalance = 0.0;
        }
    }

    // Cancel transaction
    public void cancel() {
        if (currentBalance > 0) {
            System.out.printf("Transaction cancelled. Refunding: $%.2f%n", currentBalance);
            currentBalance = 0.0;
        } else {
            System.out.println("No money to refund.");
        }
    }
}
