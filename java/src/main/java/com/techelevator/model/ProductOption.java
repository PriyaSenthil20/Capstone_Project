package com.techelevator.model;

import java.math.BigDecimal;

public class ProductOption {


    private int optionId;
    private String optionName;
    private String optionDesc;
    private int optionTypeId;
    private BigDecimal optionPrice= new BigDecimal(0);
    private boolean optionAvailable;
    public ProductOption(){}
    public ProductOption(int optionId, String optionName, String optionDesc, int option_type_id, BigDecimal option_price, boolean optionAvailable) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionDesc = optionDesc;
        this.optionTypeId = option_type_id;
        this.optionPrice = option_price;
        this.optionAvailable = optionAvailable;
    }

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String getOptionDesc() {
        return optionDesc;
    }

    public void setOptionDesc(String optionDesc) {
        this.optionDesc = optionDesc;
    }

    public int getOptionTypeId() {
        return optionTypeId;
    }

    public void setOptionTypeId(int option_type_id) {
        this.optionTypeId = option_type_id;
    }

    public BigDecimal getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(BigDecimal option_price) {
        this.optionPrice = option_price;
    }

    public boolean isOptionAvailable() {
        return optionAvailable;
    }

    public void setOptionAvailable(boolean optionAvailable) {
        this.optionAvailable = optionAvailable;
    }
}
