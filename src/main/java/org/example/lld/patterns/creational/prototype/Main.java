package org.example.lld.patterns.creational.prototype;

public class Main {
    public static void main(String[] args) {
        Car prototype = new Car("Sedan", 2020, new Engine("Petrol"));
        Car car1 = (Car) prototype.clone();
        car1.setModel("SUV");
        car1.setYear(2022);
        Car car2 = (Car) prototype.clone();
        car2.setModel("Hatchback");
        car2.getEngine().setType("Electric");

        // Print results
        System.out.println("Prototype: " + prototype);
        System.out.println("Car1: " + car1);
        System.out.println("Car2: " + car2);

    }
}
