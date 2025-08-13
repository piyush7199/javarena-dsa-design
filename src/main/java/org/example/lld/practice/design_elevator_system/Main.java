package org.example.lld.practice.design_elevator_system;

import org.example.lld.practice.design_elevator_system.controllers.ElevatorController;
import org.example.lld.practice.design_elevator_system.enums.ElevatorDirection;
import org.example.lld.practice.design_elevator_system.models.Building;
import org.example.lld.practice.design_elevator_system.models.ElevatorCar;
import org.example.lld.practice.design_elevator_system.observers.ElevatorDisplay;
import org.example.lld.practice.design_elevator_system.strategies.FCFSSchedulingStrategies;
import org.example.lld.practice.design_elevator_system.strategies.LookSchedulingStrategies;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("Tower 1", 10, 3, new LookSchedulingStrategies());
        ElevatorController elevatorController = building.getElevatorController();

        ElevatorDisplay elevatorDisplay = new ElevatorDisplay();
        for (ElevatorCar elevatorCar : elevatorController.getElevatorCars()) {
            elevatorCar.addObserver(elevatorDisplay);
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Elevator System Simulation");
        System.out.println("Building: " + building.getName());
        System.out.println("Floors: " + building.getNumberOfFloors());
        System.out.println("Elevators: " + elevatorController.getElevatorCars().size());

        while (running) {
            System.out.println("nSelect an option:");
            System.out.println("1. Request elevator (external)");
            System.out.println("2. Request floor (internal)");
            System.out.println("3. Simulate next step");
            System.out.println("4. Change scheduling strategy");
            System.out.println("5. Exit simulation");
            int choice = scanner.nextInt(); // Read user's menu choice

            switch (choice) {
                case 1:
                    System.out.print("Enter elevator ID: ");
                    int externalElevatorId = scanner.nextInt();
                    elevatorController.setCurrentElevatorId(externalElevatorId); // Set the selected elevator
                    System.out.print("Enter floor number: ");
                    int floorNum = scanner.nextInt();
                    System.out.print("Direction (1 for UP, 2 for DOWN): ");
                    int dirChoice = scanner.nextInt();
                    ElevatorDirection dir = dirChoice == 1 ? ElevatorDirection.UP : ElevatorDirection.DOWN;
                    elevatorController.requestElevator(externalElevatorId, floorNum, dir);
                    break;
                case 2:
                    // Handle internal elevator floor request
                    System.out.print("Enter elevator ID: ");
                    int elevatorId = scanner.nextInt();
                    elevatorController.setCurrentElevatorId(elevatorId); // Set the selected elevator
                    System.out.print("Enter destination floor: ");
                    int destFloor = scanner.nextInt();
                    elevatorController.requestFloor(elevatorId, destFloor);
                    break;
                case 3:
                    // Simulate the next step in the system
                    System.out.println("Simulating next step...");
                    elevatorController.step(); // Perform the simulation step
                    displayElevatorStatus(elevatorController.getElevatorCars()); // Display elevator statuses
                    break;
                case 4:
                    // Change the scheduling strategy
                    System.out.println("Select strategy:");
                    System.out.println("1. SCAN Algorithm");
                    System.out.println("2. FCFS Algorithm");
                    System.out.println("3. Look Algorithm");
                    int strategyChoice = scanner.nextInt();
                    if (strategyChoice == 1) {
                        elevatorController.setSchedulingStrategy(new LookSchedulingStrategies());
                        System.out.println("Strategy set to SCAN Algorithm");
                    } else {
                        elevatorController.setSchedulingStrategy(new FCFSSchedulingStrategies());
                        System.out.println("Strategy set to Nearest Elevator First");
                    }
                    break;
                case 5:
                    // Exit the simulation
                    running = false;
                    break;
                default:
                    // Handle invalid choices
                    System.out.println("Invalid choice!");
            }
        }
    }


    private static void displayElevatorStatus(List<ElevatorCar> elevators) {
        System.out.println("nElevator Status:");
        for (ElevatorCar elevator : elevators) {
            // Print details of each elevator, including current floor, direction, and
            // state
            System.out.println("Elevator " + elevator.getId() + ": Floor "
                    + elevator.getCurrentFloor() + ", Direction "
                    + elevator.getDirection() + ", State " + elevator.getElevatorState()
                    + ", Destinations " + elevator.getDestinationFloors());
        }

    }

}
