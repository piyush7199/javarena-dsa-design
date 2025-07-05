package org.example.lld.patterns.creational.builder.product;

public class HouseBuilder {
    int walls;
    String roof;
    int windows;
    int doors;

    public HouseBuilder setWalls(int walls) {
        this.walls = walls;
        return this;
    }


    public HouseBuilder setRoof(String roof) {
        this.roof = roof;
        return this;
    }


    public HouseBuilder setWindows(int windows) {
        this.windows = windows;
        return this;
    }


    public HouseBuilder setDoors(int doors) {
        this.doors = doors;
        return this;
    }


    public House build() {
        if (walls <= 0) throw new IllegalStateException("House must have walls");
        return new House(this);
    }
}
