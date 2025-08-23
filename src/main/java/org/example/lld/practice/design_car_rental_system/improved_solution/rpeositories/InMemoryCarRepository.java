package org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.CarStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryCarRepository implements Repository<Car, String> {
    private final Map<String, Car> carDb = new HashMap<>();

    @Override
    public void save(Car Car) {
        carDb.put(Car.getCarId(), Car);
    }

    @Override
    public Car findById(String carId) {
        return carDb.get(carId);
    }

    public List<Car> findByStatus(CarStatus status) {
        return carDb.values().stream()
                .filter(car -> car.getStatus() == status)
                .collect(Collectors.toList());
    }
}
