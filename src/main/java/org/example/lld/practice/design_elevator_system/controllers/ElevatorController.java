package org.example.lld.practice.design_elevator_system.controllers;

import org.example.lld.practice.design_elevator_system.enums.ElevatorDirection;
import org.example.lld.practice.design_elevator_system.models.ElevatorCar;
import org.example.lld.practice.design_elevator_system.strategies.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {
    private List<ElevatorCar> elevatorCars;
    private SchedulingStrategy schedulingStrategy;
    private int currentElevatorId;

    public ElevatorController() {
    }

    public ElevatorController(int noOfElevators, SchedulingStrategy schedulingStrategy) {
        this.elevatorCars = new ArrayList<>();
        this.schedulingStrategy = schedulingStrategy;
        for (int i = 0; i < noOfElevators; i++) {
            elevatorCars.add(new ElevatorCar(i));
        }
    }

    public void setSchedulingStrategy(SchedulingStrategy schedulingStrategy) {
        this.schedulingStrategy = schedulingStrategy;
    }

    private ElevatorCar getElevatorCarById(int eleId) {
        for (ElevatorCar elevatorCar : elevatorCars) {
            if (elevatorCar.getId() == eleId) {
                return elevatorCar;
            }
        }
        return null;
    }


    public void requestElevator(int elevatorId, int floorNumber, ElevatorDirection direction) {
        System.out.println("External request: Floor " + floorNumber + ", Direction " + direction);
        ElevatorCar selectedElevator = getElevatorCarById(elevatorId);
        if (selectedElevator != null) {
            selectedElevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, direction, false));
            System.out.println("Assigned elevator " + selectedElevator.getId() + " to floor " + floorNumber);
        } else {
            // If no suitable elevator is found
            System.out.println("No elevator available for floor " + floorNumber);
        }
    }

    public void requestFloor(int elevatorId, int floorNumber) {

        ElevatorCar elevator = getElevatorCarById(elevatorId);
        System.out.println("Internal request: Elevator " + elevator.getId() + " to floor " + floorNumber);

        ElevatorDirection direction = floorNumber > elevator.getCurrentFloor()
                ? ElevatorDirection.UP
                : ElevatorDirection.DOWN;
        // Add the request to the elevator
        elevator.addRequest(
                new ElevatorRequest(elevatorId, floorNumber, direction, true));
    }

    // Perform a simulation step by moving all elevators
    public void step() {
        // Iterate through all elevators
        for (ElevatorCar elevator : elevatorCars) {
            // Only process elevators with pending requests
            if (!elevator.getRequestsQueue().isEmpty()) {
                // Use the scheduling strategy to find the next stop
                int nextStop = schedulingStrategy.getNextFloor(elevator);


                // Move the elevator to the next stop if needed
                if (elevator.getCurrentFloor() != nextStop)
                    elevator.moveToNextStop(nextStop);
            }
        }
    }

    public List<ElevatorCar> getElevatorCars() {
        return new ArrayList<>(elevatorCars);
    }

    public SchedulingStrategy getSchedulingStrategy() {
        return schedulingStrategy;
    }

    public int getCurrentElevatorId() {
        return currentElevatorId;
    }

    public void setCurrentElevatorId(int currentElevatorId) {
        this.currentElevatorId = currentElevatorId;
    }
}
