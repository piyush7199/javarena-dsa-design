package org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories;

import org.example.lld.practice.design_car_rental_system.improved_solution.models.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements Repository<User, String> {
    private final Map<String, User> userDb = new HashMap<>();

    @Override
    public void save(User user) {
        userDb.put(user.userId(), user);
    }

    @Override
    public User findById(String userId) {
        return userDb.get(userId);
    }
}
