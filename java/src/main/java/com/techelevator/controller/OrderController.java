package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/orders")
@PreAuthorize("isAuthenticated()")
public class OrderController {

    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    // Get all orders
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Order> getAllOrders() {
        try {
            return orderDao.getOrders();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get order by ID
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        try {
            return orderDao.createOrder(order);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new order
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return null;
    }

    // Update order status
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public Order updateOrderStatus(@PathVariable int id, @RequestBody Order order) {
        return null;
    }

    // Update driver information for an order
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/driver")
    public Order updateDriverStatus(@PathVariable int id, @RequestBody Order order) {
        return null;
    }
}
