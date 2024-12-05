package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class CustomerDto {

    @Positive
    private int customerId;
    @NotEmpty
    private String customerFirstName;
    @NotEmpty

    private String customerLastName;
    @NotEmpty
    private String customerAddress;
    @NotEmpty
    private String phoneNumber;
    @NotEmpty
    private String customerEmail;
    @NotEmpty
    private String customerCity;
    @NotEmpty
    private String customerState;
    @Positive
    private int customerZipCode;


    public CustomerDto(int userId,String customerFirstName, String customerLastName, String customerAddress, String phoneNumber, String customerEmail, String customerCity, String customerState,int customerZipCode) {
        this.customerId=userId;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.customerEmail = customerEmail;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerZipCode = customerZipCode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public int getCustomerZipCode() {
        return customerZipCode;
    }

    public void setCustomerZipCode(int customerZipCode) {
        this.customerZipCode = customerZipCode;
    }
}
