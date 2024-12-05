package com.techelevator.dao;

import com.techelevator.model.ProductOption;

import java.math.BigDecimal;
import java.util.List;

public interface ProductOptionDao {
    public List<ProductOption> getProductOptions();

    public List<ProductOption> getAvailableProductOptions();

    public ProductOption getProductOptionById(int id);

    public ProductOption createProductOption(ProductOption productOption);

    public ProductOption setProductAvailabilityById(int id, boolean availability);

    public ProductOption setOptionPriceById(int id, BigDecimal price);

}
