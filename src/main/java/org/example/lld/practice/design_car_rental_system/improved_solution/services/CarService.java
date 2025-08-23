package org.example.lld.practice.design_car_rental_system.improved_solution.services;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.Car;
import org.example.lld.practice.design_car_rental_system.improved_solution.models.CarStatus;
import org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories.InMemoryCarRepository;

import java.util.List;

public class CarService {
    private final InMemoryCarRepository carRepository;

    public CarService() {
        this.carRepository = new InMemoryCarRepository();
    }

    public Car findById(String userId) {
        return carRepository.findById(userId);
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<Car> findAvailableCars() {
        return carRepository.findByStatus(CarStatus.AVAILABLE);
    }
}
