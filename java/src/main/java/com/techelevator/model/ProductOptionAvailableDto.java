package com.techelevator.model;

public class ProductOptionAvailableDto {

    private int productOptionId;
    private boolean productOptionAvailable;

    public ProductOptionAvailableDto() {
    }

    public int getProductOptionId() {
        return productOptionId;
    }

    public void setProductOptionId(int productOptionId) {
        this.productOptionId = productOptionId;
    }

    public boolean isProductOptionAvailable() {
        return productOptionAvailable;
    }

    public void setProductOptionAvailable(boolean productOptionAvailable) {
        this.productOptionAvailable = productOptionAvailable;
    }
}
