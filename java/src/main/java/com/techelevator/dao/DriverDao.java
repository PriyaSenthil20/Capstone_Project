package com.techelevator.dao;

import com.techelevator.model.Driver;

import java.util.List;

public interface DriverDao {
    List<Driver> getAllDrivers();

    Driver getDriverById(int driverId);

    Driver createDriver(Driver driver);

    Driver updateDriver(Driver driver);
}
