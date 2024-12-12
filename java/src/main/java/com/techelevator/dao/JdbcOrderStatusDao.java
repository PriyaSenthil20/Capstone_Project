package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.OrderStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderStatusDao implements OrderStatusDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcOrderStatusDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderStatus> getOrdersStatuses() {
        List<OrderStatus> orderStatuses = new ArrayList<>();
        String sql  = "SELECT status_id, status_type FROM order_status";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                OrderStatus product = mapRowToOrderStatus(results);
                orderStatuses.add(product);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orderStatuses;
    }

    @Override
    public OrderStatus getOrdersStatusById(int orderStatusId) {
        OrderStatus orderStatus = new OrderStatus();
        String sql  = "SELECT status_id, status_type FROM order_status WHERE status_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,orderStatusId);
            if (results.next()) {
                orderStatus = mapRowToOrderStatus(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orderStatus;
    }

    private OrderStatus mapRowToOrderStatus(SqlRowSet rs) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setStatusId(rs.getInt("status_id"));
        orderStatus.setStatusType(rs.getString("status_type"));

        return orderStatus;
    }
}
