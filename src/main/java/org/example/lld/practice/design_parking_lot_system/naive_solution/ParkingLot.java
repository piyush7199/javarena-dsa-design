package org.example.lld.practice.design_parking_lot_system.naive_solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final Map<Integer, ParkingSpot> spots;
    private final List<Ticket> activeTickets;
    private int ticketCounter;

    public ParkingLot(List<ParkingSpot> initialSpots) {
        spots = new HashMap<>();
        activeTickets = new ArrayList<>();
        ticketCounter = 1;
        for (ParkingSpot spot : initialSpots) {
            spots.put(spot.getId(), spot);
        }
    }

    public Ticket enterCar(Vehicle vehicle) {
        for (ParkingSpot spot : spots.values()) {
            if (!spot.isOccupied() && spot.getSpotSize().ordinal() >= vehicle.spotSize().ordinal()) {
                spot.setOccupied(true);
                spot.setParkedVehicle(vehicle);
                Ticket ticket = new Ticket(ticketCounter++, vehicle, spot);
                activeTickets.add(ticket);
                System.out.println("Vehicle " + vehicle.plateNumber() + " parked at Spot " + spot.getId());
                return ticket;
            }
        }
        System.out.println("No available spot for " + vehicle.plateNumber());
        return null;
    }

    public void exitCar(Ticket ticket) {
        long duration = System.currentTimeMillis() - ticket.getEntryTime();
        double fee = duration * 1.0;

        ParkingSpot spot = ticket.getParkingSpot();
        spot.setParkedVehicle(null);
        spot.setOccupied(false);
        activeTickets.remove(ticket);
        System.out.println("Vehicle " + ticket.getVehicle().plateNumber() + " exited. Fee: " + fee);
    }

    public int getAvailableSpots(SpotSize size) {
        int count = 0;
        for (ParkingSpot spot : spots.values()) {
            if (!spot.isOccupied() && spot.getSpotSize() == size) {
                count++;
            }
        }
        return count;
    }
}
