package org.example.lld.practice.design_parking_lot_system.improved_solution;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.ParkingSpot;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.SpotSize;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Ticket;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.Vehicle;
import org.example.lld.practice.design_parking_lot_system.improved_solution.services.ParkingSpotMangerService;
import org.example.lld.practice.design_parking_lot_system.improved_solution.services.PaymentService;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.PricingStrategy;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.SpotFindingStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {
    private static ParkingLot instance;
    private final ParkingSpotMangerService parkingSpotMangerService;
    private final PaymentService paymentService;
    private final Map<String, Ticket> activeTickets;

    private ParkingLot(SpotFindingStrategy spotFindingStrategy, PricingStrategy pricingStrategy) {
        this.activeTickets = new HashMap<>();
        this.paymentService = new PaymentService(pricingStrategy);
        this.parkingSpotMangerService = new ParkingSpotMangerService(spotFindingStrategy);
    }

    public static synchronized ParkingLot getInstance(SpotFindingStrategy spotFindingStrategy, PricingStrategy pricingStrategy) {
        if (instance == null) {
            instance = new ParkingLot(spotFindingStrategy, pricingStrategy);
        }
        return instance;
    }

    public void addSpot(SpotSize size, int count) {
        for (int i = 0; i < count; i++) {
            parkingSpotMangerService.addSpot(new ParkingSpot(size));
        }
    }

    public Optional<Ticket> vehicleEntry(Vehicle vehicle) {
        Optional<ParkingSpot> spotOpt = parkingSpotMangerService.getAvailableSpot(vehicle.getType());
        if (spotOpt.isPresent()) {
            ParkingSpot parkingSpot = spotOpt.get();
            parkingSpot.parkVehicle(vehicle);
            Ticket ticket = new Ticket(vehicle.getLicensePlate(), parkingSpot.getSpotId());
            activeTickets.put(ticket.getTicketId(), ticket);
            System.out.printf("Vehicle entered. Ticket ID: %s, Spot ID: %s%n", ticket.getTicketId(), ticket.getSpotId());
            return Optional.of(ticket);
        } else {
            System.out.println("Parking lot is full for your vehicle type.");
            return Optional.empty();
        }
    }

    public void vehicleExit(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            System.out.println("Invalid ticket. Please contact support.");
            return;
        }

        paymentService.processPayment(ticket);
        parkingSpotMangerService.freeSpot(ticket.getSpotId());
        activeTickets.remove(ticketId);
        System.out.printf("👋 Vehicle with ticket %s exited. Spot is now free.%n", ticketId);
    }

    public long getAvailableSpots(SpotSize size) {
        return parkingSpotMangerService.getAvailableSpotsCount(size);
    }
}
