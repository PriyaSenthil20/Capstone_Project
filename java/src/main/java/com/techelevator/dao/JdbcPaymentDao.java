package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Payment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * JdbcPaymentDao provides database access for Payment-related operations.
 * It implements the PaymentDao interface and uses JdbcTemplate to execute SQL queries.
 * ------------------------------------------------------------------------------------
 * Key functionalities:
 * - Retrieve all payments for a specific order.
 * - Retrieve a payment by its ID.
 * - Create a new payment and return the created record.
 */
@Component
public class JdbcPaymentDao implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;
    private final String selectPaymentSql = "SELECT payment_id, order_id, payment_type, card_number, " +
            "card_expiration, card_cvv, card_zipcode ";

    public JdbcPaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Retrieves all payments for a specific order ID
    @Override
    public List<Payment> getPaymentsByOrderId(int orderId) {
        List<Payment> payments = new ArrayList<>();
        String sql = selectPaymentSql + " FROM payment WHERE order_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            while (results.next()) {
                payments.add(mapRowToPayment(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return payments;
    }

    // Retrieves a payment by its unique payment ID
    @Override
    public Payment getPaymentById(int paymentId) {
        Payment payment = null;
        String sql = selectPaymentSql + " FROM payment WHERE payment_id = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, paymentId);
            if (results.next()) {
                payment = mapRowToPayment(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return payment;
    }

    // Creates a new payment record and returns the created Payment object
    @Override
    public Payment createPayment(Payment payment) {
        String insertPaymentSql = "INSERT INTO payment (order_id, payment_type, card_number, card_expiration, " +
                "card_cvv, card_zipcode) VALUES (?, ?, ?, ?, ?, ?) RETURNING payment_id";

        try {
            Integer paymentId = jdbcTemplate.queryForObject(insertPaymentSql, Integer.class,
                    payment.getOrderId(), payment.getPaymentType(), payment.getCardNumber(),
                    payment.getCardExpiration(), payment.getCardCvv(), payment.getCardZipcode());
            if (paymentId != null) {
                return getPaymentById(paymentId);
            } else {
                throw new DaoException("Failed to create payment, payment ID is null.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
    }

    // Maps a SQL row to a Payment object
    private Payment mapRowToPayment(SqlRowSet rs) {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getInt("payment_id"));
        payment.setOrderId(rs.getInt("order_id"));
        payment.setPaymentType(rs.getString("payment_type"));
        payment.setCardNumber(rs.getString("card_number"));
        payment.setCardExpiration(rs.getDate("card_expiration").toLocalDate());
        payment.setCardCvv(rs.getInt("card_cvv"));
        payment.setCardZipcode(rs.getInt("card_zipcode"));
        return payment;
    }
}