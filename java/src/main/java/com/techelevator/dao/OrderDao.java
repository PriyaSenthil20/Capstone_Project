package com.techelevator.dao;

import com.techelevator.model.Customer;
import com.techelevator.model.CustomerDto;
import com.techelevator.model.Order;
import com.techelevator.model.OrderDto;

import java.util.List;

public interface OrderDao {
    public List<Order> getOrders();

    public Order getOrderById(int id);


    //public Order getOrderByEmail(String customerEmail);
    // may be to insert id of orderdto
    Order createOrder(OrderDto orderDto);
    Order updateOrderStatus(Order order, int id);
    Order updateDriverStatus(Order order,int id);

    Order createOrderAdmin(Order order, int currentUserId);
}
