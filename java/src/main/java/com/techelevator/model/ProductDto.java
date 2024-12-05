package com.techelevator.model;

import java.math.BigDecimal;

public class ProductDto {
    private String productName;
    private String productDescription;
    private int productTypeId;
    private BigDecimal productPrice = new BigDecimal(0);

    private boolean availability;
    private int sizeId;

    public ProductDto(){

    }

    public ProductDto(String productName, String productDescription,
                      int productTypeId, BigDecimal productPrice, boolean availability, int sizeId){

        this.productName = productName;
        this.productDescription = productDescription;
        this.productTypeId = productTypeId;
        this.productPrice = productPrice;
        this.availability = availability;
        this.sizeId = sizeId;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductDescription() {
        return productDescription;
    }
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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
    public boolean getAvailability() {
        return availability;
    }
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
    public int getSizeId() {
        return sizeId;
    }
    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

}