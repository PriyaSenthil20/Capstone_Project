<template>
    <div class="payment-form" v-if="!submitSuccess">
      <h3>Payment Information</h3>
      <form v-on:submit.prevent="submitPayment" >
        <div class="form-group">
          <label name="orderId">Your Order Id:&nbsp;&nbsp; {{this.$store.state.orderDetails.orderId}}</label>
        </div>
        <div class="form-group">
          <label name="price">Total Payment:&nbsp;&nbsp;${{this.$store.state.orderDetails.totalSale}}</label>  
        </div>

        <div class="form-group" v-if="this.$store.state.orderDetails.transferId=='1'">
          <label for="deliveryAddress">Enter Delivery Address</label>
          <input
            type="text"
            id="deliveryAddress"
            v-model="deliveryAddress"
            required
            maxlength="100"
            placeholder="Enter Street Name and Door Number"
          />
          <label for="City">City</label>
          <input
            type="text"
            id="City"
            v-model="cityName"
            required
            maxlength="10"
            placeholder="Enter City Name"
          />
          <label for="State">State</label>
          <input
            type="text"
            id="State"
            v-model="stateName"
            required
            maxlength="2"
            placeholder="Enter State Abbreviation"
          />
        </div>
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
          <button type="submit">Submit Payment</button>
        </div>
      </form>
    </div>
    <div name="paymentConfirmationPage" v-if="submitSuccess">
      <label>Order SuccessFul</label>
      <label> Your Payment Confirmation Id: {{this.paymentConfirmation}}</label>
      <label> Enjoy Your Pizza!!!</label>
      <div v-if="this.$store.state.orderDetails.transferId===1">
        <label>Your Pizza Will be delivered to:</label>
        <p>{{this.deliveryAddress}}, <br>{{this.cityName}}, {{this.stateName}}</p>
      </div>
      <div v-if="this.$store.state.orderDetails.transferId===2">
        <label>Please Pick Your Pizza At:</label>
        <p><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rocco's Pizza Place,<br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Java Purple,<br>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NLR-2024.
        </p>
        <p>{{this.deliveryAddress}}, <br>{{this.cityName}}, {{this.stateName}}</p>
      </div>
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
          paymentType: "",
          cardNumber: "",
          cardExpiration: "",
          cardCvv: "",
          cardZipcode: ""
        },
        deliveryAddress:'',
        cityName:'',
        stateName:'',
        submitSuccess:false,
        paymentConfirmation:0
      };
    },
    methods: {
      submitPayment() {
        this.payment.orderId=this.$store.state.orderDetails.orderId;
      
        console.log("Payment submitted with data:", this.payment);
        PaymentService.submitPayment(this.payment)
        .then(response => {
        this.paymentConfirmation = response.data.paymentId;
        })
        .catch(error => {
            console.log(error);
        }); 
        alert("Payment Successful");
        this.submitSuccess=true;
        this.resetForm();
      },
   
      resetForm() {
        this.payment = {
          paymentId: null,
          orderId: null,
          paymentType: "",
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
  