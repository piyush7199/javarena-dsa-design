package org.example.lld.patterns.behavioural.state;

public class Main {
    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();

        machine.insertCoin();
        machine.dispense();

        machine.dispense();
        machine.insertCoin();
        machine.dispense();
    }
}
