package org.example.lld.patterns.creational.prototype;

public class Car implements Prototype, Cloneable {
    private String model;
    private int year;
    private Engine engine; // Nested object

    public Car(String model, int year, Engine engine) {
        this.model = model;
        this.year = year;
        this.engine = engine;
    }

    // Deep copy implementation
    @Override
    public Prototype clone() {
        try {
            Car cloned = (Car) super.clone();
            // Deep copy the nested Engine object
            cloned.engine = new Engine(this.engine.getType());
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public Engine getEngine() {
        return engine;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', year=" + year + ", engine=" + engine + "}";
    }
}
