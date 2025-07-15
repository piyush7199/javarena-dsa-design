package org.example.lld.patterns.behavioural.observer;

class TVDisplay implements Observer {
    private String name;

    public TVDisplay(String name) {
        this.name = name;
    }

    public void update(float temperature) {
        System.out.println(name + " display: Temp = " + temperature + "°C");
    }
}
