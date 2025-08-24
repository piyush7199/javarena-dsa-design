package org.example.lld.practice.design_parking_lot_system.improved_solution.services;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.ParkingSpot;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.SpotSize;
import org.example.lld.practice.design_parking_lot_system.improved_solution.models.VehicleType;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.SpotFindingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingSpotMangerService {
    private final Map<String, ParkingSpot> spots;
    private final SpotFindingStrategy spotFindingStrategy;

    public ParkingSpotMangerService(SpotFindingStrategy spotFindingStrategy) {
        this.spots = new HashMap<>();
        this.spotFindingStrategy = spotFindingStrategy;
    }

    public void addSpot(ParkingSpot spot) {
        spots.put(spot.getSpotId(), spot);
    }

    public Optional<ParkingSpot> getAvailableSpot(VehicleType vehicleType) {
        SpotSize desiredSize;
        switch (vehicleType) {
            case MOTORCYCLE:
                desiredSize = SpotSize.SMALL;
                break;
            case CAR:
                desiredSize = SpotSize.MEDIUM;
                break;
            case BUS:
                desiredSize = SpotSize.LARGE;
                break;
            default:
                return Optional.empty();
        }

        List<ParkingSpot> availableSpots = spots.values().stream()
                .filter(spot -> spot.isAvailable() && spot.getSpotSize() == desiredSize)
                .toList();
        return spotFindingStrategy.findSpot(availableSpots);
    }

    public void freeSpot(String spotId) {
        ParkingSpot spot = spots.get(spotId);
        if (spot != null) {
            spot.removeVehicle();
        }
    }

    public ParkingSpot getSpotById(String spotId) {
        return spots.get(spotId);
    }

    public long getAvailableSpotsCount(SpotSize size) {
        return spots.values().stream()
                .filter(s -> s.isAvailable() && s.getSpotSize() == size)
                .count();
    }
}
