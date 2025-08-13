package org.example.lld.practice.design_elevator_system.observers;

import org.example.lld.practice.design_elevator_system.enums.ElevatorState;
import org.example.lld.practice.design_elevator_system.models.ElevatorCar;

public class ElevatorDisplay implements ElevatorObserver {
    @Override
    public void onElevatorStateChange(ElevatorCar elevator, ElevatorState state) {
        // Display the new state of the elevator
        System.out.println("Elevator " + elevator.getId() + " state changed to " + state);
    }

    @Override
    public void onElevatorFloorChange(ElevatorCar elevator, int floor) {
        // Display the elevator's movement to a new floor
        System.out.println("Elevator " + elevator.getId() + " moved to floor " + floor);
    }
}
