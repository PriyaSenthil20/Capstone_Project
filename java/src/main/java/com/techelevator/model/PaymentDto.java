package com.techelevator.model;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for Payment.
 * Used for transferring payment data between the client and server.
 */
public class PaymentDto {
    private int orderId;
    private String paymentType;
    private String cardNumber;
    private LocalDate cardExpiration;
    private int cardCvv;
    private int cardZipCode;

    // Default constructor
    public PaymentDto() {}

    // Constructor with all fields
    public PaymentDto(int orderId, String paymentType, String cardNumber, LocalDate cardExpiration, int cardCvv, int cardZipCode) {
        this.orderId = orderId;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardCvv = cardCvv;
        this.cardZipCode = cardZipCode;
    }

    // Returns the associated order ID
    public int getOrderId() {
        return orderId;
    }

    // Sets the associated order ID
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    // Returns the payment type
    public String getPaymentType() {
        return paymentType;
    }

    // Sets the payment type
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    // Returns the card number
    public String getCardNumber() {
        return cardNumber;
    }

    // Sets the card number
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // Returns the card expiration date
    public LocalDate getCardExpiration() {
        return cardExpiration;
    }

    // Sets the card expiration date
    public void setCardExpiration(LocalDate cardExpiration) {
        this.cardExpiration = cardExpiration;
    }

    // Returns the card CVV
    public int getCardCvv() {
        return cardCvv;
    }

    // Sets the card CVV
    public void setCardCvv(int cardCvv) {
        this.cardCvv = cardCvv;
    }

    // Returns the card billing zip code
    public int getCardZipCode() {
        return cardZipCode;
    }

    // Sets the card billing zip code
    public void setCardZipCode(int cardZipCode) {
        this.cardZipCode = cardZipCode;
    }
}
