<template>
  <div class="customer-products">
    <form class="customer-order" v-on:submit.prevent="customerOrder()">
      <!-- Pizza Selection -->
      <ul class="pizza-list">
        <li>
          <h3>Custom Pizza</h3>
          <div class="pizza-img-container" @click="selectPizza('custom')">
            <img src="../assets/PizzaPic.png" id="custom-pizza" value="custom-pizza" 
            class="pizza-img"  :class="{ 'selected': selectedPizzaType === 'custom' }"/>

          </div>
        </li>
        <li>
          <h3>Specialty Pizza</h3>
          <div class="pizza-img-container" @click="selectPizza('specialty')">
            <img src="../assets/PizzaPic.png" id="specialty-pizza" value="specialty-pizza" 
            class="pizza-img"  :class="{ 'selected': selectedPizzaType === 'specialty' }"/>
          </div>
        </li>
      </ul>

                      <!-- Delivery Option -->
        <div class="delivery-options">
          <h3>Delivery Option</h3>
          <label>
            <input type="radio" v-model="deliveryOption" value="2" />
            Pick-up
          </label>
          <label>
            <input type="radio" v-model="deliveryOption" value="1" />
            Delivery
          </label>
        </div>

      <!-- Pizza Options -->
      <div class="pizza-options">
        
        <h3>Custom Pizza</h3>
        <select v-model="selectedProduct" id="custom" class="dropdown" 
        :disabled="isCustomDisabled" required>
          <option v-for="customPizza in this.$store.state.customPizzas" 
          :key="customPizza.productTypeId" :value="customPizza.productId">
            {{ customPizza.productName }}
          </option>
        </select>

        <h3>Specialty Pizza</h3>
        <select v-model="selectedProduct" id="specialty" class="dropdown" 
        :disabled="isSpecialtyDisabled">
          <option v-for="specialtyPizza in this.$store.state.specialtyPizzas" 
          :key="specialtyPizza.productTypeId" :value="specialtyPizza.productId">
            {{ specialtyPizza.productName }}
          </option>
        </select>

        <h3>Crust</h3>
        <select v-model="selectedCrust" id="crust" class="dropdown" 
        :disabled="isCrustDisabled" required>
          <option v-for="crust in this.$store.state.crusts" 
          :key="crust.optionId" :value="crust.optionId">{{ crust.optionName }}</option>
        </select>

        <h3>Topping</h3>
        <select v-model="selectedTopping" id="toppings" class="dropdown" 
        :disabled="isToppingDisabled" required>
          <option v-for="topping in this.$store.state.toppings" 
          :key="topping.optionId" :value="topping.optionId">{{ topping.optionName }}</option>
        </select>

        <h3>Sauce</h3>
        <select v-model="selectedSauce" id="sauces" class="dropdown" 
        :disabled="isSauceDisabled" required>
          <option v-for="sauce in this.$store.state.sauces" 
          :key="sauce.optionId" :value="sauce.optionId">{{ sauce.optionName }}</option>
        </select>

        <!-- Quantity Input -->
        <h3>Quantity</h3>
        <input class="quantity" name="quantity" type="number" 
        v-model="orderQuantity" placeholder="Quantity" min="1"/>

        <!-- Buttons -->
        <div class="action-buttons">
          <button type="button" class="add-to-cart" @click="addToCart">
            <span class="cart-icon">&#128722;</span> Add to Cart
          </button>
          <button type="button" class="proceed-to-checkout" @click="proceedToCheckout">
            Proceed to Checkout
          </button>
        </div>
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
      selectedTopping: null,
      selectedSauce: null,
      deliveryOption: null,
      orderQuantity: 1,
      cart:[ {
            productId:'',
            productOptionDtoList:[]   
      }]
    };
    },
    computed: {
    isSpecialtyDisabled() {
      return this.selectedPizzaType !== 'specialty';
    },
    isCrustDisabled() {
      return this.selectedPizzaType === 'specialty';
    },
    isToppingDisabled() {
      return this.selectedPizzaType === 'specialty';
    },
    isSauceDisabled() {
      return this.selectedPizzaType === 'specialty';
    },
    isCustomDisabled() {
      return this.selectedPizzaType !== 'custom';
    },
    cartItemCount() {
      return this.cart.length;
    }
  },
  
    methods: {
   
      selectPizza(type) {
        this.selectedPizzaType = type; 
      },
      addToCart() {
      for (let i = 0; i < this.orderQuantity; i++) {
      const product = {
        
        productDtoList: {
          productId: this.selectedProduct,
          productOptionDtoList: [this.selectedCrust, this.selectedTopping, this.selectedSauce]
        },
        };
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

      }
      
    }
      
};
</script>
<style scoped>
/* General Styling */
.customer-products {
  max-width: 1200px;
  margin: 30px auto;
  padding: 30px;
  background-color: #2e2e2e;
  border-radius: 10px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  color: #fff;
}

.customer-order {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

/* Pizza List Styling */
.pizza-list {
  display: flex;
  justify-content: space-between;
  gap: 30px;
  padding: 0;
  margin-bottom: 30px;
  list-style: none;
}

.pizza-list li {
  flex: 1;
  text-align: center;
}

.pizza-img-container {
  width: 100%;
  max-width: 300px;
  margin: 0 auto;
  overflow: hidden;
  border-radius: 8px;
  cursor: pointer;
}

.pizza-img {
  width: 100%;
  height: auto;
  object-fit: cover;
}

/* Dropdowns and Inputs */
.dropdown, .quantity {
  width: 50%;
  padding: 12px;
  font-size: 1rem;
  margin-bottom: 15px;
  background-color: #333;
  border: 1px solid #555;
  border-radius: 5px;
  color: #fff;
}

/* Delivery Option */
.delivery-options {
  display: flex;
  width: 50%;
  justify-content: space-between;
}

.delivery-options input[type="radio"] {
  margin-right: 8px;
}

/* Action Buttons */
.action-buttons {
  display: flex;
  gap: 20px;
  justify-content: center;
}

.add-to-cart, .proceed-to-checkout {
  padding: 15px 25px;
  background-color: #d84d22;
  color: white;
  border: none;
  border-radius: 30px;
  font-size: 1.1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.3s ease;
}

.add-to-cart:hover, .proceed-to-checkout:hover {
  background-color: #c13c18;
  transform: scale(1.05);
}
.selected {
  border: 5px solid #d84d22;  
  transform: scale(1.05);      
}
.cart-icon {
  margin-right: 10px;
}

/* Responsive Adjustments */
@media (max-width: 768px) {
  .pizza-list {
    flex-direction: column;
    align-items: center;
  }

  .pizza-img-container {
    max-width: 200px;
  }

  .pizza-img {
    width: 100%;
    height: auto;
  }

  .add-to-cart, .proceed-to-checkout {
    width: 100%;
    padding: 20px;
  }
}
</style>
