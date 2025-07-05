package org.example.lld.patterns.creational.builder;

import org.example.lld.patterns.creational.builder.product.House;
import org.example.lld.patterns.creational.builder.product.HouseBuilder;

public class BuilderPattern {
    public static void main(String[] args) {
        HouseBuilder builder = new HouseBuilder();
        House customHouse = builder.setWalls(5).setRoof("Metal").setWindows(8).setDoors(2).build();
        System.out.println("Custom House: " + customHouse);
    }
}
