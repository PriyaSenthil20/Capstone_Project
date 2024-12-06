package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Order;
import com.techelevator.model.OrderProduct;
import com.techelevator.model.Product;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcOrderProductDao implements OrderProductDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public OrderProduct getOrderProductById(int id) {
        OrderProduct orderProduct = null;
        String sql = "SELECT order_id,product_id,product_sale_price " +
                "FROM order_products " +
                "WHERE order_product_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                orderProduct = mapRowToOrderProduct(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orderProduct;
    }

    @Override
    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        int newOrderProductId;
        String sql  = "INSERT INTO order_ products (order_id, product_id, product_sale_price) " +
                "VALUES (?,?,?) " +
                "RETURNING order_product_id ";
        try {
            newOrderProductId = jdbcTemplate.queryForObject(sql, int.class, orderProduct.getOrderId(),
                    orderProduct.getProductId(),orderProduct.getOptionSalePrice());
            return getOrderProductById(newOrderProductId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
    }

    private OrderProduct mapRowToOrderProduct(SqlRowSet rs) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderProductId(rs.getInt("order_product_id"));
        orderProduct.setOrderId(rs.getInt("order_id"));
        orderProduct.setProductId(rs.getInt("product_id"));
        orderProduct.setOptionSalePrice(BigDecimal.valueOf(rs.getFloat("product_sale_price")));
        return orderProduct;
    }
}
