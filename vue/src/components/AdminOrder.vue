<template>
  <div class="admin-orders">


    <form class="admin-order form">

      <div>
        <h3>Select Order</h3>
        <select v-model="selectedOrder" class="dropdown" required>
          <option disabled selected>Select an Order</option>
          <option v-for="order in this.$store.state.adminOrders" :key="order.orderId" :value="order">
            Order #{{ order.orderId }} - {{ CalculateStatus(order.orderId) }}
          </option>
        </select>
      </div>

      <!--<div v-if="selectedOrder">-->
        <div>
        <h3>Assign Driver</h3>
        <select v-model="selectedDriver" class="dropdown" required>
          <option disabled selected>Select a Driver</option>
          <option v-for="driver in this.$store.state.drivers" :key="driver.driverId" :value="driver">
            {{ driver.driverName }} ID: {{ driver.driverId }}
          </option>
        </select>
        <button type="button" class="nes-btn is-primary" @click="assignDriver">
          Assign Driver
        </button>
      </div>

      <!--<div v-if="selectedOrder">-->
        <div>
        <h3>Update Order Status</h3>
        <select v-model="selectedStatus" class="dropdown" required>
          <option disabled selected>Select a Status</option>
          <option v-for="status in this.$store.state.orderStatuses" :key="status.statusId" :value="status">
            {{ status.statusType }}
          </option>
        </select>
        <button type="button" class="nes-btn is-warning" @click="updateOrderStatus">
          Update Status
        </button>
      </div>

      <div v-if="selectedOrder">
        
        <h3>Order Details</h3>
        <p>Order: {{ selectedOrder.orderId }}</p>
        <p>Driver ID: {{ selectedDriver.driverId }}</p>
        <p>Status: {{ selectedStatus.statusType }}</p>
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
      orders: [],
      selectedOrder: null,
      selectedDriver: null,
      selectedStatus: null,
      currentAdminOrder: null,
      updatedStatus: null
    };
  },  
  methods: {
    getOrders() {
      AdminService.getOrders()
      .then(response => {
        this.orders = response.data;
      })
      .catch(error => {
        console.log(error);
      })
    },
    assignDriver() {
      if (this.selectedOrder && this.selectedDriver) {
        const orderId = this.selectedOrder.orderId;
        const driverId = this.selectedDriver.driverId;
        
        const orderDriver = {
          orderId: orderId,
          driverId: driverId
        };

        AdminService.assignDriver(orderDriver)
          .then(response => {
            this.commit('SET_CURRENT_ORDER_DRIVER') 
          })
          .catch((error) => {
            console.error("Error assigning driver:", error);
          });
      } else {
        alert("Please select an order and driver.");
      }
    },
    updateOrderStatus() {
      if (this.selectedOrder && this.selectedStatus) {
        const orderId = this.selectedOrder.orderId;
        const statusId = this.selectedStatus.statusId;

        const orderStatus = {
          orderId: orderId,
          statusId: statusId
        };

        AdminService.updateOrderStatus(orderStatus)
          .then(response => {
            updatedStatus = response.data;
            this.commit('SET_CURRENT_ORDER_STATUS') 
          })
          .catch((error) => {
            console.error("Error updating order status:", error);
          });
      } else {
        alert("Please select an order and status.");
      }
    },
    getOrderDetails(order) {
      let id = order.orderId

        AdminService.getOrderById(id)
          .then(response => {
            this.currentAdminOrder = response.data;
          })
          .catch((error) => {
            console.error("Error updating order status:", error);
          });
    },
    CalculateStatus(number){
      if(number == 1){
        return "Pending"
      } else if (number == 2){
        return "Canceled"
      } else if (number == 3){
        return "Ready"
      } else if (number == 4){
        return "Complete"
      }
    }
  },
  created() {
    this.$store.dispatch("getAdminOrders");
    this.$store.dispatch("getDrivers");
    this.$store.dispatch("getOrderStatuses");
    this.getOrders();
  },

}
</script>
<style src="../styles/AdminOrderStyles.css">
</style>

