package org.example.lld.practice.design_parking_lot_system.improved_solution;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Car;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.SpotSize;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Ticket;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.impl.HourlyPricing;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.impl.NearestSpotStrategy;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        ParkingLot lot = ParkingLot.getInstance(new NearestSpotStrategy(), new HourlyPricing(2.5));
        lot.addSpot(SpotSize.MEDIUM, 2);

        // Create multiple entry and exit gates
        EntryGate gate1 = new EntryGate(lot);
        EntryGate gate2 = new EntryGate(lot);
        ExitGate exitGate1 = new ExitGate(lot);

        System.out.println("Car 1 enters through Gate 1.");
        Optional<Ticket> ticket1 = gate1.processEntry(new Car("CAR-1"));

        System.out.println("\nCar 2 tries to enter through Gate 2.");
        Optional<Ticket> ticket2 = gate2.processEntry(new Car("CAR-2")); // Will be successful

        System.out.println("\nCar 3 tries to enter through Gate 1 (lot is now full).");
        gate1.processEntry(new Car("CAR-3")); // Should fail, as lot is full

        System.out.println("\nCar 1 exits through Gate 1.");
        ticket1.ifPresent(ticket -> exitGate1.processExit(ticket.getTicketId()));
    }
}
