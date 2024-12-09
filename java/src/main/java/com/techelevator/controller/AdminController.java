package com.techelevator.controller;

import com.techelevator.dao.DriverDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.dao.ProductDao;
import com.techelevator.dao.ProductOptionDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Driver;
import com.techelevator.model.Order;
import com.techelevator.model.Product;
import com.techelevator.model.ProductOption;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final DriverDao driverDao;
    private final ProductDao productDao;
    private final ProductOptionDao productOptionDao;
    private final OrderDao orderDao;

    public AdminController(DriverDao driverDao, ProductDao productDao, ProductOptionDao productOptionDao, OrderDao orderDao) {
        this.driverDao = driverDao;
        this.productDao = productDao;
        this.productOptionDao = productOptionDao;
        this.orderDao = orderDao;
    }

    @GetMapping("/drivers")
    public List<Driver> getAllDrivers() {
        try {
            return driverDao.getAllDrivers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch drivers.", e);
        }
    }

    @GetMapping("/drivers/{id}")
    public Driver getDriverById(@PathVariable int id) {
        try {
            return driverDao.getDriverById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Driver not found.", e);
        }
    }

    @PostMapping("/drivers")
    @ResponseStatus(HttpStatus.CREATED)
    public Driver createDriver(@RequestBody Driver driver) {
        try {
            return driverDao.createDriver(driver);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create driver.", e);
        }
    }

    @PutMapping("/drivers")
    public Driver updateDriver(@RequestBody Driver driver) {
        try {
            return driverDao.updateDriver(driver);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update driver.", e);
        }
    }

    @PutMapping("/products/availability")
    public Product changeProductAvailability(@RequestBody Product product) {
        try {
            return productDao.setProductAvailabilityById(product.getProductId(), product.getAvailability());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update product availability.", e);
        }
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {
        try {
            return productDao.createProduct(product);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add product.", e);
        }
    }
//  public ProductOption changeProductOptionAvailability() {}
//  public ProductOption addProductOption() {}
//  public List<Order> viewPendingOrders() {}
//  public Order changeOrderStatus() {}
//  public List<Order> getHistoricalReport() {}
//  public List<Order> searchOrders() {}


}
