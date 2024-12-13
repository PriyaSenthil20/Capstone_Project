<template>
  <div class="home">
    <nav class="navbar">
      <nav-options />
    </nav>
    <div class="adminForm">
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
          <th>Action</th>
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
      <div class="btnsavediv">
      <button type="submit" class="btnsave">Save Product</button>
      </div>
    </form>
   
    </div>
<!-- ----------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
-->

    <div class="option-page-buttons">
      <button v-on:click="editOptionAvailability = !editOptionAvailability && clearSelectedOptions">Edit Option Availability</button>
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
            <input type="checkbox" id="selectAllOptions" v-on:change="selectAllOptions($event)" v-bind:checked="selectedOptions.length === productOptions.length" />
          </td>
          <td>
            <input type="text" id="optionNameFilter" v-model="filter.optionName" />
          </td>
          <td>
            <select id="optionAvailableFilter" v-model="filter.optionAvailable">
              <option value>Show All</option>
              <option value="true">Available</option>
              <option value="false">Unavailable</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="option in filteredOptionList"
          v-bind:key="option.optionId"
          v-bind:class="{ deactivated: option.optionAvailable === 'false' }"
        >
          <td>
            <input
              type="checkbox"
              v-bind:id="'optionCheckbox' + option.optionId"
              v-bind:value="option.optionId"
              v-model="selectedOptions"
            />
          </td>
          <td>{{option.optionName}}</td>
          <td>{{option.optionAvailable}}</td>
         <!-- <td>
            <button
              class="btnActivateDeactivate"
              v-on:click="flipOptionStatus(option.optionId)"
            >{{ option.optionAvailable === 'true' ? 'Make Unavailable' : 'Make Available' }}</button>
          </td> -->
        </tr>
      </tbody>
    </table>

    
    <div class="all-actions">
      <button v-on:click="activateSelectedOptions()" v-bind:disabled="!commitButtonEnabled">Make Selected Available</button>
      <button v-on:click="deactivateSelectedOptions()" v-bind:disabled="!commitButtonEnabled">Make Selected Unavailable</button>
    </div>
    </div>
    <div>
      <form id="frmAddNewOption" v-show="showOptionForm" v-on:submit.prevent="saveOption">
      <div class="field">
        <label for="optionName">Option Name:</label>
        <input type="text" id="optionName" name="optionName" v-model="newOption.optionName" />
      </div>
      <div class="field">
        <label for="optionDesc">Option Description:</label>
        <input type="text" id="optionDesc" name="optionDesc" v-model="newOption.optionDesc" />
      </div>
      <div class="field">
        <label for="optionTypeId">Option Type Id:</label>
        <select type="text" id="optionTypeId" name="optionTypeId" v-model="newOption.optionTypeId" >
        <option value="1">Topping</option>
        <option value="2">Sauce</option>
        <option value="3">Crust</option>
        </select>
      </div>
      <div class="field">
        <label for="optionPrice">Option Price:</label>
        <input type="text" id="optionPrice" name="optionPrice" v-model="newOption.optionPrice" />
      </div>
            <div class="field">
        <label for="optionAvailable">Option Available?</label>
        <input type="checkbox" id="optionAvailable" name="optionAvailable" v-model="newOption.optionAvailable" />
      </div>
      <button type="submit" class="btn_save">Save Option</button>
    </form>
    </div>
  </div> 
</div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import AdminService from '../services/AdminService';

