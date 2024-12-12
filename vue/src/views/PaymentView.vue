<template>
  <div class="navOptions">
    <nav-options />
    </div>
    <div class="payment-view">
      <payment-form  />
    </div>
  </template>
  
  <script>
  import PaymentForm from '../components/Payment.vue';
  import NavOptions from '../components/NavOptions.vue';
  export default {
    components: {
      PaymentForm,
      NavOptions
    }
  };
  </script>
  
 