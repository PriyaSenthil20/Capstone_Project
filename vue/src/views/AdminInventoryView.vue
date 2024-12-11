 <!-- WILL BE LENGTHY WITH NEW ROUTE!!! If we have time I'll attempt to
 move the tables to the components and not the view and drop everything in the store.
 
Tabs to choose whether to display product/options removed If you see
 any references to them or a stepper please comment out or delete
contains:
- 1st table for products --18
 - attached form to add new Product shown by button -- 83
-2nd table product options --
 - attached form to add new product options shown by button --
 -->
<template>
  <div class="home">
    <nav class="navbar">
      <nav-options />
    </nav>
     <!-- Demo of Gets from Product table to be used in table and basis for forms in components-->
    
    <div class="content-section">      
      <inventory-product v-bind:products="products" />
    </div>
    <div class="product-page-buttons">
      <button v-on:click="editAvailability = !editAvailability">Edit Product Availability</button>
      <button v-on:click="showForm = !showForm">Add New Product</button>
    </div>
     <div id="test-table" v-show="editAvailability">
      <h3>Change Price/Availability</h3>
      <table id="tblProduct">
      <thead>
        <tr>
          <th>select</th>
          <th>Product Name</th>
          <th>Product Price</th>
          <th>Availability</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="checkbox" id="selectAllProducts" v-on:change="selectAllProducts($event)" v-bind:checked="selectedProducts.length === products.length" />
          </td>
          <td>
            <input type="text" id="productDescFilter" v-model="filter.productDesc" />
          </td>
          <td>
            <input type="text" id="productPriceFilter" v-model="filter.productPrice" />
          </td>
          <td>
            <select id="productAvailableFilter" v-model="filter.productAvailable">
              <option value>Show All</option>
              <option value="true">Available</option>
              <option value="false">Unavailable</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="product in filteredList"
          v-bind:key="product.productId"
          v-bind:class="{ deactivated: product.productAvailable === 'false' }"
        >
          <td>
            <input
              type="checkbox"
              v-bind:id="'checkbox' + product.productId"
              v-bind:value="product.productId"
              v-model="selectedProducts"
            />
          </td>
          <td>{{ product.productDesc }}</td>
          <td>{{product.productPrice}}</td>
            <!-- <input 
          type="text" v-bind:id="'price' + product.productId"
           v-bind:placeholder="product.productPrice" /> 
          <button v-bind:disabled="!commitButtonEnabled"
              v-bind:id="'updateBtn' + product.productId"
              class="updateValue"
              v-on:click="updatePrice(product)"
            >Update</button> -->
          <!-- To-do. Add price update functionality, make input fields with placeholder of price and tie them to
           update quantity method that posts to db, button disabled if filtered list price matches product price in db -->
          <td>{{ product.productAvailable }}</td>
          
          <td>
            <button
              class="btnActivateDeactivate"
              v-on:click="flipStatus(product.productId)"
            >{{ product.productAvailable === 'true' ? 'Make Unavailable' : 'Make Available' }}</button>
          </td>
        </tr>
      </tbody>
    </table>

    
    <div class="all-actions">
      <button v-on:click="activateSelectedProducts()" v-bind:disabled="!actionButtonEnabled">Make Selected Available</button>
      <button v-on:click="deactivateSelectedProducts()" v-bind:disabled="!actionButtonEnabled">Make Selected Unavailable</button>
    </div>
    </div>
    <div>
      <form id="frmAddNewProduct" v-show="showForm" v-on:submit.prevent="saveProduct">
      <div class="field">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" v-model="newProduct.productName" />
      </div>
      <div class="field">
        <label for="productDesc">Product Description:</label>
        <input type="text" id="productDesc" name="productDesc" v-model="newProduct.productDesc" />
      </div>
      <div class="field">
        <label for="ProductTypeId">Product Type Id:</label>
        <select type="text" id="ProductTypeId" name="ProductTypeId" v-model="newProduct.productTypeId" >
          <!-- SG dynamically populate options from product type table, content to contain both values of Product type table productTypeId and ProductName-->
        <option value="1">Special Pizza</option>
        <option value="2">Custom Pizza</option>
        <option value="3">Drink</option>
        </select>
      </div>
      <div class="field">
        <label for="productPrice">Product Price:</label>
        <input type="text" id="productPrice" name="productPrice" v-model="newProduct.productPrice" />
      </div>
            <div class="field">
        <label for="productAvailable">Product Available?</label>
        <input type="checkbox" id="productAvailable" name="productAvailable" v-model="newProduct.productAvailable" />
      </div>
      <div class="field">
        <label for="sizeId">Size:</label>
        <select type="text" id="sizeId" name="sizeId" v-model="newProduct.sizeId" >
          <!-- SG dynamically populate options from size table, content to contain both values of Size Table SizeId and SizeStyle and Price Multiplier-->
        <option value="1">Small</option>
        <option value="2">Medium</option>
        <option value="3">Large</option>
        <option value="4">Anthony</option>
        </select>
      </div>      
      <button type="submit" class="btn save">Save Product</button>
    </form>
    </div>
