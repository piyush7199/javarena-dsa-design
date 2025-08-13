package org.example.lld.practice.design_elevator_system.strategies;

import org.example.lld.practice.design_elevator_system.models.ElevatorCar;

public interface SchedulingStrategy {
    int getNextFloor(ElevatorCar elevatorCar);
}
