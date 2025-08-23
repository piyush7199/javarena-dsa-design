package org.example.lld.practice.design_car_rental_system.improved_solution.models;

import java.util.UUID;

public abstract class Car {

    private final String carId;
    private final String make;
    private final String model;
    private CarStatus status;

    public Car(String make, String model) {
        this.carId = UUID.randomUUID().toString();
        this.make = make;
        this.model = model;
        this.status = CarStatus.AVAILABLE;
    }

    public String getCarId() {
        return carId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

}