<!-- ----------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
-->

    <!-- Demo of Gets from Product Options table to be used in table and basis for forms in components-->
    <div class="content-section">      
      <inventory-product-option v-bind:productOptions="productOptions" />
    </div>
    <div class="option-page-buttons">
      <button v-on:click="editOptionAvailability = !editOptionAvailability">Edit Option Availability</button>
      <button v-on:click="showOptionForm = !showOptionForm">Add New Option</button>
    </div>
    <div id="test-option-table" v-show="editOptionAvailability">
      <h3>Change Price/Availability</h3>
      <table id="tblOption">
      <thead>
        <tr>
          <th>select</th>
          <th>Option Name</th>
          <th>Option Available</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input type="checkbox" id="selectAllProducts" v-on:change="selectAllProducts($event)" v-bind:checked="selectedProducts.length === products.length" />
          </td>
          <td>
            <input type="text" id="productDescFilter" v-model="filter.productDesc" />
          </td>
          <td>
            <input type="text" id="productPriceFilter" v-model="filter.productPrice" />
          </td>
          <td>
            <select id="productAvailableFilter" v-model="filter.productAvailable">
              <option value>Show All</option>
              <option value="true">Available</option>
              <option value="false">Unavailable</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="product in filteredList"
          v-bind:key="product.productId"
          v-bind:class="{ deactivated: product.productAvailable === 'false' }"
        >
          <td>
            <input
              type="checkbox"
              v-bind:id="'checkbox' + product.productId"
              v-bind:value="product.productId"
              v-model="selectedProducts"
            />
          </td>
          <td>{{ product.productDesc }}</td>
          <td>{{product.productPrice}}</td>
            <!-- <input 
          type="text" v-bind:id="'price' + product.productId"
           v-bind:placeholder="product.productPrice" /> 
          <button v-bind:disabled="!commitButtonEnabled"
              v-bind:id="'updateBtn' + product.productId"
              class="updateValue"
              v-on:click="updatePrice(product)"
            >Update</button> -->
          <!-- To-do. Add price update functionality, make input fields with placeholder of price and tie them to
           update quantity method that posts to db, button disabled if filtered list price matches product price in db -->
          <td>{{ product.productAvailable }}</td>
          
          <td>
            <button
              class="btnActivateDeactivate"
              v-on:click="flipStatus(product.productId)"
            >{{ product.productAvailable === 'true' ? 'Make Unavailable' : 'Make Available' }}</button>
          </td>
        </tr>
      </tbody>
    </table>

    
    <div class="all-actions">
      <button v-on:click="activateSelectedProducts()" v-bind:disabled="!actionButtonEnabled">Make Selected Available</button>
      <button v-on:click="deactivateSelectedProducts()" v-bind:disabled="!actionButtonEnabled">Make Selected Unavailable</button>
    </div>
    </div>
    <div>
      <form id="frmAddNewProduct" v-show="showForm" v-on:submit.prevent="saveProduct">
      <div class="field">
        <label for="productName">Product Name:</label>
        <input type="text" id="productName" name="productName" v-model="newProduct.productName" />
      </div>
      <div class="field">
        <label for="productDesc">Product Description:</label>
        <input type="text" id="productDesc" name="productDesc" v-model="newProduct.productDesc" />
      </div>
      <div class="field">
        <label for="ProductTypeId">Product Type Id:</label>
        <select type="text" id="ProductTypeId" name="ProductTypeId" v-model="newProduct.productTypeId" >
          <!-- SG dynamically populate options from product type table, content to contain both values of Product type table productTypeId and ProductName-->
        <option value="1">Special Pizza</option>
        <option value="2">Custom Pizza</option>
        <option value="3">Drink</option>
        </select>
      </div>
      <div class="field">
        <label for="productPrice">Product Price:</label>
        <input type="text" id="productPrice" name="productPrice" v-model="newProduct.productPrice" />
      </div>
            <div class="field">
        <label for="productAvailable">Product Available?</label>
        <input type="checkbox" id="productAvailable" name="productAvailable" v-model="newProduct.productAvailable" />
      </div>
      <div class="field">
        <label for="sizeId">Size:</label>
        <select type="text" id="sizeId" name="sizeId" v-model="newProduct.sizeId" >
          <!-- SG dynamically populate options from size table, content to contain both values of Size Table SizeId and SizeStyle and Price Multiplier-->
        <option value="1">Small</option>
        <option value="2">Medium</option>
        <option value="3">Large</option>
        <option value="4">Anthony</option>
        </select>
      </div>      
      <button type="submit" class="btn save">Save Product</button>
    </form>
    </div>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import AdminService from '../services/AdminService';
