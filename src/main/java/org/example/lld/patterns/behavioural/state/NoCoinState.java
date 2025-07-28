package org.example.lld.patterns.behavioural.state;

public class NoCoinState implements VendingMachineState {
    private VendingMachine vendingMachine;

    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void insertCoin() {
        System.out.println("Coin inserted");
        vendingMachine.setState(vendingMachine.getHasCoinState());
    }

    public void dispense() {
        System.out.println("Insert a coin first");
    }
}

