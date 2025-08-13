package org.example.lld.practice.design_elevator_system.strategies;

import org.example.lld.practice.design_elevator_system.controllers.ElevatorRequest;
import org.example.lld.practice.design_elevator_system.enums.ElevatorDirection;
import org.example.lld.practice.design_elevator_system.models.ElevatorCar;

import java.util.Queue;

public class FCFSSchedulingStrategies implements SchedulingStrategy {
    @Override
    public int getNextFloor(ElevatorCar elevatorCar) {
        ElevatorDirection elevatorDirection = elevatorCar.getDirection();
        int currentFloor = elevatorCar.getCurrentFloor();

        Queue<ElevatorRequest> requests = elevatorCar.getRequestsQueue();

        if (requests == null || requests.isEmpty()) {
            return currentFloor;
        }

        int nextReqFloor = requests.poll().getFloor();

        if (nextReqFloor == currentFloor) return currentFloor;

        if (elevatorCar.getDirection() == ElevatorDirection.IDLE) {
            elevatorCar.setDirection(nextReqFloor > currentFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN);
        } else if (elevatorCar.getDirection() == ElevatorDirection.UP && nextReqFloor < currentFloor) {
            elevatorCar.setDirection(ElevatorDirection.DOWN);
        } else if (nextReqFloor > currentFloor) {
            elevatorCar.setDirection(ElevatorDirection.UP);
        }

        return nextReqFloor;
    }
}
