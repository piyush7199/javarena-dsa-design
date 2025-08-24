package org.example.lld.practice.design_parking_lot_system.improved_solution.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class Ticket {
    private final String ticketId;
    private final String licensePlate;
    private final String spotId;
    private final LocalDateTime entryTime;

    public Ticket(String licensePlate, String spotId) {
        this.ticketId = UUID.randomUUID().toString();
        this.licensePlate = licensePlate;
        this.spotId = spotId;
        this.entryTime = LocalDateTime.now();
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getSpotId() {
        return spotId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }
}
