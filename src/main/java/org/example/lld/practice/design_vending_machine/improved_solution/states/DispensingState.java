package org.example.lld.practice.design_vending_machine.improved_solution.states;

import org.example.lld.practice.design_vending_machine.improved_solution.models.Coin;
import org.example.lld.practice.design_vending_machine.improved_solution.models.Product;

public class DispensingState implements VendingMachineState {
    private final VendingMachine machine;

    public DispensingState(VendingMachine machine) {
        this.machine = machine;
        dispenseProduct(); // Auto-dispense upon entering this state
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void selectProduct(String productName) {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void dispenseProduct() {
        Product product = machine.getSelectedProduct();
        double change = machine.getCoinHandler().getCurrentBalance() - product.price();
        machine.getInventory().dispenseProduct(product);
        machine.getCoinHandler().dispenseChange(change);
        machine.setState(new IdleState(machine));
    }

    @Override
    public void refund() {
        System.out.println("Cannot refund during dispensing.");
    }

}
