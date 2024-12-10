package com.techelevator.controller;

import com.techelevator.dao.PaymentDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Payment;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * REST controller for managing payments.
 *
 * This controller provides endpoints for fetching, creating, updating, and deleting payment information.
 */
@RestController
@CrossOrigin
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentDao paymentDao;

    // Constructs a new `PaymentController` instance with the provided `PaymentDao`
    public PaymentController(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }
   //method_12
    // Retrieves a list of all payments for a specific order

    @GetMapping("/order/{orderId}")
    public List<Payment> getPaymentsByOrderId(@PathVariable int orderId) {
        try {
            return paymentDao.getPaymentsByOrderId(orderId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_13
    // Retrieves a payment by its unique identifier

    @GetMapping("/{paymentId}")
    public Payment getPaymentById(@PathVariable int paymentId) {
        try {
            return paymentDao.getPaymentById(paymentId);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_14
    // Creates a new payment for an order

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Payment createPayment(@Valid @RequestBody Payment payment) {
        try {
            return paymentDao.createPayment(payment);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create payment.");
        }
    }
  //method_15
    // Updates an existing payment

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Payment updatePayment(@Valid @RequestBody Payment payment) {
        try {
            return paymentDao.createPayment(payment);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update payment.");
        }
    }
}
