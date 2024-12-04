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
@PreAuthorize("isAuthenticated()")
public class CustomerRegisterController {
    private final CustomerDao customerDao;
    private final UserDao userDao;
    public CustomerRegisterController(CustomerDao customerDao, UserDao userDao){
        this.customerDao=customerDao;
        this.userDao=userDao;
    }
    @PreAuthorize("hasRole('ADMIN')")
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
    public void createCustomer(@Valid @RequestBody CustomerDto customer){
        try {
            if (customerDao.getCustomerById(customer.getCustomerId()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer Record already exists.");
            } else {
                customerDao.createCustomer(customer);
            }
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }
    }

    private long getCurrentUserId(Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getId();
    }
    }


