package com.techelevator.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;
    private int transferId;
    private int driverId;
    private String notes;
    private BigDecimal totalSale;
    private String pickUpDate;
    private String pickUpTime;
    private int statusId;


    private LocalDateTime createdTime;

    public Order() {
    }

    public Order(int orderId, int customerId, int transferId, int driverId, String name,
                 BigDecimal totalSale, String notes, String pickUpDate, String pickUpTime,
                 int statusId, LocalDateTime createdTime) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.transferId = transferId;
        this.driverId = driverId;
        this.totalSale = totalSale;
        this.notes = notes;
        this.pickUpDate = pickUpDate;
        this.pickUpTime = pickUpTime;
        this.statusId = statusId;
        this.createdTime = createdTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    // Returns OrderrId
    public int getOrderId() {
        return orderId;
    }
 // set orderid
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

 //return totalsale
    public BigDecimal getTotalSale() {
        return totalSale;
    }
  //set totalsale
    public void setTotalSale(BigDecimal totalSale) {
        this.totalSale = totalSale;
    }
//returns notes
    public String getNotes() {
        return notes;
    }
  //set notes
    public void setNotes(String notes) {
        this.notes = notes;
    }
 //return pickuptime
    public String getPickUpTime() {
        return pickUpTime;
    }
 //set pickuptime
    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }
 //get statusid
    public int getStatusId() {
        return statusId;
    }
//set statusid
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
