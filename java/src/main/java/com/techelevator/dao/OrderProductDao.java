package com.techelevator.dao;

import com.techelevator.model.OrderProduct;

public interface OrderProductDao {

    OrderProduct createOrderProduct(OrderProduct orderProduct);

    OrderProduct getOrderProductById(int id);
}
