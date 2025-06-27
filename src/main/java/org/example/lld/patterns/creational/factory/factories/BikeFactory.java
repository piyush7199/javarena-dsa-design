package org.example.lld.patterns.creational.factory.factories;

import org.example.lld.patterns.creational.factory.products.Bike;
import org.example.lld.patterns.creational.factory.products.Vehicle;

public class BikeFactory implements VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}
