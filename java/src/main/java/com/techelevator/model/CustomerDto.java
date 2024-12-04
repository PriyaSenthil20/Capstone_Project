package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;

public class CustomerDto {
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
    @NotEmpty
    private int customerZipCode;
    private String username;
    @JsonIgnore
    private String password;

    public CustomerDto(String customerFirstName, String customerLastName, String customerAddress, String phoneNumber, String customerEmail, String customerCity, String customerState,int customerZipCode,String username,String password) {
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.customerEmail = customerEmail;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerZipCode = customerZipCode;
        this.username=username;
        this.password=password;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
