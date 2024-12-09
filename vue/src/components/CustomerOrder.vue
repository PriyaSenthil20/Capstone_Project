<template>
  <div class="customer-products">
    <form class="customer-order" v-on:submit.prevent="customerOrder()">
      <!-- Pizza Selection -->
      <ul class="pizza-list">
        <li>
          <h3>Custom Pizza</h3>
          <div class="pizza-img-container" @click="selectPizza('custom')">
            <img src="../assets/PizzaPic.png" id="custom-pizza" value="custom-pizza" class="pizza-img"  :class="{ 'selected': selectedPizzaType === 'custom' }"/>

          </div>
        </li>
        <li>
          <h3>Specialty Pizza</h3>
          <div class="pizza-img-container" @click="selectPizza('specialty')">
            <img src="../assets/PizzaPic.png" id="specialty-pizza" value="specialty-pizza" class="pizza-img"  :class="{ 'selected': selectedPizzaType === 'specialty' }"/>
          </div>
        </li>
      </ul>

      <!-- Pizza Options -->
      <div class="pizza-options">
        <h3>Specialty Pizza</h3>
        <select v-model="selectedSpecialty" id="specialty" class="dropdown" :disabled="isSpecialtyDisabled">
          <option v-for="specialtyPizza in specialtyPizzas" :key="specialtyPizza" :value="specialtyPizza">
            {{ specialtyPizza }}
          </option>
        </select>

        <h3>Crust</h3>
        <select v-model="selectedCrust" id="crust" class="dropdown" :disabled="isCrustDisabled" required>
          <option v-for="crust in crusts" :key="crust" :value="crust">{{ crust }}</option>
        </select>

        <h3>Topping</h3>
        <select v-model="selectedTopping" id="toppings" class="dropdown" :disabled="isToppingDisabled" required>
          <option v-for="topping in toppings" :key="topping" :value="topping">{{ topping }}</option>
        </select>

        <h3>Sauce</h3>
        <select v-model="selectedSauce" id="sauces" class="dropdown" :disabled="isSauceDisabled" required>
          <option v-for="sauce in sauces" :key="sauce" :value="sauce">{{ sauce }}</option>
        </select>

        <h3>Sizes</h3>
        <select v-model="selectedSize" id="size" class="dropdown" :disabled="isSizeDisabled" required>
          <option v-for="size in sizes" :key="size" :value="size">{{ size }}</option>
        </select>

        <!-- Delivery Option -->
        <div class="delivery-options">
          <h3>Delivery Option</h3>
          <label>
            <input type="radio" v-model="deliveryOption" value="pickup" />
            Pick-up
          </label>
          <label>
            <input type="radio" v-model="deliveryOption" value="delivery" />
            Delivery
          </label>
        </div>

        <!-- Quantity Input -->
        <h3>Quantity</h3>
        <input class="quantity" name="quantity" type="number" v-model="orderQuantity" placeholder="Quantity" min="1"/>

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
    return {
      order: {
        orderUserId: this.$store.state.user.id,
        productList: [
          {
            product_name: '',
            specialty_pizza_name: '',
            crust: '',
            toppings: [],
            sauce: '',
            quantity: 0,
          },
        ],
      },
      specialtyPizzas: [],
      crusts: [],
      toppings: [],
      sauces: [],
      sizes: [],
      selectedPizzaType: null, // 'custom' or 'specialty'
      selectedSpecialty: null,
      selectedCrust: null,
      selectedTopping: null,
      selectedSauce: null,
      selectedSize: null,
      orderQuantity: 1, // Default quantity
      cart: [], // Cart to hold added items
    };
  },
  computed: {
    // Dynamically disable dropdowns based on pizza type selection
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
    isSizeDisabled() {
      return this.selectedPizzaType === null;
    },
    cartItemCount() {
      return this.cart.length;
    },
  },
  mounted() {
   
    this.getSpecialtyList();
    this.getPizzaOptions();
  },
  methods: {
    getSpecialtyList() {
      OrderService.getPizzas().then(response => {
        const products = response.data;
        this.specialtyPizzas = products
          .filter(product => product.product_type_id === 1) 
          .map(product => product.product_name);
      }).catch(error => {
        console.error("Error fetching specialty pizzas:", error);
      });
    },
    getPizzaOptions() {
      OrderService.getPizzaOptions() .then(response => {
        const options = response.data;
        
        this.crusts = options
          .filter(option => option.option_type_id === 3) 
          .map(option => option.option_name);

        this.toppings = options
          .filter(option => option.option_type_id === 1)
          .map(option => option.option_name);

        this.sauces = options
          .filter(option => option.option_type_id === 2) 
          .map(option => option.option_name);
      })
      .catch(error => {
        console.error('Error fetching pizza options:', error);
      });
    },
    
    getSizeList() {
      OrderService.getSizes().then((response) => {
      this.sizes = response.data.map(size => size.size_style);
    })
    },
    customerOrder() {
      this.$store.commit('CUSTOMER_ORDER', this.order);
    },
    selectPizza(type) {
      this.selectedPizzaType = type; // 'custom' or 'specialty'
    },
    addToCart() {
      // Create the order item based on the current selections
      const orderItem = {
        pizzaType: this.selectedPizzaType,
        specialty: this.selectedSpecialty,
        crust: this.selectedCrust,
        toppings: this.selectedTopping,
        sauce: this.selectedSauce,
        size: this.selectedSize,
        quantity: this.orderQuantity,
      };

      // Add the item to the cart
      this.cart.push(orderItem);

      // Optionally, clear selections after adding to cart
      this.resetSelections();

      // Log the cart to console for debugging
      console.log('Current cart:', this.cart);
    },
    resetSelections() {
      this.selectedPizzaType = null;
      this.selectedSpecialty = null;
      this.selectedCrust = null;
      this.selectedTopping = null;
      this.selectedSauce = null;
      this.selectedSize = null;
      this.orderQuantity = 1; // Reset quantity
    },
    proceedToCheckout() {
      // Handle the checkout logic here (redirect to checkout page)
      console.log('Proceeding to checkout', this.cart);
      // You can use Vue Router to navigate to a checkout page, for example:
      this.$router.push('/checkout');
    },
  },
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
  width: 100%;
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
  justify-content: space-between;
  gap: 20px;
}

.delivery-options label {
  font-size: 1.1rem;
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
