package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;
    private int transferId;
    private int driverId;
    private String name;
    private String notes;
    private int totalSale;
    private Date pickUpDate;
    private LocalTime pickUpTime;
    private int statusId;


    private LocalDateTime createdTime;

    public Order() {
    }

    public Order(int orderId, int customerId, int transferId, int driverId, String name,
                 int totalSale, String notes, Date pickUpDate, LocalTime pickUpTime,
                 int statusId, LocalDateTime createdTime) {
        this.orderId = orderId;
        this.customerId=customerId;
        this.transferId=transferId;
        this.driverId=driverId;
        this.name = name;
        this.totalSale = totalSale;
        this.notes = notes;
        this.pickUpDate=pickUpDate;
        this.pickUpTime = pickUpTime;
        this.statusId = statusId;
        this.createdTime=createdTime;
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

    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
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
  //returns userId

  //getname
    public String getName() {
        return name;
    }
 //setname
    public void setName(String name) {
        this.name = name;
    }
 //return totalsale
    public int getTotalSale() {
        return totalSale;
    }
  //set totalsale
    public void setTotalSale(int totalSale) {
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
    public LocalTime getPickUpTime() {
        return pickUpTime;
    }
 //set pickuptime
    public void setPickUpTime(LocalTime pickUpTime) {
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
