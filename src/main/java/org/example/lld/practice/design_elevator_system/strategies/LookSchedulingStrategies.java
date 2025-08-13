package org.example.lld.practice.design_elevator_system.strategies;

import org.example.lld.practice.design_elevator_system.controllers.ElevatorRequest;
import org.example.lld.practice.design_elevator_system.enums.ElevatorDirection;
import org.example.lld.practice.design_elevator_system.models.ElevatorCar;

import java.util.Queue;

public class LookSchedulingStrategies implements SchedulingStrategy {
    @Override
    public int getNextFloor(ElevatorCar elevatorCar) {

        int currentFloor = elevatorCar.getCurrentFloor();

        Queue<ElevatorRequest> requests = elevatorCar.getRequestsQueue();

        if (requests == null || requests.isEmpty()) {
            return currentFloor;
        }

        ElevatorRequest primaryRequest = requests.peek();
        int primaryFloor = primaryRequest.getFloor();

        if (currentFloor == primaryFloor) return primaryFloor;

        ElevatorDirection travelDirection = primaryFloor > currentFloor ? ElevatorDirection.UP : ElevatorDirection.DOWN;

        Integer candidate = null;
        for (ElevatorRequest req : requests) {
            int reqFloor = req.getFloor();
            if (travelDirection == ElevatorDirection.UP && reqFloor > currentFloor && reqFloor <= primaryFloor) {
                if (req.isInternalRequest() || req.getRequestDirection() == ElevatorDirection.UP) {
                    if (candidate == null || reqFloor < candidate) {
                        candidate = reqFloor;
                    }
                }
            } else if (travelDirection == ElevatorDirection.DOWN && reqFloor < currentFloor
                    && reqFloor >= primaryFloor) {
                // For downward movement, consider the request if internal or if external with direction
                // DOWN.
                if (req.isInternalRequest() || req.getRequestDirection() == ElevatorDirection.DOWN) {
                    // For a downward journey, we choose the candidate that is closest to the current floor
                    // (i.e. the largest floor less than currentFloor).
                    if (candidate == null || reqFloor > candidate) {
                        candidate = reqFloor;
                    }
                }
            }
        }

        return (candidate != null) ? candidate : primaryFloor;
    }
}
