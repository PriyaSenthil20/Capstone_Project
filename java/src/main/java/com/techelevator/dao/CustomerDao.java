package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.CustomerDto;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getCustomer();

    public Customer getCustomerById(int id);
    public Customer getCustomerByUserName(String username);

    Customer getCustomerByEmail(String customerEmail);

    Customer createCustomer(CustomerDto customer);
}
