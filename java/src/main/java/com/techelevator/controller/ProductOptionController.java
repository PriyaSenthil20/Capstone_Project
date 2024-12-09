package com.techelevator.controller;


import com.techelevator.dao.JdbcProductOptionDao;
import com.techelevator.dao.ProductOptionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.ProductOption;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> 77cbde6b39dacc4e4888e1c252349476f8bcb7bc
@RestController
@CrossOrigin
public class ProductOptionController {
    private final ProductOptionDao optionDao;

    private final UserDao userDao;

    public ProductOptionController(ProductOptionDao optionDao,  UserDao userDao) {
        this.optionDao = optionDao;
        this.userDao=userDao;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/options")
    public List<ProductOption> getOptions(){
        try {
            return optionDao.getProductOptions();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/options")
    public List<ProductOption> getAvailableOptions(){
        try {
            return optionDao.getAvailableProductOptions();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

<<<<<<< HEAD
=======
    @GetMapping("/options/{id}")
    public List<ProductOption> getAvailableOptionsByOptionTypeId(@PathVariable int id){
        try {
            return optionDao.getAvailableOptionsByOptionTypeId(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

>>>>>>> 77cbde6b39dacc4e4888e1c252349476f8bcb7bc
    @PreAuthorize("hasRole('ADMIN')")
    public ProductOption getProductOptionById(int id){
        try {
            return optionDao.getProductOptionById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/options")
    public ProductOption createProductOption(@RequestBody ProductOption productOption){
        try {
            return optionDao.createProductOption(productOption);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/options/availability")
    public ProductOption setProductOptionAvailabilityById(@RequestBody ProductOption productOption){
        try {
            return optionDao.setProductOptionAvailabilityById(productOption.getOptionId(),productOption.isOptionAvailable());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/options/price")
    public ProductOption setProductOptionPriceById(@RequestBody ProductOption productOption){

        try {
            return optionDao.setOptionPriceById(productOption.getOptionId(),productOption.getOptionPrice());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getCurrentUserId(Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}

