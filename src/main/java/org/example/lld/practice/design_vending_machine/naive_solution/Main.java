package org.example.lld.practice.design_vending_machine.naive_solution;

public class Main {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.addProduct("Soda", 1.25, 5);
        vm.addProduct("Chips", 0.75, 3);
        vm.addProduct("Candy", 0.65, 2);

        vm.insertCoin(Coin.DOLLAR);
        vm.insertCoin(Coin.QUARTER);
        vm.selectProduct("Soda");

        vm.insertCoin(Coin.QUARTER);
        vm.selectProduct("Chips");

        vm.insertCoin(Coin.DOLLAR);
        vm.cancel();
    }
}
