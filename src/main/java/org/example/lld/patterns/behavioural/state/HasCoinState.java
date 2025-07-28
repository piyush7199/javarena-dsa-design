package org.example.lld.patterns.behavioural.state;

public class HasCoinState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void insertCoin() {
        System.out.println("Coin already inserted");
    }

    public void dispense() {
        System.out.println("Dispensing product");
        vendingMachine.setState(vendingMachine.getNoCoinState());
    }
}