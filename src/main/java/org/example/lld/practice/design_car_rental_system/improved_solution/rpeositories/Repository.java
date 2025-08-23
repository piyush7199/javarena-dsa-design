package org.example.lld.practice.design_car_rental_system.improved_solution.rpeositories;

public interface Repository<T, ID> {
    void save(T entity);

    T findById(ID id);
}
