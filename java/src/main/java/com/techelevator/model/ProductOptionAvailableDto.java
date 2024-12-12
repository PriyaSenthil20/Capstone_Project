package com.techelevator.model;

public class ProductOptionAvailableDto {

    private int optionId;
    private boolean optionAvailable;

    public ProductOptionAvailableDto() {
    }

    public int getProductOptionId() {
        return optionId;
    }

    public void setProductOptionId(int optionId) {
        this.optionId = optionId;
    }

    public boolean isProductOptionAvailable() {
        return optionAvailable;
    }

    public void setProductOptionAvailable(boolean optionAvailable) {
        this.optionAvailable = optionAvailable;
    }
}
