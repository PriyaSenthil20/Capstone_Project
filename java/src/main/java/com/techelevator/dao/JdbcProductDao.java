package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    private final String selectProductSql = "SELECT product_id,product_name," +
            "product_desc,product_type_id,product_price,product_available,size_id ";

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        String sql  = selectProductSql + " FROM Products";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    @Override
    public List<Product> getAvailableProducts() {
        List<Product> products = new ArrayList<>();
        String sql  = selectProductSql + " FROM Products WHERE product_available = true";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Product product = mapRowToProduct(results);
                products.add(product);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return products;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        String sql  = selectProductSql + " FROM Products WHERE product_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                product = mapRowToProduct(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return product;
    }

    @Override
    public Product createProduct(Product product) {
        int newProductId;
        String sql  = "INSERT INTO products (product_name,product_desc,product_type_id,product_price, " +
                "product_available,size_id) " +
                "VALUES (?,?,?,?,?,?) " +
                "RETURNING product_id ";
        try {
            newProductId = jdbcTemplate.queryForObject(sql, Integer.class, product.getProductName(),
                    product.getProductDescription(), product.getProductTypeId(), product.getProductPrice(),
                    product.getAvailability(), product.getSizeId());
            return getProductById(newProductId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public Product setProductAvailabilityById(int id, boolean availability) {
        Product updatedProduct = null;
        String sql = "UPDATE products " +
                "SET product_available = ? " +
                "WHERE product_id = ?";
        try {

            //second argument in update method is just passing the opposite of what the availability currently is
            int rowsAffected = jdbcTemplate.update(sql, availability, id);
            if (rowsAffected != 1){
                throw new DaoException("DaoException");
            } else {
                updatedProduct = getProductById(id);
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedProduct;
    }

    @Override
    public Product setProductPriceById(int id, BigDecimal price) {
        Product updatedProduct = null;
        String sql = "UPDATE products " +
                "SET product_price = ? " +
                "WHERE product_id = ?";
        try {

            int rowsAffected = jdbcTemplate.update(sql, price, id);
            if (rowsAffected != 1){
                throw new DaoException("DaoException");
            } else {
                updatedProduct = getProductById(id);
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedProduct;
    }

    //Method to map a row from the Products table to a Product model POJO
    // for the back end Java logic.
    private Product mapRowToProduct(SqlRowSet rs) {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));
        product.setProductDescription(rs.getString("product_desc"));
        product.setProductTypeId(rs.getInt("product_type_id"));
        //may need to address this further for the float to big decimal conversion
        product.setProductPrice(BigDecimal.valueOf(rs.getFloat("product_price")));
        product.setAvailability(rs.getBoolean("product_available"));
        product.setSizeId(rs.getInt("size_id"));

        return product;
    }
};
