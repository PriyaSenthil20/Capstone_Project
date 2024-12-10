package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
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
public class OrderController {

    private final OrderDao orderDao;
    private final UserDao userDao;

    public OrderController(OrderDao orderDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
    }

    // method_5
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
   //method_6
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
  // method_7
    // Create a new order

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders")
    public Order createOrder(@RequestBody OrderDto orderDto) {

        try {
            return orderDao.createOrder(orderDto);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
   //method_8
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
   //method_9
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
    //method_10
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
  //method_11
    private int getCurrentUserId(Principal principal) {

        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
