package org.example.lld.patterns.creational.builder.product;

public class House {
    private final String roof;
    private final int windows;
    private final int doors;
    private final int walls;

    public House(HouseBuilder builder) {
        this.walls = builder.walls;
        this.roof = builder.roof;
        this.windows = builder.windows;
        this.doors = builder.doors;
    }

    @Override
    public String toString() {
        return "House{walls=" + walls + ", roof=" + roof + ", windows=" + windows + ", doors=" + doors + "}";
    }

}
