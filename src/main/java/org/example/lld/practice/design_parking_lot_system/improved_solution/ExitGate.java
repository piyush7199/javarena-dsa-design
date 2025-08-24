package org.example.lld.practice.design_parking_lot_system.improved_solution;

public class ExitGate {
    private final ParkingLot parkingLot;

    public ExitGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public void processExit(String ticketId) {
        parkingLot.vehicleExit(ticketId);
    }
}
