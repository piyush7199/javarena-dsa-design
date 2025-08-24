package org.example.lld.practice.design_vending_machine.improved_solution;

import org.example.lld.practice.design_vending_machine.improved_solution.models.Coin;
import org.example.lld.practice.design_vending_machine.improved_solution.models.Product;
import org.example.lld.practice.design_vending_machine.improved_solution.states.VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

        // Setup the inventory
        Product coke = new Product("Coke", 1.50);
        Product pepsi = new Product("Pepsi", 1.25);
        vm.getInventory().addProduct(coke, 2);
        vm.getInventory().addProduct(pepsi, 1);

        System.out.println("--- Test Case 1: Successful Purchase ---");
        vm.selectProduct("Coke");      // Expecting a message to insert money
        vm.insertCoin(Coin.DOLLAR);
        vm.insertCoin(Coin.QUARTER);
        vm.insertCoin(Coin.QUARTER);
        vm.selectProduct("Coke");      // Should dispense and give change

        System.out.println("\n--- Test Case 2: Sold Out Item ---");
        vm.insertCoin(Coin.DOLLAR);
        vm.insertCoin(Coin.QUARTER);
        vm.selectProduct("Pepsi");     // Only one Pepsi, now sold out
        vm.selectProduct("Pepsi");     // Expecting a sold out message, with money still in machine
        vm.refund();                 // Get the money back
    }
}
