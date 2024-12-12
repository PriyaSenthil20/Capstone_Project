<template>
  <div class="customer-products">
    <form class="customer-order" v-on:submit.prevent="customerOrder()">
      <h3>Select Delivery Option</h3>
    <ul class="delivery-options">
    <li>Pick Up<br><br>
      <div class="option-container" value='2' @click="selectDeliveryOption('2') ">
        <img 
        src="../assets/pickup-icon.png" 
        alt="Pick-up Option" 
        class="option-img" 
        :class="{ 'selected': deliveryOption === '2' }"
        />
      <p v-if="deliveryOption === '2'">Pick-up Selected</p>
      </div>
    </li>
    <li>Delivery <br><br>
    <div class="option-container value='1'"  @click="selectDeliveryOption('1') ">
      <img 
        src="../assets/delivery-icon.png" 
        alt="Delivery Option" 
        class="option-img" 
        :class="{ 'selected': deliveryOption === '1' }"
       />
      <p v-if="deliveryOption === '1'">Delivery Selected</p>
      </div>
      </li>
      </ul>
      <!-- Pizza Selection -->
      <div v-if="deliveryOption !== null" class="pizza-selection">
        <h3>Select Pizza Type</h3>
        <ul class="pizza-list"><li>Custom Pizza<br><br>
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
        <li>Specialty Pizza<br><br>
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
            {{ customPizza.productDesc }}
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
        <h3>Premium Toppings</h3>
        <div id="premiumToppings" class="checkbox-group">
          <label v-for="topping in $store.state.premiumToppings" :key="topping.optionId">
            <input type="checkbox" :value="topping.optionId" v-model="selectedPremiumToppings"  />
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
            {{ specialtyPizza.productDesc }}
          </option>
        </select>    
      </div>
        <div v-if="deliveryOption !== null && selectedPizzaType !== null">
        <!-- Quantity Input -->
        <h3>Quantity</h3>
        <input class="quantity" name="quantity" type="number" v-model="orderQuantity" placeholder="Quantity" min="1"/>
      
        <h3>PickUp Timings</h3>
        <div id="time" class="dropdown">
          <label v-if="this.dateLabel">The Store is closed now, but you can place a pre-order for tomorrow: {{this.pickUpDate}}</label>
          <select v-model="time" class="dropdown" required>
            <option v-for="pickUpTimeSlot in this.storeTime" :key="pickUpTimeSlot" :value="pickUpTimeSlot" >
              {{ pickUpTimeSlot }}
            </option>
          </select>          
        </div> 
        <!-- Buttons -->
        <div class="action-buttons">
          <button type="button" class="add-to-cart" @click="addToCart">
            <span class="cart-icon">&#128722;</span> Add to Cart
          
            <span v-if="totalCartItems>0" class="cart-badge">{{ totalCartItems }}</span> 
          </button>
          <button type="button" class="proceed-to-checkout" @click="proceedToCheckout">
            Proceed to Checkout
          </button>
        </div>
      </div>
   
  <div v-if="showConfirmation" class="order-confirmation">
    <h2>Order Summary</h2>
    <ul>
      <li>Order Id:&nbsp;&nbsp;{{this.$store.state.orderDetails.orderId}}</li>
      <li>Order Details:</li>

        <li v-for="product in orderProducts">
          &nbsp;&nbsp;{{ product }}
        </li>
        <li v-for="option in orderOptions">
          &nbsp;&nbsp;{{ option }}
        </li>
        <li>&nbsp;&nbsp;Total Sales Price: ${{this.$store.state.orderDetails.totalSale}}</li>
        <li v-if="this.deliveryOption === '2'"><br>
          <ul><li>Pick Up Date and Time:
            <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; {{this.$store.state.orderDetails.pickUpDate}}&nbsp;{{this.$store.state.orderDetails.pickUpTime}}</li>
            <li>Pickup Address: <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rocco's Pizza Place,<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Anthony's Drive,<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Java Purple,<br>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;NLR-2024.

            </li>
        </ul></li>
  </ul>
    <div class="action-buttons">
      <router-link v-bind:to="{ name: 'payments' }">
    <button type="button" class="continue" >
      Continue
    </button></router-link>
    
    <button type="button" class="cancel" v-on:click="cancelOrder">
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
  created(){
    this.loadTime();
  },
  data() {
   
    return {
      order: {
        customerId: null,
        transferId: null,
        pickUpDate: null,
        pickUpTime: null,
        productDtoList: []
      }, 
      
      orderProducts:[],
      orderOptions:[],
      time:null,
      date:(new Date().getMonth()+1)+'-'+new Date().getDate()+'-'+new Date().getFullYear(),
      selectedProduct: null,
      selectedPizzaType: null,
      selectedCrust: null,
      selectedToppings: [],
      selectedPremiumToppings: [],
      selectedSauce: null,
      deliveryOption: null,
      showConfirmation :false,
      orderQuantity : 1,
      errorMessage :[],
      cartCheck : false,
      dateLabel:false,
      storeTime:[]
    };
    },
   computed: {
        totalCartItems() {
            return this.order.productDtoList.length;
          }
        },

    methods: {
      selectDeliveryOption(option) {
        this.deliveryOption=option;
      },
      selectPizza(type) {
        this.selectedPizzaType = type; 
      },
      checkForSelection()
        {   
          let result=true;
        if (!this.selectedProduct) {
          this.errorMessage.push("Please select a product.");
          result=false;
        }
        if (!this.time) {
          this.errorMessage.push("Please select a TimeSlot.");
          result=false;
        }
        if (this.selectedPizzaType === 'custom') {
          if (!this.selectedCrust) {
            this.errorMessage.push("Please select a crust.");
            result=false;
          }
          if (!this.selectedSauce) {
            this.errorMessage.push("Please select a sauce.");
            result=false;
          }
          if (this.selectedToppings.length === 0) {
            this.errorMessage.push("Please select at least one topping.");
            result=false;
          }
        }
        return result; 
        },      
       
        addToCart(){
          if(this.checkForSelection()===true){
            this.cartCheck=true;
          let productOptions=[];
          
          if(this.selectPizza==='specialty'){
            productOptions.push({productOptionId:'0'});
          }
          else{
          if(this.selectedCrust){
            productOptions.push({productOptionId:this.selectedCrust})
          }
          for(let i=0;i<this.selectedToppings.length;i++){
          
            productOptions.push({productOptionId:this.selectedToppings[i]});
          }
          for(let i=0;i<this.selectedPremiumToppings.length;i++){
          
          productOptions.push({productOptionId:this.selectedPremiumToppings[i]});
        }
          if(this.selectedSauce){
            productOptions.push({productOptionId:this.selectedSauce});
          }       
          }
          for (let i = 0; i < this.orderQuantity; i++) {
          
           this.order.productDtoList.push(
          {
            productId:this.selectedProduct,
            productOptionDtoList:productOptions
          });
          }       
         alert("Products added to the order"); 
          
         }  
         else{
          alert("Please fill the appropriate fields: "+ this.errorMessage.pop());
         }
      },
        proceedToCheckout(){
          if(this.checkForSelection() === true){
            if(this.cartCheck===false){
                this.addToCart();
              }
          this.order.customerId=this.$store.state.user.id
          this.order.transferId=this.deliveryOption;
          this.order.pickUpDate=this.date;
          this.order.pickUpTime=this.time;
          try{
          this.$store.commit('SET_ORDER', this.order);
          this.$store.dispatch('createCustomerOrder'); 
          console.log("Order stored in Vuex:", this.$store.state.order);
          this.showConfirmation = true;
          this.initializeOrder();
          this.fillOrderDetails();
          }catch (error) {
          console.error("Error processing customer order:", error);
          }}
          else{
            alert("Please fill the appropriate fields: "+ this.errorMessage.pop());
          }
        },
        initializeOrder(){
            this.order= {
              transferId:null,
              pickUpDate: null,
              pickUpTime: null,
              productDtoList: []
            };
          this.order.productDtoList.forEach(product => {
            product.productOptionDtoList.forEach(option => {
              option=null;
        
            }); 
            this.errorMessage=[];      
         });
        
        },
        cancelOrder(){
          this.order= {
            transferId:null,
            pickUpDate: null,
            pickUpTime: null,
            productDtoList: []
           }; 
           this.errorMessage=[];
            this.showConfirmation=false;    
        
        },
        fillOrderDetails(){
          let temp=this.$store.state.products;
          for(let i=0;i<temp.length;i++){
             if(temp[i].productId===this.selectedProduct){
                this.orderProducts.push(temp[i].productDesc);
              }
            }
          temp=this.$store.state.options;
          for(let i=0;i<temp.length;i++){
              if(temp[i].optionId===this.selectedCrust)
                {
                  this.orderOptions.push(temp[i].optionName);
                }
                else if(temp[i].optionId===this.selectedSauce){
                  this.orderOptions.push(temp[i].optionName);
                }
                else if (this.selectedToppings.includes(temp[i].optionId)) {
                   this.orderOptions.push(temp[i].optionName);
                }
                else if (this.selectedPremiumToppings.includes(temp[i].optionId)) {
                   this.orderOptions.push(temp[i].optionName);
                }
              }
                },
                  loadTime() {
          let hours24 = new Date().getHours();
          const minutes = new Date().getMinutes();
          let ampm = ' AM';
          this.storeTime = []; // Initialize storeTime array

          if (hours24 >= 21) {
            this.dateLabel = true;
            const today = new Date();
            const nextDate = new Date(today);
            nextDate.setDate(today.getDate() + 1);
            this.pickUpDate = nextDate.getDate() + "-" + (nextDate.getMonth() + 1) + "-" + nextDate.getFullYear();
            hours24 = 8;

            for (let i = 0; hours24 < 21; hours24++) {
              let displayHours12 = hours24 % 12 || 12;
              ampm = hours24 >= 12 ? ' PM' : ' AM';
              this.storeTime.push(displayHours12 + ':00' + ampm, displayHours12 + ':30' + ampm);
            }
          } else if (hours24 < 8) {
            hours24 = 8;
            for (let i = 0; hours24 < 21; hours24++) {
              let displayHours12 = hours24 % 12 || 12;
              ampm = hours24 >= 12 ? ' PM' : ' AM';
              this.storeTime.push(displayHours12 + ':00' + ampm, displayHours12 + ':30' + ampm);
            }
          } else {
            for (let i = 0; hours24 < 21; ) {
              let displayHours12 = hours24 % 12 || 12;
              ampm = hours24 >= 12 ? ' PM' : ' AM';

              if (minutes < 30) {
                this.storeTime.push(displayHours12 + ':30' + ampm);
                hours24++;
                displayHours12 = hours24 % 12 || 12;
                ampm = hours24 >= 12 ? ' PM' : ' AM';
                this.storeTime.push(displayHours12 + ':00' + ampm);
              } else {
                hours24++;
                displayHours12 = hours24 % 12 || 12;
                ampm = hours24 >= 12 ? ' PM' : ' AM';
                this.storeTime.push(displayHours12 + ':00' + ampm, displayHours12 + ':30' + ampm);
              }
            }
          }
        }

  
    }
  };
</script>
<style src="../styles/CustomerOrderStyles.css">
</style>
