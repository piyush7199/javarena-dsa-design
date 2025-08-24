package org.example.lld.practice.design_parking_lot_system.naive_solution;

public class Ticket {
    private final int id;
    private final Vehicle vehicle;
    private final ParkingSpot parkingSpot;
    private final long entryTime;

    public Ticket(int id, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.id = id;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public long getEntryTime() {
        return entryTime;
    }
}
