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
 */
@Component
public class JdbcPaymentDao implements PaymentDao {

    private final JdbcTemplate jdbcTemplate;
    private final String selectPaymentSql = "SELECT payment_id, order_id, payment_type, card_number, " +
            "card_expiration, card_cvv, card_zipcode FROM payment";

    public JdbcPaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Retrieves all payments for a specific order ID
    @Override
    public List<Payment> getPaymentsByOrderId(int orderId) {
        List<Payment> payments = new ArrayList<>();
        String sql = selectPaymentSql + " WHERE order_id = ?";
        try {
            // Execute query to get payments for the provided orderId
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);
            while (results.next()) {
                payments.add(mapRowToPayment(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle exception if there's an issue with the database connection
            throw new DaoException("Unable to connect to server or database", e);
        }
        return payments;
    }

    // Retrieves a payment by its unique payment ID
    @Override
    public Payment getPaymentById(int paymentId) {
        Payment payment = null;
        String sql = selectPaymentSql + " WHERE payment_id = ?";
        try {
            // Execute query to get the payment for the given paymentId
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, paymentId);
            if (results.next()) {
                payment = mapRowToPayment(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle exception if there's an issue with the database connection
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
            // Insert payment details into the database and get the generated payment ID
            Integer paymentId = jdbcTemplate.queryForObject(insertPaymentSql, Integer.class,
                    payment.getOrderId(), payment.getPaymentType(), payment.getCardNumber(),
                    payment.getCardExpiration(), payment.getCardCvv(), payment.getCardZipcode());

            // Retrieve and return the created payment using the generated paymentId
            if (paymentId != null) {
                return getPaymentById(paymentId);
            } else {
                throw new DaoException("Failed to create payment, payment ID is null.");
            }
        } catch (CannotGetJdbcConnectionException e) {
            // Handle exception if there's an issue with the database connection
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            // Handle exception if there's an issue with data integrity (e.g., duplicate keys, constraint violations)
            throw new DaoException("Data integrity violation", e);
        }
    }

    // Maps a SQL row to a Payment object
    private Payment mapRowToPayment(SqlRowSet rs) {
        // Create a new Payment object and set its properties from the SQL row
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
