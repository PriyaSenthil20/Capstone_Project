package com.techelevator.controller;

import com.techelevator.dao.ProductDao;
import com.techelevator.exception.DaoException;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Customer;
import com.techelevator.model.Product;
import com.techelevator.model.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    private final ProductDao productDao;
    private final UserDao userDao;

    public ProductController(ProductDao productDao,  UserDao userDao) {
        this.productDao = productDao;
        this.userDao=userDao;
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/admin/products")
    public List<Product> getProducts(int userId){
        try {
            return productDao.getProducts();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/menu")
    public List<Product> getAvailableProducts(){
        try {
            return productDao.getAvailableProducts();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('admin')")
    public Product getProductById(int id, int userId){
        try {
            return productDao.getProductById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/admin/products")
    public Product createProduct(Product product, int userId){
        try {
            return productDao.createProduct(product);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/admin/products/availability")
    public Product setProductAvailabilityById(int id){
        try {
            return productDao.setProductAvailabilityById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/admin/products/price")
    public Product setProductPriceById(int id){

        try {
            return productDao.setProductPriceById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getCurrentUserId(Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
