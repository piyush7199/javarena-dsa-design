package org.example.lld.patterns.structural.decorator.decorators;

import org.example.lld.patterns.structural.decorator.components.Coffee;

public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.5;
    }
}
