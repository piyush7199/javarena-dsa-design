package org.example.lld.practice.design_a_vending_machine.improved_solution.states;

import org.example.lld.practice.design_a_vending_machine.improved_solution.controllers.CoinHandler;
import org.example.lld.practice.design_a_vending_machine.improved_solution.controllers.Inventory;
import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Coin;
import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Product;

public class VendingMachine implements VendingMachineState {
    private VendingMachineState currentState;
    private final Inventory inventory;
    private final CoinHandler coinHandler;
    private Product selectedProduct;

    public VendingMachine() {
        this.inventory = new Inventory();
        this.coinHandler = new CoinHandler();
        this.currentState = new IdleState(this); // Initial state
    }


    // Delegation to the current state
    @Override
    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    @Override
    public void selectProduct(String productName) {
        currentState.selectProduct(productName);
    }

    @Override
    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    @Override
    public void refund() {
        currentState.refund();
    }

    public void setState(VendingMachineState newState) {
        this.currentState = newState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public CoinHandler getCoinHandler() {
        return coinHandler;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
    }

}
