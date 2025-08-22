package org.example.lld.practice.design_car_rental_system.naive_approach;

public class Car {
    private final String licensePlate;
    private final String make;
    private final String model;
    private boolean isAvailable;

    public Car(String licensePlate, String make, String model) {
        this.licensePlate = licensePlate;
        this.make = make;
        this.model = model;
        this.isAvailable = true; // Initially, all cars are available
    }

    public void setAvailability(boolean status) {
        this.isAvailable = status;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    @Override
    public String toString() {
        return make + " " + model + " (" + licensePlate + ")";
    }
}
