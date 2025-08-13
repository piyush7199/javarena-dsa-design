package org.example.lld.practice.design_elevator_system.models;

import org.example.lld.practice.design_elevator_system.controllers.ElevatorRequest;
import org.example.lld.practice.design_elevator_system.enums.ElevatorDirection;
import org.example.lld.practice.design_elevator_system.enums.ElevatorState;
import org.example.lld.practice.design_elevator_system.observers.ElevatorObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorCar {
    private final int id;
    private int currentFloor;
    private ElevatorDirection direction;
    private ElevatorState elevatorState;
    private List<ElevatorObserver> elevatorObservers;
    private final Queue<ElevatorRequest> requests;

    // TODO : Add List of observers

    public ElevatorCar(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = ElevatorDirection.IDLE;
        this.elevatorState = ElevatorState.IDLE;
        this.requests = new LinkedList<>();
        this.elevatorObservers = new ArrayList<>();
    }

    public void addObserver(ElevatorObserver elevatorObserver) {
        this.elevatorObservers.add(elevatorObserver);
    }

    public void removeObserver(ElevatorObserver elevatorObserver) {
        this.elevatorObservers.remove(elevatorObserver);
    }

    private void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver elevatorObserver : elevatorObservers) {
            elevatorObserver.onElevatorStateChange(this, state);
        }
    }

    // Notify all observers about a floor change
    private void notifyFloorChange(int floor) {
        for (ElevatorObserver observer : elevatorObservers) {
            observer.onElevatorFloorChange(this, floor);
        }
    }

    public void addRequest(ElevatorRequest elevatorRequest) {
        if (!requests.contains(elevatorRequest)) {
            requests.add(elevatorRequest);
        }

        int reqFloor = elevatorRequest.getFloor();
        if (elevatorState == ElevatorState.IDLE && !requests.isEmpty()) {
            if (reqFloor > currentFloor) {
                this.direction = ElevatorDirection.UP;
            } else if (reqFloor < currentFloor) {
                this.direction = ElevatorDirection.DOWN;
            }
            setElevatorState(ElevatorState.MOVING);
        }

    }

    public void moveToNextStop(int nextStop) {
        // Only move if the elevator is currently in the MOVING state
        if (elevatorState != ElevatorState.MOVING)
            return;
        while (currentFloor != nextStop) {
            // Update floor based on direction
            if (direction == ElevatorDirection.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            // Notify observers about the floor change
            notifyFloorChange(currentFloor);
            // Complete arrival once the target floor is reached
            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }

    private void completeArrival() {
        // Stop the elevator and notify observers
        setElevatorState(ElevatorState.STOPPED);
        // Remove the current floor from the requests queue
        requests.removeIf(request -> request.getFloor() == currentFloor);
        // If no more requests, set state to IDLE
        if (requests.isEmpty()) {
            direction = ElevatorDirection.IDLE;
            setElevatorState(ElevatorState.IDLE);
        } else {
            // Otherwise, continue moving after a brief stop
            setElevatorState(ElevatorState.MOVING);
        }
    }

    public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
        notifyStateChange(elevatorState);
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorDirection getDirection() {
        return direction;
    }

    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }

    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }
}
