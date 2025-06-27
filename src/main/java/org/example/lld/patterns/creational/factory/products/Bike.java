package org.example.lld.patterns.creational.factory.products;

public class Bike implements Vehicle {
    @Override
    public String getType() {
        return "Bike";
    }

    @Override
    public void drive() {
        System.out.println("Driving the bike");
    }
}
