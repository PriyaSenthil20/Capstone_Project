<template>
  <div class="home">
    <nav class="navbar">
      <nav-options />
    </nav>
     <div>
    </div>
    <div class="content-section">      
      <admin-order/>
    </div>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import AdminOrder from '../components/AdminOrder.vue';

export default {
  components: {
    NavOptions,
    AdminOrder
  },
  data() {
    
  },
  methods: {
    getMenu(){

    }
  },
  created() {
    this.$store.dispatch('getAdminOrders');
    this.$store.dispatch('getDrivers');
    this.$store.dispatch('getOrderStatuses');
  }
};
</script>

<style scoped>
.home {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f4f4f4;
  font-family: Arial, sans-serif;
  /*
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f4f4f4;
  font-family: Arial, sans-serif;
  */
}


.navbar {
  width: 100%;
  display: bottom;
  justify-content: center;
  /*
  background-color: #e63946;
  padding: 1rem 0;
  display: flex;
  justify-content: center;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
  */
}

.welcome-section {
  width: 90%;
  max-width: 1200px;
  padding: 2rem 0;
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content-section {
  width: 90%;
  max-width: 1200px;
  display: flex;
  flex-direction: column;
  gap: 2rem;
}

.content-section > div {
  background-color: white;
  border-radius: 10px;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
}
</style>
