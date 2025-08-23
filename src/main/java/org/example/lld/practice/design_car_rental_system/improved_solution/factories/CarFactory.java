package org.example.lld.practice.design_car_rental_system.improved_solution.factories;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.*;

public class CarFactory {
    public static Car createCar(String make, String model, CarType type) {
        switch (type) {
            case SEDAN:
                return new Sedan(make, model);
            case SUV:
                return new SUV(make, model);
            case TRUCK:
                return new Truck(make, model);
            default:
                throw new IllegalArgumentException("Unknown car type: " + type);
        }
    }
}

