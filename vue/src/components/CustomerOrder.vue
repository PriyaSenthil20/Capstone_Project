<template>
    <div class = "customer-order">
       <h1>Order</h1>
           <div v-for="product in this.products" v-bind:key="product.productId">
               {{product.productName}}
           </div>
   </div>
</template>