package org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.impl;

import org.example.lld.practice.design_parking_lot_system.improved_solution.models.ParkingSpot;
import org.example.lld.practice.design_parking_lot_system.improved_solution.strategies.SpotFindingStrategy;

import java.util.List;
import java.util.Optional;

public class AnyAvailableSpotStrategy implements SpotFindingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingSpot> availableSpots) {
        System.out.println("🔍 Finding any available spot...");
        return availableSpots.stream().findFirst();
    }
}
