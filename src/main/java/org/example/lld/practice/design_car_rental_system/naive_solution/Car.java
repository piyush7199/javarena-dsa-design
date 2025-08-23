package org.example.lld.practice.design_car_rental_system.naive_solution;

public class Car {
    private final int id;
    private final String make;
    private final String model;
    private final int year;
    private final String location;
    private boolean isAvailable;

    Car(int id, String make, String model, int year, String location) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.location = location;
        this.isAvailable = true;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLocation() {
        return location;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
