package com.techelevator.model;

public class Customer {
    private int customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private String customerCity;
    private String customerState;
    private int customerZipCode;
    public Customer(){}

    public Customer(int customerId,String customerFirstName, String customerLastName, String customerAddress, String customerPhoneNumber, String customerEmail, String customerCity, String customerState, int customerZipCode) {
        this.customerId=customerId;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerZipCode = customerZipCode;

    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int userId) {
        this.customerId = userId;
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

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
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
