package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();

    List<Order> getPendingOrders();

    Order getOrderById(int id);


    //public Order getOrderByEmail(String customerEmail);
    // may be to insert id of orderdto
    Order createOrder(OrderDto orderDto);
    Order updateOrderStatus(OrderStatusDto order);
    Order updateOrderDriver(OrderDriverDto order);

    Order createOrderAdmin(Order order, int currentUserId);
}
