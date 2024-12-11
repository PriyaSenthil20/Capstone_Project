<template>
    <div>
        <nav-options />
        </div>

      <div>
      <customer-orders />
      </div>
   
</template>

<script>


import CustomerOrders from "../components/CustomerOrder.vue";
import NavOptions from "../components/NavOptions.vue";

export default {

 
components: {
    CustomerOrders,
    NavOptions
},
created(){
       this.$store.dispatch('loadData');
    }

}

</script>

