package com.techelevator.dao;

import com.techelevator.model.OrderStatus;

import java.util.List;

public interface OrderStatusDao {

    List<OrderStatus> getOrdersStatuses();


    OrderStatus getOrdersStatusById(int orderStatusId);
}