export default {
  components: {
    NavOptions,
  },
  data() {
    return{
    products: [],
    productOptions: [],
    filter: {
        productDesc: "",
        productPrice: "",
        productAvailable: "",
        optionName: "",
        optionAvailable: ""
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
      newOption: {
        optionName: "",
        optionDesc: "",
        optionTypeId: "",
        optionPrice: "",
        optionAvailable: "false",
      },
      selectedProducts: [],
      selectedOptions: [],
      productAvailableDto: {
          productId: "",
          productAvailable: ""
      },
      productOptionAvailableDto: {
          optionId: "",
          optionAvailable: ""
      }

  };
  },
  methods: {
    getProducts(){
      AdminService.Products()
      .then(response => {
       this.products = response.data.map(product => ({
        ...product,
        productAvailable: String(product.productAvailable)
      }));
    })
      .catch(error => {
        console.log(error);
      })
    },
    getOptions(){
      AdminService.Options()
      .then(response => {
       this.productOptions = response.data.map(productOption => ({
        ...productOption,
        optionAvailable: String(productOption.optionAvailable)
      }));
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
    },
    saveOption() {
      AdminService
          .createProductOption(this.newOption)
          .then((response) => {})
          .catch((error) => {
            const response = error.response;

            if (response.status === 400) {
              alert('Bad Request: Validation Errors');
            }
          });
      this.clearNewOption();
      this.getOptions
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
     clearNewOption() {
      this.NewOption ={
        optionName: "",
        optionDesc: "",
        optionTypeId: "",
        optionPrice: "",
        optionAvailable: "false",
      };
      },
    flipStatus(id) {
      const index = this.findProductById(id);
        this.filteredList[index].productAvailable =
        this.filteredList[index].productAvailable === "true" ? "false" : "true";
        
        //this.productAvailableDto = {
        //productId: id,
        //productAvailable: this.products[index].productAvailable};
      this.products = [...this.products];
      this.productAvailableDto.productId = id;
      this.productAvailableDto.productAvailable = this.filteredList[index].productAvailable;
      AdminService.updateProductAvailability(this.productAvailableDto)
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
        const index = this.findProductById(id); 
       this.productAvailableDto.productId = id;
       this.productAvailableDto.productAvailable = "true";
        AdminService.updateProductAvailability(this.productAvailableDto)
        this.products[index].productAvailable = "true";
        this.products = [...this.products];
      });
      this.clearSelectedProducts();
      this.getProducts();
    },
    deactivateSelectedProducts() {
      this.selectedProducts.forEach((id) => {
        const index = this.findProductById(id);
        this.productAvailableDto.productId = id;
       this.productAvailableDto.productAvailable = "false";
        AdminService.updateProductAvailability(this.productAvailableDto)
        this.products[index].productAvailable = "false";
        this.products = [...this.products];
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
    selectAllOptions(event) {
      if (event.target.checked) {
        this.selectedOptions = this.filteredOptionList.map(option=>option.optionId);
      } else {
        this.selectedOptions = [];
      }
    },
    activateSelectedOptions() {
      this.selectedOptions.forEach((id) => {
        const index = this.findOptionById(id);
       this.productOptionAvailableDto.optionId = id;
       this.productOptionAvailableDto.optionAvailable = "true";
        AdminService.updateProductOptionAvailability(this.productOptionAvailableDto)
        this.productOptions[index].productAvailable = "true";
        this.productOptions = [...this.productOptions];
      });
      this.clearSelectedOptions();
      this.getOptions();
    },
    deactivateSelectedOptions() {
      this.selectedOptions.forEach((id) => {
        const index = this.findOptionById(id);
        this.productOptionAvailableDto.optionId = id;
       this.productOptionAvailableDto.optionAvailable = "false";
        AdminService.updateProductOptionAvailability(this.productOptionAvailableDto)
        this.productOptions[index].productAvailable = "false";
        this.productOptions = [...this.productOptions];
      });
      this.clearSelectedOptions();
      this.getOptions();
    },
    clearSelectedOptions() {
      this.selectedOptions = [];
    },
    findOptionById(id) {
      return this.productOptions.findIndex((option) => option.optionId === id);
    },
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
    },
    filteredOptionList() {
      let filteredOptions = this.productOptions;
      if (this.filter.optionName != "") {
        filteredOptions = filteredOptions.filter((option) =>
          option.optionName
            .toLowerCase()
            .includes(this.filter.optionName.toLowerCase())
        );
      }
      if (this.filter.optionAvailable != "") {
        filteredOptions = filteredOptions.filter((option) =>
          option.optionAvailable === this.filter.optionAvailable
        );
      }
      return filteredOptions;
      },
    commitButtonEnabled() {
      return this.selectedOptions.length > 0;
    }
  }
};

</script>

<style scoped>


.adminForm{
  display:flex;
  flex-direction: column;
  text-align: center;
  font-size: large;
 

}.product-page-buttons, .option-page-buttons {
  display: flex;
  flex-wrap: wrap; /* Allows buttons to wrap if necessary */
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
  gap: 1rem; /* Space between buttons */
  margin: 2rem auto; /* Center the container itself if needed */
  width: 100%; /* Full width of the parent container */
  max-width: 600px; /* Optional: limit the maximum width */
  text-align: center;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  border: none;
  border-radius: 5px;
  background-color: whitesmoke;
  color: black;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #0057b3e7;
}

.btnsave{
  max-width: 600px; 
  height: 50px;
}

.btnsavediv{
  justify-content: center;
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
#tblProduct, #tblOption {
  margin: 2rem auto; /* Center the table horizontally */
  width: fit-content; /* Adjust the table width based on content */
  border-collapse: collapse; /* Ensures borders are collapsed and not doubled */
  text-align: center; /* Center text inside table cells */
}

th, td {
  padding: 0.5rem; 
  border: 1px solid #ccc; 
}

th {
  text-transform: uppercase;
  text-align: center;
  background-color: #f1f1f1; /* Light background for header */
  font-weight: bold; /* Make header text bold */
}

tr.deactivated {
  color: grey;
  background-color: rgb(195, 211, 225);
}

.all-actions {
  display: flex;
  justify-content: center;  
}

.field label {
  width: 150px; /* Ensures consistent width for labels */
  text-align: right;
  
}
.field {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 1.5rem; /* Space between label and input */
  margin-bottom: 1.5rem; /* Space between rows of fields */
}


.field input,
.field select {
  width: 250px; /* Sets consistent width for input elements */
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
