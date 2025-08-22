package org.example.lld.practice.design_a_vending_machine.improved_solution.states;

import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Coin;
import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Product;

public class HasMoneyState implements VendingMachineState {

    private final VendingMachine machine;

    public HasMoneyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void insertCoin(Coin coin) {
        machine.getCoinHandler().insertCoin(coin);
    }

    @Override
    public void selectProduct(String productName) {
        Product product = machine.getInventory().getProductByName(productName);
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if (machine.getInventory().hasProduct(product)) {
            if (machine.getCoinHandler().getCurrentBalance() >= product.price()) {
                machine.setSelectedProduct(product);
                System.out.println("Product '" + product.name() + "' selected. Ready to dispense.");
                machine.setState(new DispensingState(machine));
            } else {
                System.out.println("Insufficient balance. Remaining: $" + String.format("%.2f", product.price() - machine.getCoinHandler().getCurrentBalance()));
            }
        } else {
            System.out.println("Product is sold out.");
            machine.setState(new SoldOutState(machine)); // Transition to SoldOutState
        }
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first.");
    }

    @Override
    public void refund() {
        machine.getCoinHandler().refund();
        machine.setState(new IdleState(machine)); // Transition back to IdleState
    }
}
