package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AdminController {

    private final DriverDao driverDao;
    private final ProductDao productDao;
    private final ProductOptionDao productOptionDao;
    private final OrderDao orderDao;

    private final OrderStatusDao orderStatusDao;

    public AdminController(DriverDao driverDao, ProductDao productDao,
                           ProductOptionDao productOptionDao, OrderDao orderDao,
                           OrderStatusDao orderStatusDao) {
        this.driverDao = driverDao;
        this.productDao = productDao;
        this.productOptionDao = productOptionDao;
        this.orderDao = orderDao;
        this.orderStatusDao = orderStatusDao;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/drivers")
    public List<Driver> getAllDrivers() {
        try {
            return driverDao.getAllDrivers();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch drivers.", e);
        }
    }

    @GetMapping("admin/statuses")
    public List<OrderStatus> getAllOrderStatuses() {
        try {
            return orderStatusDao.getOrdersStatuses();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
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
            return productDao.setProductAvailableById(product.getProductId(), product.getProductAvailable());
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
    @PutMapping("/product-options/availability")
    public ProductOption changeProductOptionAvailability(@RequestBody ProductOption productOption) {
        try {
            return productOptionDao.setProductOptionAvailabilityById(productOption.getOptionId(), productOption.isOptionAvailable());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update product option availability.", e);
        }
    }
    @PostMapping("/product-options")
    public ProductOption addProductOption(@RequestBody ProductOption productOption) {
        try {
            return productOptionDao.createProductOption(productOption);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to add product option.", e);
        }
    }
    @GetMapping("/orders/pending")
    public List<Order> viewPendingOrders() {
        try {
            return orderDao.getOrders(); // Modify to filter for pending orders if needed.
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch pending orders.", e);
        }
    }
    @PutMapping("/orders/{id}/status")
    public Order changeOrderStatus(@PathVariable int id, @RequestBody Order order) {
        try {
            return orderDao.updateOrderStatus(order, id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update order status.", e);
        }
    }
//  public List<Order> getHistoricalReport() {}
//  public List<Order> searchOrders() {}


}
