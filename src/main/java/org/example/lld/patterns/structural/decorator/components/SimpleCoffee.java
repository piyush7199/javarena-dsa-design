package org.example.lld.patterns.structural.decorator.components;

public class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 10.0;
    }
}
