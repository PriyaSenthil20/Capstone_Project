package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcOrderDao implements OrderDao {
    private final JdbcTemplate jdbcTemplate;
    private final JdbcProductDao jdbcProductDao;
    private final JdbcProductOptionDao jdbcProductOptionDao;
    private final String ORDER_SELECT = "SELECT order_id, customer_id, transfer_id, driver_id, " +
            "notes, total_sale, pickup_date, pickup_time, status_id, created_time\n" +
            "\tFROM public.orders;";

    public JdbcOrderDao(JdbcTemplate jdbcTemplate, JdbcProductDao jdbcProductDao, JdbcProductOptionDao jdbcProductOptionDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcProductDao = jdbcProductDao;
        this.jdbcProductOptionDao = jdbcProductOptionDao;
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
    public Order createOrder(OrderDto orderDto, int customerId) {

        final int PENDING_STATUS = 1;
        final int DEFAULT_DRIVER_ADMIN = 2;

        Integer newOrderId;
        Order newOrder = null;
        BigDecimal totalSalePrice = new BigDecimal(0);

        String sqlOrderInsert = "INSERT INTO orders (customer_id, " +
                "transfer_id,driver_id," +
                "notes,total_sale," +
                "pickup_date,pickup_time,status_id) " +
                "values (?,?,?,?,?,?,?,?) " + " RETURNING order_id";

        for (ProductDto productDto : orderDto.getProductDtoList()) {
            BigDecimal productPrice = jdbcProductDao.getProductById(productDto.getProductId()).getProductPrice();
            totalSalePrice = totalSalePrice.add(productPrice);

            for (ProductOptionDto optionDto : productDto.getProductOptionDtoList()) {
                BigDecimal optionPrice = jdbcProductOptionDao.getProductOptionById(optionDto.getProductOptionId()).getOptionPrice();
                totalSalePrice = totalSalePrice.add(optionPrice);
            }
        }

        try {
            newOrderId = jdbcTemplate.queryForObject(sqlOrderInsert, Integer.class, customerId,
                    orderDto.getTransferId(), DEFAULT_DRIVER_ADMIN,
                    orderDto.getProductDtoList(), totalSalePrice,
                    orderDto.getPickUpDate(), orderDto.getPickUpTime(),
                    PENDING_STATUS);
            if (newOrderId != null) {

                for (ProductDto productDto : orderDto.getProductDtoList()) {
                    int productId = productDto.getProductId();
                    BigDecimal productPrice = jdbcProductDao.getProductById(productDto.getProductId()).getProductPrice();

                    //calls the method that writes an insert per product of an order
                    int orderProductId = createOrderProduct(newOrderId,productId,productPrice);

                    for (ProductOptionDto optionDto : productDto.getProductOptionDtoList()) {
                        int optionId = optionDto.getProductOptionId();
                        BigDecimal optionPrice = jdbcProductOptionDao.getProductOptionById(optionDto.getProductOptionId()).getOptionPrice();

                        //calls the method that writes an insert per option of an order's product
                        createOrderSelection(orderProductId,newOrderId,productId,optionId,optionPrice);
                    }
                }

                return getOrderById(newOrder.getOrderId());
            } else {
                throw new DaoException("Failed to create order, orderId is null.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public int createOrderProduct(int orderId, int productId, BigDecimal productPrice) {
        String sqlOrderProducts = "INSERT INTO order_products (order_id, product_id, product_sale_price) " +
                "VALUES (?, ?, ?) RETURNING order_product_id";
        try {
            Integer orderProductId = jdbcTemplate.queryForObject(sqlOrderProducts, Integer.class,
                    orderId, productId, productPrice);
            if (orderProductId == null) {
                throw new DaoException("Failed to create order product, orderProductId is null.");
            }
        return orderProductId;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    public void createOrderSelection(int orderProductId, int orderId,
                                    int productId, int optionId, BigDecimal optionSalePrice) {

        //SQL INSERT statement for inserting a relevant row for a specific order's product's selection.
        String sql = "INSERT INTO orders_selections(order_product_id, order_id, " +
                "product_id, option_id, option_sale_price) VALUES (?, ?, ?, ?, ?);";

        try {
            jdbcTemplate.update(sql, orderProductId, orderId, productId, optionId, optionSalePrice);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }


    public Order updateOrderStatus(Order order, int orderId) {
        String updateStatusSql = "UPDATE orders SET status_id=?  WHERE order_id = ?";
        Order updatedOrder = null;
        try {
            int rowsAffected = jdbcTemplate.update(updateStatusSql, order.getStatusId(), orderId);
            if (rowsAffected != 1) {
                throw new DaoException("Zero or more than one row affected.");
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
               throw new DaoException("Zero or more than one row affected.");
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

    @Override
    public Order createOrderAdmin(Order order, int currentUserId) {
        return null;
    }


    private Order mapRowToOrder(SqlRowSet rs) {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setTransferId(rs.getInt("transfer_id"));
        order.setDriverId(rs.getInt("driver_id"));
        order.setNotes(rs.getString("notes"));
        order.setTotalSale(rs.getInt("total_sale"));
        order.setPickUpDate(rs.getDate("pickup_date"));
        order.setPickUpTime(rs.getTime("pickup_time").toLocalTime());
        order.setStatusId(rs.getInt("status_id"));
        order.setCreatedTime(LocalDateTime.from(rs.getTime("created_time").toLocalTime()));
        return order;
    }
}
