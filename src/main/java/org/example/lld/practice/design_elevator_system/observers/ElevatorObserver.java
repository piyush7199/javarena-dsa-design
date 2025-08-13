package org.example.lld.practice.design_elevator_system.observers;

import org.example.lld.practice.design_elevator_system.enums.ElevatorState;
import org.example.lld.practice.design_elevator_system.models.ElevatorCar;

public interface ElevatorObserver {
    void onElevatorStateChange(ElevatorCar elevatorCar, ElevatorState elevatorState);

    void onElevatorFloorChange(ElevatorCar elevatorCar, int floor);
}
