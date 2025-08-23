package org.example.lld.practice.design_car_rental_system.improved_solution.services;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.User;
import org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories.InMemoryUserRepository;

public class UserService {
    private final InMemoryUserRepository userRepository;

    public UserService() {
        this.userRepository = new InMemoryUserRepository();
    }

    public User findById(String userId) {
        return userRepository.findById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
