package org.example.lld.practice.design_parking_lot_system.improved_solution;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.SpotSize;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Ticket;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Vehicle;

import java.util.Optional;

public class EntryGate {
    private final ParkingLot parkingLot;

    public EntryGate(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Optional<Ticket> processEntry(Vehicle vehicle) {
        // Display available spots
        System.out.println("\n--- GATE INFO ---");
        System.out.printf("Available Car Spots: %d%n", parkingLot.getAvailableSpots(SpotSize.MEDIUM));
        System.out.printf("Available Motorcycle Spots: %d%n", parkingLot.getAvailableSpots(SpotSize.SMALL));

        return parkingLot.vehicleEntry(vehicle);
    }
}
