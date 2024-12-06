package com.techelevator.dao;

import com.techelevator.model.OrderSelection;

public interface OrderSelectionDao {

    OrderSelection createOrderSelection(OrderSelection orderSelection);

    OrderSelection getOrderSelectionById(int id);
}
