package com.techelevator.model;

public class Driver {
    private int driverId;
    private String driverName;
    private boolean active;

    public Driver() {
    }

    public Driver(int driverId, String driverName, boolean active) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.active = active;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
