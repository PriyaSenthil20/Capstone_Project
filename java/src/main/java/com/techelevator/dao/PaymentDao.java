package com.techelevator.dao;

import com.techelevator.model.Payment;

import java.util.List;

/**
 * PaymentDao defines the operations for managing payment data in the system.
 * It provides methods to retrieve, create, and manage payments associated with orders.
 */
public interface PaymentDao {

    // Retrieve all payments associated with a specific order
    List<Payment> getPaymentsByOrderId(int orderId);

    // Retrieve a payment by its unique ID
    Payment getPaymentById(int paymentId);

    // Create a new payment and return the created payment
    Payment createPayment(Payment payment);
}
