<template>


      <div>
      <customer-orders />
      </div>
   
</template>

<script>


import CustomerOrders from "../components/CustomerOrder.vue";

export default {

 
    methods:{
        customerOrder(){
            this.$store.commit('CUSTOMER_ORDER',this.order)
    
        }
    },
  components: {
    CustomerOrders
}
};
</script>

