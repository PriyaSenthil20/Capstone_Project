<template>
  <div class="admin-orders">
    <form class="admin-order form">
      <div>
        <h3>Select Order</h3>
        <select v-model="selectedOrder" class="dropdown" required>
          <option disabled selected>Select an Order</option>
          <option v-for="order in adminOrders" :key="order.orderId" :value="order">
            Order #{{ order.orderId }} - {{ order.customerName }}
          </option>
        </select>
      </div>
      <div v-if="selectedOrder">
        <h3>Assign Driver</h3>
        <select v-model="selectedDriver" class="dropdown" required>
          <option disabled selected>Select a Driver</option>
          <option v-for="driver in drivers" :key="driver.driverId" :value="driver.driverId">
            {{ driver.name }}
          </option>
        </select>
        <button type="button" class="nes-btn is-primary" @click="assignDriver">
          Assign Driver
        </button>
      </div>
      <div v-if="selectedOrder">
        <h3>Update Order Status</h3>
        <select v-model="selectedStatus" class="dropdown" required>
          <option disabled selected>Select a Status</option>
          <option v-for="status in orderStatuses" :key="status.id" :value="status.id">
            {{ status.name }}
          </option>
        </select>
        <button type="button" class="nes-btn is-warning" @click="updateOrderStatus">
          Update Status
        </button>
      </div>
      <div v-if="selectedOrder">
        <h3>Order Details</h3>
        <p>Customer: {{ selectedOrder.customerName }}</p>
        <p>Delivery Option: {{ selectedOrder.transferType }}</p>
        <p>Order Total: ${{ selectedOrder.totalSale }}</p>
        <ul>
          <li v-for="item in selectedOrder.items" :key="item.id">
            {{ item.quantity }}x {{ item.productName }}
          </li>
        </ul>
      </div>
    </form>
  </div>
</template>

<script>
import AdminService from "../services/AdminService";

export default {
  data() {
    return {
      selectedOrder: null,
      selectedDriver: null,
      selectedStatus: null,
    };
  },
  computed: {
    adminOrders() {
      return this.$store.state.adminOrders;
    },
    drivers() {
      return this.$store.state.drivers;
    }
  },
  methods: {
    assignDriver() {
    },
    updateOrderStatus() { 
  },
  created() {
  }
},}
</script>
