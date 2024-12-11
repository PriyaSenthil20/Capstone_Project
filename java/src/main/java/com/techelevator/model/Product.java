package com.techelevator.model;

import java.math.BigDecimal;

public class Product {

    private int id;
    private String productName;
    private String productDesc;
    private int productTypeId;
    private BigDecimal productPrice = new BigDecimal(0);

    private boolean productAvailable;
    private int sizeId;

    public Product (){

    }

    public Product(int id, String productName, String productDesc,
                   int productTypeId, BigDecimal productPrice, boolean productAvailable, int sizeId){

        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productTypeId = productTypeId;
        this.productPrice = productPrice;
        this.productAvailable = productAvailable;
        this.sizeId = sizeId;
    }

    public int getProductId() {
        return id;
    }
    public void setProductId(int id) {
        this.id = id;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getproductDesc() {
        return productDesc;
    }
    public void setproductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    public int getProductTypeId() {
        return productTypeId;
    }
    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }
    public BigDecimal getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    public boolean getProductAvailable() {
        return productAvailable;
    }
    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }
    public int getSizeId() {
        return sizeId;
    }
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

}