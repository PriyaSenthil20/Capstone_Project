 <!-- WILL BE LENGTHY WITH NEW ROUTE!!! If we have time I'll attempt to
 move the tables to the components and not the view.
 
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
     <div id="test-table">
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
            <input type="text" id="ProductDescriptionFilter" v-model="filter.productDescription" />
          </td>
          <td>
            <input type="text" id="productPriceFilter" v-model="filter.productPrice" />
          </td>
          <td>
            <select id="availabilityFilter" v-model="filter.availability">
              <option value>Show All</option>
              <option value="true">Available</option>
              <option value="false">Unavailable</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="product in filteredList"
          v-bind:key="product.productID"
          v-bind:class="{ deactivated: product.availability === 'false' }"
        >
          <td>
            <input
              type="checkbox"
              v-bind:id="product.productID"
              v-bind:value="product.productID"
              v-model="selectedProducts"
            />
          </td>
          <td>{{ product.productDescription }}</td>
          <td>{{ product.productPrice }}</td>
          <td>{{ product.productAvailable }}</td>
          
          <td>
            <button
              class="btnActivateDeactivate"
              v-on:click="flipStatus(product.productId)"
            >{{ product.availability === 'true' ? 'false' : 'true' }}</button>
          </td>
        </tr>
      </tbody>
    </table>

    
    <div class="all-actions">
      <button v-on:click="activateSelectedProducts()" v-bind:disabled="!actionButtonEnabled">Selected Available</button>
      <button v-on:click="deactivateSelectedProducts()" v-bind:disabled="!actionButtonEnabled">Selected Unavailable</button>
    </div>

    <button v-on:click="showForm = !showForm">Add New Product</button>

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
          <!-- dynamically populate options from product type table, content to contain both values of Product type table productTypeId and ProductName-->
        <option value="WY">Wyoming</option>
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
          <!-- dynamically populate options from size table, content to contain both values of Size Table SizeId and SizeStyle and Price Multiplier-->
        <option value="WY">Wyoming</option>
        </select>
      </div>      
      <button type="submit" class="btn save">Save Product</button>
    </form>
  
 <!-- Demo of Gets from Product table to be used in table and basis for forms in components-->
    </div>
    <div class="content-section">      
      <inventory-product v-bind:products="products" />
    </div>

 <!-- Demo of Gets from Product Options table to be used in table and basis for forms in components-->
    <div class="content-section">      
      <inventory-product-option v-bind:productOptions="productOptions" />
    </div>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import AdminService from '../services/AdminService';
import InventoryProduct from '../components/InventoryProduct.vue';
import InventoryProductOption from '../components/InventoryProductOption.vue';
import { computed } from 'vue';

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
        productName: "",
        productPrice: "",
        availability: ""
      },
      showForm: false,
      newProduct: {
        productName: "",
        productDesc: "",
        productTypeId: "",
        productPrice: "",
        productAvailable: "false",
        sizeId: ""
      },
      selectedProducts: [],
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
      /* to-do set method to call method to post to product table in db,
      clearNewProduct then reload products*/
      
     // this.products.push(this.newProduct); won't need this since we'll reload from db
      this.clearNewProduct();
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
      alert(index)
      this.products[index].availability =
        this.products[index].availability === "true" ? "false" : "true";
    },
    selectAllProducts(event) {
      if (event.target.checked) {
        this.selectedProducts = this.products.map(product=>product.id);
      } else {
        this.selectedProducts = [];
      }
    },
    activateSelectedProducts() {
      this.selectedProducts.forEach((id) => {
        this.products[this.findProductById(id)].availability = "true";
      });
      this.clearSelectedProducts();
    },
    deactivateSelectedProducts() {
      this.selectedProducts.forEach((id) => {
        this.products[this.findProductById(id)].availability = "false";
      });
      this.clearSelectedProducts();
    },
    clearSelectedProducts() {
      this.selectedProducts = [];
    },
    findProductById(id) {
      return this.products.findIndex((product) => product.productId === id);
    }
  },
  created() {
    this.getProducts();
    this.getOptions();
  },
  computed: {
    filteredList() {
      let filteredProducts = this.products;
      if (this.filter.productName != "") {
        filteredProducts = filteredProducts.filter((product) =>
          product.productName
            .toLowerCase()
            .includes(this.filter.productName.toLowerCase())
        );
      }
      if (this.filter.productPrice != "") {
        filteredProducts = filteredProducts.filter((product) =>
          product.productPrice
            .toLowerCase()
            .includes(this.filter.productPrice.toLowerCase())
        );
      }
      if (this.filter.availability != "") {
        filteredProducts = filteredProducts.filter((product) =>
          product.availability === this.filter.availability
        );
      }
      return filteredProducts;
    },
    actionButtonEnabled() {
      return this.selectedProducts.length > 0;
    }
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
