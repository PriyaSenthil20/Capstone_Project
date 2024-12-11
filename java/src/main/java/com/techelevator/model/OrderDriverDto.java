package com.techelevator.model;

import jakarta.validation.constraints.Positive;

public class OrderDriverDto {

    @Positive
    private int orderId;

    @Positive
    private int driverId;

    public OrderDriverDto() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
