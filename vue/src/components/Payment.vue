<template>
    <div class="payment-form">
      <h3>Payment Information</h3>
      <form v-on:submit.prevent="submitPayment">
        <div class="form-group">
          <label for="paymentType">Payment Type</label>
          <select v-model="payment.paymentType" id="paymentType" required>
            <option value="Credit Card">Credit Card</option>
            <option value="Debit Card">Debit Card</option>
          </select>
        </div>
        <div class="form-group">
          <label for="cardNumber">Card Number</label>
          <input
            type="text"
            id="cardNumber"
            v-model="payment.cardNumber"
            required
            maxlength="16"
            placeholder="Enter Card Number"
          />
        </div>
        <div class="form-group">
          <label for="cardExpiration">Card Expiration</label>
          <input
            type="month"
            id="cardExpiration"
            v-model="payment.cardExpiration"
            required
          />
        </div>
        <div class="form-group">
          <label for="cardCvv">Card CVV</label>
          <input
            type="number"
            id="cardCvv"
            v-model="payment.cardCvv"
            required
            maxlength="3"
            placeholder="Enter CVV"
          />
        </div>
        <div class="form-group">
          <label for="cardZipcode">Card Zipcode</label>
          <input
            type="text"
            id="cardZipcode"
            v-model="payment.cardZipcode"
            required
            maxlength="5"
            placeholder="Enter Zipcode"
          />
        </div>
        <div class="form-group">
          <button type="submit" >Submit Payment</button>
        </div>
      </form>
    </div>
  </template>
  <script>
   import PaymentService from '../services/PaymentServices.js';
 
  export default {
    data() {
      return {
        payment: {
          paymentId: null,
          orderId: null,
          paymentType: "Credit Card",
          cardNumber: "",
          cardExpiration: "",
          cardCvv: "",
          cardZipcode: ""
        }
      };
    },
    methods: {
      submitPayment() {
        this.payment.orderId=this.$route.query.orderId;
        alert(this.payment.orderId);
        console.log("Payment submitted with data:", this.payment);
        PaymentService.submitPayment(this.payment)
        .then(response => {
        const paymentConfirmation = response.data;
        alert("Payment Successful"+paymentConfirmation.paymentId);
        })
        .catch(error => {
            console.log(error);
        }); 
        alert("Payment Successful");
        this.resetForm();
      },
   
      resetForm() {
        this.payment = {
          paymentId: null,
          orderId: null,
          paymentType: "Credit Card",
          cardNumber: "",
          cardExpiration: "",
          cardCvv: "",
          cardZipcode: ""
        };
      }
    }
  };
  </script>
  
  <style scoped>
  .payment-form {
    width: 300px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f4f4f4;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  h3 {
    text-align: center;
    margin-bottom: 20px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
  }
  
  input,
  select {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    border-radius: 4px;
    border: 1px solid #ccc;
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  button:disabled {
    background-color: #ccc;
  }
  
  button:hover:not(:disabled) {
    background-color: #45a049;
  }
  </style>
  