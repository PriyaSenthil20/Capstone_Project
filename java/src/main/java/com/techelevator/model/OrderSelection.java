package com.techelevator.model;

import java.math.BigDecimal;

/*
This is the POJO for rows in the orders_selections table, the database table that keeps track of
options, specific to products on specific orders.
 */
public class OrderSelection {

    private int orderSelectionId;
    private int orderId;
    private int productId;
    private int optionId;
    /*The price of the option for a given product on a
     specific order at the time the order was placed.
     */
    private BigDecimal optionSalePrice;

    public OrderSelection() {
    }

    public int getOrderSelectionId() {
        return orderSelectionId;
    }
    public void setOrderSelectionId(int orderSelectionId) {
        this.orderSelectionId = orderSelectionId;
    }
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public int getOptionId() {
        return optionId;
    }
    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }
    public BigDecimal getOptionSalePrice() {
        return optionSalePrice;
    }
    public void setOptionSalePrice(BigDecimal optionSalePrice) {
        this.optionSalePrice = optionSalePrice;
    }

}