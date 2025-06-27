package org.example.lld.patterns.creational.factory.products;

public class Car implements Vehicle{
    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public void drive() {
        System.out.println("Driving the car");
    }
}
