package com.techelevator.controller;

import com.techelevator.dao.ProductDao;
import com.techelevator.dao.ProductOptionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Customer;
import com.techelevator.model.Product;




import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ProductController {

    private final ProductDao productDao;
    private final UserDao userDao;
    private final ProductOptionDao productOptionDao;

    public ProductController(ProductDao productDao,
                             ProductOptionDao productOptionDao, UserDao userDao) {
        this.productDao = productDao;
        this.productOptionDao = productOptionDao;
        this.userDao = userDao;
    }
    //method_28
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/products")
    public List<Product> getProducts(){
        try {
            return productDao.getProducts();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //method_29
    @GetMapping("/menu")
    public List<Product> getAvailableProducts(){
        try {
            return productDao.getAvailableProducts();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_30
    @GetMapping("/orders/products/{typeId}/{sizeId}")
    public List<Product> getAvailableProductsByTypeAndSize(
            @PathVariable int typeId, @PathVariable int sizeId){
        try {
            return productDao.getAvailableProductsByTypeAndSize(typeId, sizeId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_31
    @PreAuthorize("hasRole('ADMIN')")
    public Product getProductById(int id){
        try {
            return productDao.getProductById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_32
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products")
    public Product createProduct(@RequestBody Product product){
        try {
            return productDao.createProduct(product);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_33
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/products/availability")
    public Product setProductAvailabilityById(@RequestBody Product product){
        try {
            return productDao.setProductAvailabilityById(product.getProductId(),product.getAvailability());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_34
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/products/price")
    public Product setProductPriceById(@RequestBody Product product){

        try {
            return productDao.setProductPriceById(product.getProductId(),product.getProductPrice());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_35
    private int getCurrentUserId(Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
