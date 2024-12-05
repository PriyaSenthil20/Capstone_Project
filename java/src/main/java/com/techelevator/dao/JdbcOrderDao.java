package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Customer;
import com.techelevator.model.Order;
import com.techelevator.model.OrderDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcOrderDao implements OrderDao {
    private final JdbcTemplate jdbcTemplate;
    private final String ORDER_SELECT = "SELECT order_id, customer_id, transfer_id, driver_id, name, notes, total_sale, pickup_date, pickup_time, status_id, created_time\n" +
            "\tFROM public.orders;";
    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = ORDER_SELECT + " FROM orders";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Order order = mapRowToOrder(results);
                orders.add(order);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return orders;
    }

    public Order getOrderById(int id) {
        Order order = null;
        String sql = ORDER_SELECT + "  FROM orders WHERE order_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
            if (results.next()) {
                order = mapRowToOrder(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return order;
    }


    //public Order getOrderByEmail(String customerEmail);
    // may be to insert id of orderdto
    public Order createOrder(Order order) {
        Order newOrder = null;
        String insertOrderSql = "INSERT INTO orders (customer_id, " +
                "transfer_id,driver_id,name," +
                "notes,total_sale," +
                "pickup_date,pickup_time,status_id,created_time) " +
                "values (?,?,?,?,?,?,?,?,?,?) " + " RETURNING order_id";

        try {
            jdbcTemplate.update(insertOrderSql, order.getCustomerId(),
                    order.getTransferId(), order.getDriverId(),
                    order.getName(), order.getNotes(), order.getTotalSale(),
                    order.getPickUpDate(), order.getPickUpTime(),
                    order.getStatusId(), order.getCreatedTime());
            return getOrderById(order.getCustomerId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public Order updateOrderStatus(Order order, int id) {
        String updateStatusSql = "UPDATE orders SET status_id=?  WHERE order_id = ?";
        /*try {

            int rowsAffected= jdbcTemplate.update(updateStatusSql, id, id);
            if (rowsAffected.next()) {
                order = mapRowToOrder(rowsAffected);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return order;*/
        Order updatedOrder = null;
        try {
            int rowsAffected = jdbcTemplate.update(updateStatusSql, order.getStatusId(), order.getOrderId());
            if (rowsAffected != 1) {
                throw new DaoException("Three things are certain: Death, Taxes and Loss of Data. Guess which has occurred.");
            } else {
                updatedOrder = getOrderById(order.getOrderId());
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return updatedOrder;
    }
   public Order updateDriverStatus(Order order,int id){
       String updateDriverSql = "UPDATE orders SET driver_id=?  WHERE order_id = ?";
       Order updatedOrder = null;
       try {
           int rowsAffected = jdbcTemplate.update(updateDriverSql, order.getDriverId(), order.getOrderId());
           if (rowsAffected != 1) {
               throw new DaoException("Three things are certain: Death, Taxes and Loss of Data. Guess which has occurred.");
           } else {
               updatedOrder = getOrderById(order.getOrderId());
           }

       } catch (CannotGetJdbcConnectionException e) {
           throw new DaoException("Unable to connect to server or database", e);
       } catch (DataIntegrityViolationException e) {
           throw new DaoException("Data integrity violation", e);
       }
       return updatedOrder;
   }



    private Order mapRowToOrder(SqlRowSet rs) {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setTransferId(rs.getInt("transfer_id"));
        order.setDriverId(rs.getInt("driver_id"));
        order.setName(rs.getString("name"));
        order.setNotes(rs.getString("notes"));
        order.setTotalSale(rs.getInt("total_sale"));
        order.setPickUpDate(rs.getDate("pickup_date"));
        order.setPickUpTime(rs.getTime("pickup_time").toLocalTime());
        order.setStatusId(rs.getInt("status_id"));
        order.setCreatedTime(LocalDateTime.from(rs.getTime("created_time").toLocalTime()));
        return order;
    }
}
