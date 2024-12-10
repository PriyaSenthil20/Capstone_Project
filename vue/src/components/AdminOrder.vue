<template>
  <div class="admin-orders">
    <form class="admin-order form">
         <!-- Order Management-->
         <h3>Select order to Manage</h3>
      <div>
        <h3>Order Management</h3>
        <h3>Driver</h3>
        <select v-model="selectedProduct" class="dropdown" required>
          <option v-for="customPizza in this.$store.state.customPizzas" :key="customPizza.productId" :value="customPizza.productId">
            {{ customPizza.productDescription }}
          </option>
        </select>



        <h3>Status</h3>
        <select v-model="selectedCrust" class="dropdown" required>
          <option v-for="crust in this.$store.state.crusts" :key="crust.optionId" :value="crust.optionId">
            {{ crust.optionName }}
          </option>
        </select>

        
        <h3></h3>
        <div id="toppings" class="checkbox-group">
          <label v-for="topping in $store.state.toppings" :key="topping.optionId">
            <input type="checkbox" :value="topping.optionId" v-model="selectedToppings"  />
            {{ topping.optionName }}
          </label>
        </div>
      </div> 
       <div v-if="deliveryOption !== null && selectedPizzaType !== null">       
        <!-- Buttons -->
        <div class="action-buttons">
          <button type="button" class="add-to-cart" @click="addToCart">
            <span class="cart-icon">&#128722;</span> Add to Cart
          (
            <span class="cart-badge">{{ totalCartItems }}</span> )
          </button>
          <button type="button" class="proceed-to-checkout" @click="proceedToCheckout">
            Proceed to Checkout
          </button>
        </div>
      </div>
   
  <div v-if="showConfirmation" class="order-confirmation">
    <h2>Order Summary</h2>
    <h3>Delivery Option: {{ this.$store.state.order.transferId === 2 ? 'Pick-up' : 'Delivery' }}</h3>
    <h3>Pick-Up Date: {{ this.$store.state.order.pickUpDate }}</h3>
    <h3>Pick-Up Time: {{ this.$store.state.order.pickUpTime }}</h3>
    <h3>Items:</h3>
    <ul>
      <li v-for="(item, index) in this.$store.state.order.productDtoList" :key="index">
        <div class="product-id">
          Product ID: {{ item.productDtoList.productId }}
        </div>
        <div class="options">
          Options:
          <ul>
            <li v-for="(option, idx) in item.productDtoList.productOptionDtoList" :key="idx">
              {{ option }}
            </li>
        </ul>
        </div>
      </li>
    </ul>
    <p>Total items: {{ this.$store.state.order.productDtoList.length }}</p>
    <div class="action-buttons">
    <button type="button" class="continue" >
      Continue
    </button>
    <button type="button" class="cancel" >
      Cancel
    </button>
    </div>
    </form>
  </div>
</template>

<script>
import OrderService from '../services/OrderService';

export default {
  data() {
    const now = new Date();
    now.setMinutes(now.getMinutes() + 45);
    const defaultPickUpDate = now.toISOString().split('T')[0]; 
    const defaultPickUpTime = now.toTimeString().split(' ')[0];
    return {
      order: {
        transferId: null,
        pickUpDate: defaultPickUpDate,
        pickUpTime: defaultPickUpTime,
        productDtoList: []
      },
      time:defaultPickUpDate,
      date:defaultPickUpTime,
      selectedProduct: null,
      selectedPizzaType: null,
      selectedCrust: null,
      selectedToppings: [],
      selectedSauce: null,
      deliveryOption: null,
      showConfirmation :false,
      orderQuantity: 1,
      cart:[ {
            productId:'',
            productOptionDtoList:[]   
      }]
    };
    },
   computed: {
        totalCartItems() {
            return this.cart.length;
          }
        },

    methods: {
      selectDeliveryOption(option) {
      this.deliveryOption = option; 
      },
      selectPizza(type) {
        this.selectedPizzaType = type; 
      },
      addToCart() {
      for (let i = 0; i < this.orderQuantity; i++) {
      const product = {
        
        productDtoList: {
          productId: this.selectedProduct,
          productOptionDtoList: [this.selectedCrust, ...this.selectedToppings, this.selectedSauce]
        },
        };
        alert(product.productDtoList.productOptionDtoList[0]);
        this.cart.push(product);
        }
        alert(`Added ${this.orderQuantity} of ${this.selectedProduct} to the cart.`);
        console.log('Updated Cart:', this.cart);
        },
       
      proceedToCheckout() {
        const newOrder={
        transferId: this.deliveryOption,
        pickUpDate: this.date,
        pickUpTime: this.time,
        productDtoList:this.cart
        };
        this.order=newOrder;
        this.customerOrder();
        alert("Order successfully created!");
          this.cart = [];
          this.order = {
          transferId: null,
          pickUpDate: this.date,
          pickUpTime: this.time,
          productDtoList: []
        }},
        customerOrder() {
      this.$store.commit('CUSTOMER_ORDER', this.order);
      alert("Order successfully saved in store!");
      console.log("Order stored in Vuex:", this.$store.state.order);
      this.showConfirmation = true;

      }
      
    }
      
};
</script>
<style src="../styles/CustomerOrderStyles.css">
</style>
