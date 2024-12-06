package com.techelevator.model;

import java.math.BigDecimal;

/*
This is the POJO for rows in the orders_products table,
the database table that keeps track of products on specific orders.
 */
public class OrderProduct {

    private int orderProductId;
    private int orderId;
    private int productId;
    /*
    The price of a product on a specific
      order at the time the order was placed.
     */
    private BigDecimal optionSalePrice;

    public OrderProduct() {
    }

    public int getOrderProductId() {
        return orderProductId;
    }
    public void setOrderProductId(int orderProductId) {
        this.orderProductId = orderProductId;
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
    public BigDecimal getOptionSalePrice() {
        return optionSalePrice;
    }
    public void setOptionSalePrice(BigDecimal optionSalePrice) {
        this.optionSalePrice = optionSalePrice;
    }

}