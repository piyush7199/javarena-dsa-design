package org.example.lld.practice.design_parking_lot_system.improved_solution.models;

import java.util.UUID;

public class ParkingSpot {
    private final String spotId;
    private final SpotSize spotSize;
    private Vehicle parkedVehicle;
    private boolean isAvailable;

    public ParkingSpot(SpotSize spotSize) {
        this.spotId = UUID.randomUUID().toString();
        this.spotSize = spotSize;
        this.parkedVehicle = null;
        this.isAvailable = true;
    }

    public String getSpotId() {
        return spotId;
    }

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.isAvailable = false;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isAvailable = true;
    }
}
