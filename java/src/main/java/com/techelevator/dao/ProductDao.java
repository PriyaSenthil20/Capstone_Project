package com.techelevator.dao;

import com.techelevator.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    List<Product> getProducts();

    List<Product> getAvailableProducts();

    List<Product> getAvailableProductsByTypeAndSize(int typeId, int sizeId);

    Product getProductById(int id);

    Product createProduct(Product product);


    public Product setProductAvailableById(int id, boolean productAvailable);

    Product setProductPriceById(int id, BigDecimal price);


}
