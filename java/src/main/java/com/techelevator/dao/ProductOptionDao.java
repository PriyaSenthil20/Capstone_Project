package com.techelevator.dao;

import com.techelevator.model.ProductOption;

import java.math.BigDecimal;
import java.util.List;

public interface ProductOptionDao {
    List<ProductOption> getProductOptions();

    List<ProductOption> getAvailableProductOptions();

    List<ProductOption> getAvailableOptionsByOptionTypeId(int id);

    ProductOption getProductOptionById(int id);

    ProductOption createProductOption(ProductOption productOption);

    ProductOption setProductOptionAvailabilityById(int id, boolean availability);

    ProductOption setOptionPriceById(int id, BigDecimal price);

}
