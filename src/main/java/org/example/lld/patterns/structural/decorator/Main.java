package org.example.lld.patterns.structural.decorator;

import org.example.lld.patterns.structural.decorator.components.Coffee;
import org.example.lld.patterns.structural.decorator.components.SimpleCoffee;
import org.example.lld.patterns.structural.decorator.decorators.MilkDecorator;
import org.example.lld.patterns.structural.decorator.decorators.SugarDecorator;

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " $" + coffee.getCost());
    }
}
