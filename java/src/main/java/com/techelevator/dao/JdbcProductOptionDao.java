package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.ProductOption;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductOptionDao implements ProductOptionDao{
    private final JdbcTemplate jdbcTemplate;

    private final String SELECTOPTIONSQL = "SELECT option_id,option_name,option_desc," +
            "option_type_id,option_price,option_available ";

    public JdbcProductOptionDao(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate; }

    @Override
    public List<ProductOption> getProductOptions() {
        List<ProductOption> productOptions = new ArrayList<>();
        String sql  = SELECTOPTIONSQL + " FROM product_options";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ProductOption productOption = mapRowToProductOption(results);
                productOptions.add(productOption);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return productOptions;
    }

    @Override
    public List<ProductOption> getAvailableProductOptions() {
        List<ProductOption> productOptions = new ArrayList<>();
        String sql  = SELECTOPTIONSQL + " FROM product_options WHERE option_available = true";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                ProductOption productOption = mapRowToProductOption(results);
                productOptions.add(productOption);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return productOptions;
    }

    @Override
    public List<ProductOption> getAvailableOptionsByOptionTypeId(int id) {
        List<ProductOption> productOptions = new ArrayList<>();

        String sql  = SELECTOPTIONSQL + " FROM product_options WHERE option_available = true AND option_type_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            while (results.next()) {
                ProductOption productOption = mapRowToProductOption(results);
                productOptions.add(productOption);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }

        return productOptions;
    }

    @Override
    public ProductOption getProductOptionById(int id) {
        ProductOption productOption = null;
        String sql  = SELECTOPTIONSQL + " FROM product_options WHERE option_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                productOption = mapRowToProductOption(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return productOption;
    }

    @Override
    public ProductOption createProductOption(ProductOption productOption) {
        int newProductId;
        String sql  = "INSERT INTO product_options (option_name,option_desc \n" +
                "\toption_type_id,\n" +
                "\toption_price,\n" +
                "\toption_available,\n"+
                "VALUES (?,?,?,?,?,?) " +
                "RETURNING option_id ";
        try {
            newProductId = jdbcTemplate.queryForObject(sql, int.class, productOption.getOptionName(),
                    productOption.getOptionDesc(), productOption.getOptionTypeId(), productOption.getOptionPrice(),productOption.isOptionAvailable());
            return getProductOptionById(newProductId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
    }

    @Override
    public ProductOption setProductOptionAvailabilityById(int id, boolean availability) {
        ProductOption updatedOption = null;
        String sql = "UPDATE product_options " +
                "SET option_available = ? " +
                "WHERE option_id = ?";
        try {

            //second argument in update method is just passing the opposite of what the availability currently is
            int rowsAffected = jdbcTemplate.update(sql, availability, id);
            if (rowsAffected != 1){
                throw new DaoException("DaoException");
            } else {
                updatedOption = getProductOptionById(id);
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedOption;

    }

    @Override
    public ProductOption setOptionPriceById(int id, BigDecimal price) {
        ProductOption updatedOption = null;
        String sql = "UPDATE product_options " +
                "SET option_price = ? " +
                "WHERE option_id = ?";
        try {

            int rowsAffected = jdbcTemplate.update(sql, price, id);
            if (rowsAffected != 1){
                throw new DaoException("DaoException");
            } else {
                updatedOption = getProductOptionById(id);
            }

        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return updatedOption;
    } private ProductOption mapRowToProductOption(SqlRowSet rs) {
        ProductOption productOption = new ProductOption();
        productOption.setOptionId(rs.getInt("option_id"));
        productOption.setOptionName(rs.getString("option_name"));
        productOption.setOptionDesc(rs.getString("option_desc"));
        productOption.setOptionTypeId(rs.getInt("option_type_id"));
        productOption.setOptionPrice(BigDecimal.valueOf(rs.getFloat("option_price")));
        productOption.setOptionAvailable(rs.getBoolean("option_available"));
        return productOption;
    }
}
