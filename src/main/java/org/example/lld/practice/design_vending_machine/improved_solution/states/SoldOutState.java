package org.example.lld.practice.design_vending_machine.improved_solution.states;

import org.example.lld.practice.design_vending_machine.improved_solution.models.Coin;

public class SoldOutState implements VendingMachineState {
    private final VendingMachine machine;

    public SoldOutState(VendingMachine machine) {
        this.machine = machine;
        System.out.println("Product is sold out. Please select another item or get a refund.");
    }

    @Override
    public void insertCoin(Coin coin) {
        machine.getCoinHandler().insertCoin(coin);
    }

    @Override
    public void selectProduct(String productName) {
        System.out.println("This product is sold out. Please select another.");
        // We stay in this state until a different product is selected or a refund is requested
        // The HasMoneyState logic will handle the transition
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product is sold out.");
    }

    @Override
    public void refund() {
        machine.getCoinHandler().refund();
        machine.setState(new IdleState(machine));
    }
}
