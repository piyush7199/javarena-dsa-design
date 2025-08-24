package org.example.lld.practice.design_parking_lot_system.naive_solution;

public class ParkingSpot {
    private final int id;
    private final SpotSize spotSize;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(int id, SpotSize spotSize) {
        this.id = id;
        this.spotSize = spotSize;
        isOccupied = false;
        parkedVehicle = null;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public int getId() {
        return id;
    }

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }
}
