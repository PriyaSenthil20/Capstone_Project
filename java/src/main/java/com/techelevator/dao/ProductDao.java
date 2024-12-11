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

<<<<<<< HEAD
    public Product setProductAvailabilityById(int id, boolean availability);
=======
    Product setProductAvailabilityById(int id, boolean availability);
>>>>>>> 5ac39bf7191a4fc1b25abc92e306b080377db82d

    Product setProductPriceById(int id, BigDecimal price);


}
