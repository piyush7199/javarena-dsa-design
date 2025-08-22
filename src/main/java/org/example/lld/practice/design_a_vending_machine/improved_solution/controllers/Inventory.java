package org.example.lld.practice.design_a_vending_machine.improved_solution.controllers;

import org.example.lld.practice.design_a_vending_machine.improved_solution.models.Product;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Product, Integer> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, products.getOrDefault(product, 0) + quantity);
    }

    public boolean hasProduct(Product product) {
        return products.containsKey(product) && products.get(product) > 0;
    }

    public void dispenseProduct(Product product) {
        int newQuantity = products.get(product) - 1;
        products.put(product, newQuantity);
        System.out.println("Dispensing: " + product.name());
    }

    public Product getProductByName(String name) {
        return products.keySet().stream()
                .filter(p -> p.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
