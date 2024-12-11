package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.CustomerDto;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();

    Customer getCustomerById(int id);


    Customer getCustomerByEmail(String customerEmail);

    Customer createCustomer(CustomerDto customer);
}
