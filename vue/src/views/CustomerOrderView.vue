<template>
    <div class="customer-products">
        <ul>
            <li>
                <img src="../assets/PizzaPic.png" id="custom-pizza"/>
            <h3>Custom Pizza</h3>
            </li>
            <li>
                <img src="../assets/PizzaPic.png" id="speciality-pizza"/>
            <h3>Speciality Pizza</h3>
            </li>
        </ul>
        <select id="custom-pizza" placeholder="Select a State" v-model="customer.customerState" required >
	           <option value="crust">Crust</option>
		          <option value="size">Size</option>
		          <option value="toppings">Toppings</option>
                  <option value="sauces">Sauces</option>
		          
                </select>

    </div>
    
</template>
<script>
//import  from '@vue/composition-api'
import CustomerOrder from '../components/CustomerOrder.vue'

export default ({
    components:{
        CustomerOrder
    }
})
</script>
