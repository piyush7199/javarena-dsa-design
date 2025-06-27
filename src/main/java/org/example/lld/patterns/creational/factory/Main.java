package org.example.lld.patterns.creational.factory;

import org.example.lld.patterns.creational.factory.factories.BikeFactory;
import org.example.lld.patterns.creational.factory.factories.CarFactory;
import org.example.lld.patterns.creational.factory.factories.VehicleFactory;
import org.example.lld.patterns.creational.factory.products.Vehicle;

public class Main {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        Vehicle car = carFactory.createVehicle();
        System.out.println("Vehicle Type: " + car.getType());
        car.drive();

        VehicleFactory bikeFactory = new BikeFactory();
        Vehicle bike = bikeFactory.createVehicle();
        System.out.println("Vehicle Type: " + bike.getType());
        bike.drive();
    }
}
