package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDto {

    private int productId;

    private List<ProductOptionDto> productOptionDtoList = new ArrayList<ProductOptionDto>();

    public ProductDto (){

    }

    public List<ProductOptionDto> getProductOptionDtoList() {
        return productOptionDtoList;
    }

    public void setProductOptionDtoList(List<ProductOptionDto> productOptionDtoList) {
        this.productOptionDtoList = productOptionDtoList;
    }

    public int getProductIdId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }



}
