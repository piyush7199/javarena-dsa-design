package org.example.lld.practice.design_elevator_system.controllers;

import org.example.lld.practice.design_elevator_system.enums.ElevatorDirection;

public class ElevatorRequest {

    private final int elevatorId; // ID of the elevator involved in the request
    private final int floor; // Floor where the request is made
    private final ElevatorDirection requestDirection; // The direction of the elevator request
    private final ElevatorController controller; // Reference to the ElevatorController to handle the request
    private final boolean isInternalRequest; // Distinguishes internal vs external requests

    public ElevatorRequest(int elevatorId, int floor, ElevatorDirection reqDirection,
                           boolean isInternal) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternal;
        this.requestDirection = reqDirection;
        this.controller = new ElevatorController();

    }

    public void execute() {
        if (isInternalRequest) {
            controller.requestFloor(elevatorId, floor);
        } else {
            controller.requestElevator(elevatorId, floor, requestDirection);
        }
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getFloor() {
        return floor;
    }

    public ElevatorDirection getRequestDirection() {
        return requestDirection;
    }

    public boolean isInternalRequest() {
        return isInternalRequest;
    }
}
