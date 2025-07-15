package org.example.lld.patterns.behavioural.observer;

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        Observer phone = new PhoneDisplay("Phone");
        Observer tv = new TVDisplay("TV");

        weatherStation.addObserver(phone);
        weatherStation.addObserver(tv);

        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);

        weatherStation.removeObserver(phone);
        weatherStation.setTemperature(28.0f);
    }
}
