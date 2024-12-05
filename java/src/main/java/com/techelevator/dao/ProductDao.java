package com.techelevator.dao;

import com.techelevator.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    public List<Product> getProducts();

    public List<Product> getAvailableProducts();

    public Product getProductById(int id);

    public Product createProduct(Product product);

    public Product setProductAvailabilityById(int id);

    public Product setProductPriceById(int id);


}
