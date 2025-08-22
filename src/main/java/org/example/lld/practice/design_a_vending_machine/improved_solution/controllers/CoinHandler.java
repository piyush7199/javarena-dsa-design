package org.example.lld.practice.design_a_vending_machine.improved_solution.controllers;

import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Coin;

import java.util.HashMap;
import java.util.Map;

public class CoinHandler {
    private double currentBalance;
    private final Map<Coin, Integer> coinInventory;

    public CoinHandler() {
        coinInventory = new HashMap<>();
        currentBalance = 0.0;
        for (Coin coin : Coin.values()) {
            coinInventory.put(coin, 10); // Start with 10 of each coin for change
        }
    }

    public void insertCoin(Coin coin) {
        currentBalance += coin.getValue();
        coinInventory.put(coin, coinInventory.get(coin) + 1);
        System.out.println("Inserted: " + coin + ". Current balance: $" + String.format("%.2f", currentBalance));
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void refund() {
        if (currentBalance > 0) {
            System.out.println("Refunding: $" + String.format("%.2f", currentBalance));
            currentBalance = 0;
        }
    }

    public void dispenseChange(double changeAmount) {
        if (changeAmount > 0) {
            System.out.println("Dispensing change: $" + String.format("%.2f", changeAmount));
            currentBalance = 0;
        }
    }

}
