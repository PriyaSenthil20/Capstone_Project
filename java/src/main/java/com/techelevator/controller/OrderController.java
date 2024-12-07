package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Order;
import com.techelevator.model.OrderDto;
import com.techelevator.model.ProductDto;
import com.techelevator.model.ProductOptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class OrderController {

    private final OrderDao orderDao;
    private final UserDao userDao;

    public OrderController(OrderDao orderDao, UserDao userDao) {

        this.orderDao = orderDao;
        this.userDao = userDao;
    }

    // Get all orders
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("admin/orders")
    @GetMapping
    public List<Order> getAllOrdersAsAdmin() {
        try {
            return orderDao.getOrders();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get order by ID
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable int id) {
        try {
            return orderDao.getOrderById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Create a new order
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderDto orderDto, Principal principal) {

        try {
            return orderDao.createOrder(orderDto, getCurrentUserId(principal));
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("admin/orders")
    public Order createOrderAsAdmin(@RequestBody Order order, Principal principal) {
        try {
            return orderDao.createOrderAdmin(order, getCurrentUserId(principal));
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update order status
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("admin/orders/{id}/status/")
    public Order updateOrderStatusByIdAsAdmin(@PathVariable int orderId, @RequestBody Order order) {
        try {
            return orderDao.updateOrderStatus(order,orderId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update driver information for an order
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("admin/orders/{id}/driver")
    public Order updateOrderDriver(@PathVariable int orderId, @RequestBody Order order) {
        try {
            return orderDao.updateDriverStatus(order,orderId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getCurrentUserId(Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
