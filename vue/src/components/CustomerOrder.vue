<template>
  
    <div class="customer-products">
      <form class="customer-order" v-on:submit.prevent="customerOrder()">
        <ul>
          <li>
            <img src="../assets/PizzaPic.png" id="custom-pizza" value="custom-pizza"/>
            <h3>Custom Pizza</h3>
          </li>
          <li>
            <img src="../assets/PizzaPic.png" id="specialty-pizza" value="specialty-pizza"/>
            <h3>Specialty Pizza</h3>
          </li>
        </ul>
        <!----Edit v-if-->>
        <select v-model="selectedSpecialty" id="specialty" required>
          <option v-for="specialtyPizza in specialtyPizzas" :key="specialtyPizza" :value="specialtyPizza">
            {{specialtyPizza }}
          </option>
        </select>
    <select v-model="selectedCrust" id="crust" required>
      <option v-for="crust in crusts" :key="crust" :value="crust">
        {{ crust }}
      </option>
    </select>

    <select v-model="selectedTopping" id="toppings" required>
      <option v-for="topping in toppings" :key="topping" :value="topping">
        {{ topping }}
      </option>
    </select>

    <select v-model="selectedSauce" id="sauces" required>
      <option v-for="sauce in sauces" :key="sauce" :value="sauce">
        {{ sauce }}
      </option>
    </select>

    <select v-model="selectedCheese" id="cheese" required>
      <option v-for="cheese in cheeses" :key="cheese" :value="cheese">
        {{ cheese }}
      </option>
    </select>

    <select v-model="selectedSize" id="size" required>
      <option v-for="size in sizes" :key="size" :value="size">
        {{ size }}
      </option>
    </select>
  
  <div><input class="quantity" name="quantity" type="text" placeholder="Quantity"/></div>
  <button type="submit">Submit Order</button>
</form>
</div>
</template>

<script>
import OrderService from '../services/OrderService';

export default {
 
  
  data() {
    return {
      order:{
              orderUserId: this.$store.state.user.id,
              productList:[
                {
                  product_name:'',
                  specialty_pizza_name:'',
                  crust:'',
                  toppings:[],
                  sauce:'',
                  cheese:'',
                  quantity:0
              }]
            },
      specialtyPizzas:[],
      crusts:[],
      topping:[],
      sauces:[],
      cheeses:[],
      sizes:[]

    };
  },
  methods:{
    getSpecialtyList(){
        OrderService.getSpecialtyPizzas
        .then(response => {
          this.specialtyPizzas = response.data;
        })
        .catch(error =>{
          console.log(error);
        })
      },
    getCrustList(){
        OrderService.getCrusts
        .then(response => {
          this.crusts = response.data;
        })
        .catch(error =>{
          console.log(error);
        })
      },
      getToppingList(){
        OrderService.getToppings
        .then(response => {
          this.toppings = response.data;
        })
        .catch(error =>{
          console.log(error);
        })
      },
      getSauceList(){
        OrderService.getToppings
        .then(response => {
          this.sauces = response.data;
        })
        .catch(error =>{
          console.log(error);
        })
      },
      getCheeseList(){
        OrderService.getToppings
        .then(response => {
          this.cheeses = response.data;
        })
        .catch(error =>{
          console.log(error);
        })
      },
      getSizeList(){
        OrderService.getToppings
        .then(response => {
          this.cheeses = response.data;
        })
        .catch(error =>{
          console.log(error);
        })
      },
      
  }
 
}
</script>

<style scoped>

.pizza-dropdowns select {
  margin: 10px;
  padding: 5px;
}
</style>