<template>
    <div class="payment-view">
      <payment-form  />
    </div>
  </template>
  
  <script>
  import PaymentForm from '../components/Payment.vue';
  
  export default {
    components: {
      PaymentForm
    }
  };
  </script>
  
  <style scoped>
  .payment-view {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    background-color: #f1f1f1;
  }
  </style>
  