import InventoryProduct from '../components/InventoryProduct.vue';
import InventoryProductOption from '../components/InventoryProductOption.vue';
import { computed, toValue } from 'vue';

export default {
  components: {
    NavOptions,
    InventoryProduct,
    InventoryProductOption,
  },
  data() {
    return{
    products: [],
    productOptions: [],
    filter: {
        productDesc: "",
        productPrice: "",
        productAvailable: ""
      },
      showForm: false,
      editAvailability: false,
      showOptionForm: false,
      editOptionAvailability: false,
      newProduct: {
        productName: "",
        productDesc: "",
        productTypeId: "",
        productPrice: "",
        productAvailable: "false",
        sizeId: ""
      },
      selectedProducts: [],
      productAvailableDto: {
          productId: "",
          productAvailable: ""

      }
  };
  },
  methods: {
    getProducts(){
      AdminService.Products()
      .then(response => {
       this.products = response.data;
      })
      .catch(error => {
        console.log(error);
      })
    },
    getOptions(){
      AdminService.Options()
      .then(response => {
       this.productOptions = response.data;
      })
      .catch(error => {
        console.log(error);
      })
      
    },
    saveProduct() {
      AdminService
          .createProduct(this.newProduct)
          .then((response) => {})
          .catch((error) => {
            const response = error.response;

            if (response.status === 400) {
              alert('Bad Request: Validation Errors');
            }
          });
      this.clearNewProduct();
      this.getProducts
      /* to-do set method to call method to post to product table in db,
      clearNewProduct then reload products*/
    },

    clearNewProduct() {
      this.newProduct = {
        productName: "",
        productDesc: "",
        productTypeId: "",
        productPrice: "",
        productAvailable: "false",
        sizeId: ""
      };
    },
    flipStatus(id) {
      const index = this.findProductById(id);
      
      this.products[index].productAvailable =
        this.products[index].productAvailable === "true" ? "false" : "true";
      
      this.productAvailableDto.productId = id;
       this.productAvailableDto.productAvailable = this.products[index].productAvailable;
        AdminService.updateProductAvailability(productAvailableDto)
    
    },
    selectAllProducts(event) {
      if (event.target.checked) {
        this.selectedProducts = this.filteredList.map(product=>product.productId);
      } else {
        this.selectedProducts = [];
      }
    },
    activateSelectedProducts() {
      this.selectedProducts.forEach((id) => {

       // this.products[this.findProductById(id)].productAvailable = "true";
       this.productAvailableDto.productId = id;
       this.productAvailableDto.productAvailable = "true";
        AdminService.updateProductAvailability(productAvailableDto)
      });
      this.clearSelectedProducts();
      this.getProducts();
    },
    deactivateSelectedProducts() {
      this.selectedProducts.forEach((id) => {
        // send out to db         this.products[this.findProductById(id)].productAvailable = "false";
        this.productAvailableDto.productId = id;
       this.productAvailableDto.productAvailable = "true";
        AdminService.updateProductAvailability(productAvailableDto)

      });
      this.clearSelectedProducts();
      this.getProducts();
    },
    clearSelectedProducts() {
      this.selectedProducts = [];
    },
    findProductById(id) {
      return this.products.findIndex((product) => product.productId === id);
    },
    /* updatePrice(product) {
      const index = this.findProductById(product.productId);
      if (index !== -1) {
        // Update the product price with the new value from the input field
        this.products[index].productPrice = product.tempPrice;
        // Optionally call API to update the database (if required)
        AdminService.updateProductPrice(product.productId, product.tempPrice)
          .then(response => {
            console.log('Product price updated successfully');
          })
          .catch(error => {
            console.error('Error updating product price', error);
          });
      }
    }, */
  },
  created() {
    this.getProducts();
    this.getOptions();
    // this.getTypes();  Stretch goal to dynamically update type information based on table in database (option_type, product_type, size)
  },
  computed: {
    filteredList() {
      let filteredProducts = this.products;
      if (this.filter.productDesc != "") {
        filteredProducts = filteredProducts.filter((product) =>
          product.productDesc
            .toLowerCase()
            .includes(this.filter.productDesc.toLowerCase())
        );
      }
      if (this.filter.productPrice != "") {
        filteredProducts = filteredProducts.filter((product) =>
          product.productPrice == this.filter.productPrice)
        
      }
      if (this.filter.productAvailable != "") {
        filteredProducts = filteredProducts.filter((product) =>
          product.productAvailable === this.filter.productAvailable
        );
      }
      return filteredProducts;
    },
    actionButtonEnabled() {
      return this.selectedProducts.length > 0;
    } /*,
    commitButtonEnabled() {
      return product.productPrice == );
    }*/
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

th {
  text-transform: uppercase;
  text-align: center;
}
tr.deactivated {
  color: grey;
  background-color: rgb(195, 211, 225);
}
</style>
