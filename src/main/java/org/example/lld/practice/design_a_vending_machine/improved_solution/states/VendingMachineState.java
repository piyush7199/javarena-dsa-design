package org.example.lld.practice.design_a_vending_machine.improved_solution.states;

import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Coin;

public interface VendingMachineState {
    void insertCoin(Coin coin);

    void selectProduct(String productName);

    void dispenseProduct();

    void refund();
}
