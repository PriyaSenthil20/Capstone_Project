<template>
    <div>
      <nav-options />
      </div>
      <div class="specials">
        <specials  />
      </div>
    </template>
    
    <script>
    import Specials from '../components/Specials.vue';
    import NavOptions from '../components/NavOptions.vue';
    export default {
      components: {
        NavOptions,
        Specials
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
    