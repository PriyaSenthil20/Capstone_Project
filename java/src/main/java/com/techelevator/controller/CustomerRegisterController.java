package com.techelevator.controller;

import com.techelevator.dao.CustomerDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import com.techelevator.model.CustomerDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class CustomerRegisterController {
    private final CustomerDao customerDao;
    private final UserDao userDao;
    public CustomerRegisterController(CustomerDao customerDao, UserDao userDao){
        this.customerDao=customerDao;
        this.userDao=userDao;
    }

    @GetMapping
    public List<Customer> getListOfCustomers()  {
        List<Customer> customers = null;
        try {
            customers = customerDao.getCustomers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return customers;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/customerRegister", method = RequestMethod.POST)
    public Customer createCustomer(@Valid @RequestBody CustomerDto customer){
        try {
                customerDao.createCustomer(customer);
                return customerDao.getCustomerById(customer.getCustomerId());

        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }
    }


