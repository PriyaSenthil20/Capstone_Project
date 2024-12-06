package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.OrderSelection;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcOrderSelectionDao implements OrderSelectionDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderSelectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public OrderSelection getOrderSelectionById(int id) {
        OrderSelection orderSelection = null;
        String sql = "SELECT order_id,product_id,option_id,product_sale_price " +
                "FROM order_selections " +
                "WHERE order_selection_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                orderSelection = mapRowToOrderSelection(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orderSelection;
    }

    @Override
    public OrderSelection createOrderSelection(OrderSelection orderSelection) {
        int newOrderSelectionId;
        String sql  = "INSERT INTO orders_selections (order_id, product_id, option_id, product_sale_price) " +
                "VALUES (?,?,?,?) " +
                "RETURNING order_product_id ";
        try {
            newOrderSelectionId = jdbcTemplate.queryForObject(sql, int.class, orderSelection.getOrderId(),
                    orderSelection.getProductId(),orderSelection.getOptionId(),
                    orderSelection.getOptionSalePrice());
            return getOrderSelectionById(newOrderSelectionId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch(DataIntegrityViolationException e){
            throw new DaoException("Data integrity violation", e);
        }
    }

    private OrderSelection mapRowToOrderSelection(SqlRowSet rs) {
        OrderSelection orderSelection = new OrderSelection();
        orderSelection.setOrderSelectionId(rs.getInt("order_selection_id"));
        orderSelection.setOrderId(rs.getInt("order_id"));
        orderSelection.setProductId(rs.getInt("product_id"));
        orderSelection.setProductId(rs.getInt("option_id"));
        orderSelection.setOptionSalePrice(BigDecimal.valueOf(rs.getFloat("option_sale_price")));
        return orderSelection;
    }

}
