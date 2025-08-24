package org.example.lld.practice.design_parking_lot_system.naive_solution;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<ParkingSpot> spots = Arrays.asList(
                new ParkingSpot(1, SpotSize.SMALL),
                new ParkingSpot(2, SpotSize.MEDIUM),
                new ParkingSpot(3, SpotSize.LARGE)
        );

        ParkingLot lot = new ParkingLot(spots);

        Vehicle car1 = new Vehicle("KA-01-1234", SpotSize.MEDIUM);
        Ticket t1 = lot.enterCar(car1);
        Thread.sleep(2000); // simulate time

        lot.exitCar(t1);

        System.out.println("Available MEDIUM spots: " + lot.getAvailableSpots(SpotSize.MEDIUM));

    }
}
