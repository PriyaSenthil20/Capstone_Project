package com.techelevator.model;

public class ProductAvailableDto {

    private int productId;
    private boolean productAvailable;

    public ProductAvailableDto() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public boolean isProductAvailable() {
        return productAvailable;
    }

    public void setProductAvailable(boolean productAvailable) {
        this.productAvailable = productAvailable;
    }
}
