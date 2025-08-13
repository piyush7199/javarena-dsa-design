package org.example.lld.practice.design_elevator_system.models;

import org.example.lld.practice.design_elevator_system.controllers.ElevatorController;
import org.example.lld.practice.design_elevator_system.strategies.SchedulingStrategy;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private final String name;
    private final List<Floor> floors;
    private final int noOfFloors;
    private final ElevatorController elevatorController;

    public Building(String name, int numberOfFloors, int numberOfElevators, SchedulingStrategy schedulingStrategy) {
        this.name = name;
        this.noOfFloors = numberOfFloors;
        floors = new ArrayList<>();
        for (int i = 0; i < numberOfFloors; i++) {
            floors.add(new Floor(i));
        }

        this.elevatorController = new ElevatorController(numberOfElevators, schedulingStrategy);
    }

    public String getName() {
        return name;
    }

    public int getNumberOfFloors() {
        return noOfFloors;
    }

    public ElevatorController getElevatorController() {
        return elevatorController;
    }

    public List<Floor> getFloors() {
        return new ArrayList<>(floors);
    }
}
