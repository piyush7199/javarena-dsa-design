package org.example.lld.practice.design_car_rental_system.models;

import org.example.lld.practice.design_car_rental_system.enums.VehicleStatus;
import org.example.lld.practice.design_car_rental_system.enums.VehicleType;

public abstract class Vehicle {
    private String registrationNumber;
    private String make;
    private String model;
    private int yearOfManufacturing;
    private VehicleType type;
    private VehicleStatus status;
    private double baseRentalPrice;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYearOfManufacturing() {
        return yearOfManufacturing;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleStatus getStatus() {
        return status;
    }

    public double getBaseRentalPrice() {
        return baseRentalPrice;
    }

    public void setStatus(VehicleStatus status) {
        this.status = status;
    }
}
