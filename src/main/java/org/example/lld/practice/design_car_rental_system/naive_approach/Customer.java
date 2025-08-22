package org.example.lld.practice.design_car_rental_system.naive_approach;

import java.util.UUID;

public class Customer {
    private final String customerId;
    private final String name;

    public Customer(String name) {
        this.customerId = UUID.randomUUID().toString(); // Use a unique ID
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return name;
    }
}
