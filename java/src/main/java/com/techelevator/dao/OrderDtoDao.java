package com.techelevator.dao;

import com.techelevator.model.Order;
import com.techelevator.model.OrderDto;

public interface OrderDtoDao {

    Order updateDriverStatusByOrderDtoId(OrderDto orderDto, int id);


}
