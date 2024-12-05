/**
 * The Payment class represents a payment made for an order in the system.
 * It contains payment details such as the order ID, payment type, card details,
 * and expiration date.
 */
package com.techelevator.model;

import java.time.LocalDate;

public class Payment {
    private int paymentId;
    private int orderId;
    private String paymentType;
    private String cardNumber;
    private LocalDate cardExpiration;
    private int cardCvv;
    private int cardZipcode;

    // Returns the payment ID
    public int getPaymentId() {
        return paymentId;
    }

    // Sets the payment ID
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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
    public int getCardZipcode() {
        return cardZipcode;
    }

    // Sets the card billing zip code
    public void setCardZipcode(int cardZipcode) {
        this.cardZipcode = cardZipcode;
    }
}
