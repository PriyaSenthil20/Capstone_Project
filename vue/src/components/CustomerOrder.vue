<template>
  <div class="customer-products">
    <form class="customer-order" v-on:submit.prevent="customerOrder()">
      <h3>Select Delivery Option</h3>
    <ul class="delivery-options">
    <li>Pick Up
      <div class="option-container" @click="selectDeliveryOption('pick-up')">
        <img 
        src="../assets/pickup-icon.png" 
        alt="Pick-up Option" 
        class="option-img" 
        :class="{ 'selected': deliveryOption === 'pick-up' }"
        />
      <p v-if="deliveryOption === 'pick-up'">Pick-up Selected</p>
      </div>
    </li>
    <li>Delivery 
    <div class="option-container" @click="selectDeliveryOption('delivery')">
      <img 
        src="../assets/delivery-icon.png" 
        alt="Delivery Option" 
        class="option-img" 
        :class="{ 'selected': deliveryOption === 'delivery' }"
       />
      <p v-if="deliveryOption === 'delivery'">Delivery Selected</p>
      </div>
      </li>
      </ul>
      <!-- Pizza Selection -->
      <div v-if="deliveryOption !== null" class="pizza-selection">
        <h3>Select Pizza Type</h3>
        <ul class="pizza-list"><li>Custom Pizza
        <div class="pizza-img-container" @click="selectPizza('custom')">
          <img 
            src="../assets/PizzaPic.png" 
            id="custom-pizza" 
            class="pizza-img" 
            :class="{ 'selected': selectedPizzaType === 'custom' }" 
            alt="Custom Pizza" 
          />
          <p v-if="selectedPizzaType === 'custom'">Custom Pizza Selected</p>
        </div></li>
        <li>Specialty Pizza
        <div class="pizza-img-container" @click="selectPizza('specialty')">
          <img 
            src="../assets/PizzaPic.png" 
            id="specialty-pizza" 
            class="pizza-img" 
            :class="{ 'selected': selectedPizzaType === 'specialty' }" 
            alt="Specialty Pizza" 
          />
          <p v-if="selectedPizzaType === 'specialty'">Specialty Pizza Selected</p>
        </div>
        </li>
      </ul>
      </div>
      <!-- Pizza Options -->
      <div v-if="selectedPizzaType === 'custom'">
        <h3>Custom Pizza Options</h3>
        <h3>Custom Pizzas</h3>
        <select v-model="selectedProduct" class="dropdown" required>
          <option v-for="customPizza in this.$store.state.customPizzas" :key="customPizza.productId" :value="customPizza.productId">
            {{ customPizza.productDescription }}
          </option>
        </select>
        <h3>Crust</h3>
        <select v-model="selectedCrust" class="dropdown" required>
          <option v-for="crust in this.$store.state.crusts" :key="crust.optionId" :value="crust.optionId">
            {{ crust.optionName }}
          </option>
        </select>
        <h3>Toppings</h3>
        <div id="toppings" class="checkbox-group">
          <label v-for="topping in $store.state.toppings" :key="topping.optionId">
            <input type="checkbox" :value="topping.optionId" v-model="selectedToppings"  />
            {{ topping.optionName }}
          </label>
        </div>
        <h3>Sauces</h3>
        <select v-model="selectedSauce" class="dropdown" required>
          <option v-for="sauce in this.$store.state.sauces" :key="sauce.optionId" :value="sauce.optionId">
            {{ sauce.optionName }}
          </option>
        </select>
      </div>

      <div v-if="selectedPizzaType === 'specialty'">
        <h3>Specialty Pizza Options</h3>
        <select v-model="selectedProduct" class="dropdown" required>
          <option v-for="specialtyPizza in this.$store.state.specialtyPizzas" :key="specialtyPizza.productId" :value="specialtyPizza.productId">
            {{ specialtyPizza.productDescription }}
          </option>
        </select>
     
      </div>
        <div v-if="deliveryOption !== null && selectedPizzaType !== null">
        <!-- Quantity Input -->
        <h3>Quantity</h3>
        <input class="quantity" name="quantity" type="number" v-model="orderQuantity" placeholder="Quantity" min="1"/>
      
        <!-- Buttons -->
        <div class="action-buttons">
          <button type="button" class="add-to-cart" @click="addToCart">
            <span class="cart-icon">&#128722;</span> Add to Cart
          
            <span class="cart-badge">{{ totalCartItems }}</span> 
          </button>
          <button type="button" class="proceed-to-checkout" @click="proceedToCheckout">
            Proceed to Checkout
          </button>
        </div>
      </div>
   
  <div v-if="showConfirmation" class="order-confirmation">
    <h2>Order Summary</h2>
    <p>{{this.$store.state.orderDetails.orderId}}</p>

    <div class="action-buttons">
    <button type="button" class="continue" >
      Continue
    </button>
    <button type="button" class="cancel" >
      Cancel
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
        transferId: '',
        pickUpDate: defaultPickUpDate,
        pickUpTime: defaultPickUpTime,
        productDtoList: []
      },
      productOption:[],
      productDtoList:[{
        productId:'',
        productOptionDtoList:[] 
      }],
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
      cart:{
           productList:[] 
          }
    };
    },
   computed: {
        totalCartItems() {
            return this.$store.state.cart.length;
          }
        },

    methods: {
      selectDeliveryOption(option) {
      this.deliveryOption = option; 
      },
      selectPizza(type) {
        this.selectedPizzaType = type; 
      },
      /*addToCart() {
      for (let i = 0; i < this.orderQuantity; i++) {
      
        const product = {
        
        productDtoList: {
          productId: this.selectedProduct,
          productOptionDtoList: [this.selectedCrust, ...this.selectedToppings, this.selectedSauce]
        },
        };
        alert(product.productDtoList.productId);
        this.cart.push(product);
        }
        alert(`Added ${this.orderQuantity} of ${this.selectedProduct} to the cart.`);
        console.log('Updated Cart:', this.cart);
        },*/
        addToCart(){
          for (let i = 0; i < this.orderQuantity; i++) {
          if(this.selectedCrust){
            this.productOption.push(this.selectedCrust)
          }
          for(let i=0;i<this.selectedToppings.length;i++){
          
            this.productOption.push(this.selectedToppings[i]);
          }
          if(this.selectedSauce){
            this.productOption.push(this.selectedSauce)
          }       
        }
        this.productDtoList.push(
          {
            productId:this.selectedProduct,
            productOptionDtoList:this.productOption
          });
          this.cart={
            productList:this.productDtoList
          }
          alert("before");
          this.$store.commit('ADD_CART',this.cart);
          alert(this.$store.state.cart.productList.length);
      },
       
      /*proceedToCheckout() {
        if(this.cart.length<=0){
          this.addToCart();
        }
        const newOrder={
        transferId: this.deliveryOption,
        pickUpDate: this.date,
        pickUpTime: this.time,
        productDtoList:this.cart
        };
        this.showConfirmation=true;
        this.order=newOrder;
        this.customerOrder();
        alert("Order successfully created!");
        
          this.cart = [];
          this.order = {
          transferId: null,
          pickUpDate: this.date,
          pickUpTime: this.time,
          productDtoList: []
        }},*/
        proceedToCheckout(){
          if(this.$store.state.cart.length<=0){
            this.addToCart();
          }
          this.order={
            transferId:this.deliveryOption,
            pickUpDate:this.defaultPickUpDate,
            pickUpTime:this.defaultPickUpTime,
            productDtoList:this.productDtoList
          }
          this.showConfirmation=true;
          try{
          this.$store.commit('SET_ORDER', this.order);
          alert(this.$store.state.order.transferId);
          this.$store.dispatch('createCustomerOrder');
          
          console.log("Order stored in Vuex:", this.$store.state.order);
          this.showConfirmation = true;
          }catch (error) {
          console.error("Error processing customer order:", error);
          }
        },
        /*customerOrder() {
          try{
          this.$store.commit('SET_ORDER', this.order);
          alert(this.$store.state.order.transferId);
          this.$store.dispatch('createCustomerOrder');
          
          console.log("Order stored in Vuex:", this.$store.state.order);
          this.showConfirmation = true;
          }catch (error) {
          console.error("Error processing customer order:", error);
          }*/
          
      
      
    }
      
};
</script>
<style src="../styles/CustomerOrderStyles.css">
</style>
