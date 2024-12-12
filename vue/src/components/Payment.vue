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
          <div class="form-group"><label for="deliveryAddress">Enter Delivery Address</label>
          <input
            type="text"
            id="deliveryAddress"
            v-model="deliveryAddress"
            required
            maxlength="100"
            placeholder="Enter Street Name and Door Number"
          /></div>
          <div class="form-group">
          <label for="City">City</label>
          <input
            type="text"
            id="City"
            v-model="cityName"
            required
            maxlength="10"
            placeholder="Enter City Name"
          /></div>
          <div class="form-group">
          <label for="State">State</label>
          <input
            type="text"
            id="State"
            v-model="stateName"
            required
            maxlength="2"
            placeholder="Enter State Abbreviation"
          /></div>
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
            type="text"
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
    <div class="paymentConfirmationPage" v-if="submitSuccess" >
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
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Anthony's Drive,<br>
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
  /* Payment Form Container */
  .payment-form {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px 25px;
    background-color: #ffffff;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    font-family: 'Arial', sans-serif;
  }
  
  /* Header */
  .payment-form h3 {
    text-align: center;
    font-size: 1.5rem;
    color: #333333;
    margin-bottom: 20px;
  }
  
  /* Form Groups */
  .form-group {
    margin-bottom: 15px;
    display: flex;
    flex-direction: column;
  }
  
  /* Labels */
  label {
    font-size: 0.9rem;
    font-weight: bold;
    color: #555555;
    margin-bottom: 5px;
  }
  
  /* Inputs and Selects */
  input,
  select {
    font-size: 1rem;
    padding: 10px;
    border: 1px solid #cccccc;
    border-radius: 8px;
    outline: none;
    background-color: #f9f9f9;
    transition: border-color 0.2s ease-in-out;
  }
  
  input:focus,
  select:focus {
    border-color: #007bff;
  }
  
  /* Button */
  button {
    font-size: 1rem;
    font-weight: bold;
    color: white;
    background-color: #007bff;
    border: none;
    padding: 12px;
    border-radius: 8px;
    cursor: pointer;
    transition: background-color 0.3s ease-in-out;
  }
  
  button:hover {
    background-color: #0056b3;
  }
  
  /* Success Message */
  .paymentConfirmationPage {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    text-align: center;
    height: 100vh;
    color: white;
    background-color: #007bff;
  }
  
  .paymentConfirmationPage label {
    font-size: 1.2rem;
    font-weight: bold;
    margin: 10px 0;
  }
  
  .paymentConfirmationPage p {
    font-size: 1rem;
    margin: 10px 0;
  }
  
  /* Delivery Details */
  .form-group div {
    margin-bottom: 10px;
  }
  </style>
  