package org.example.lld.practice.design_car_rental_system.naive_solution;

import java.util.Date;

public class Booking {
    private final int id;
    private final User user;
    private final Car car;
    private final Date startDate;
    private final Date endDate;
    private boolean isActive;

    Booking(int id, User user, Car car, Date startDate, Date endDate) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = true;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
