package org.example.lld.patterns.behavioural.observer;

class PhoneDisplay implements Observer {
    private String name;

    public PhoneDisplay(String name) {
        this.name = name;
    }

    public void update(float temperature) {
        System.out.println(name + " display: Temperature is " + temperature + "°C");
    }
}