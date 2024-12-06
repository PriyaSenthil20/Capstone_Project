package com.techelevator.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private int orderId;

    //customerId will be grabbed from principal

    private int transferId;

    //driverId will be auto assigned to admin userId

    //totalSale computer from joins on items and products selected
    private Date pickUpDate;

    private LocalTime pickUpTime;

    //statusId auto set to pending

    private List<ProductDto> productDtoList = new ArrayList<ProductDto>();


    public OrderDto() {

    }


    public Date getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(Date pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }
}
