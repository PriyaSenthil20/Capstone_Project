<template>
  <div class="home">
    <nav>
      <nav-options/>
    </nav>
  </div>
  <span>
    <h3 id="showStep1" v-if="$store.state.token == ''">Step 1/2</h3>
    <h3 id="showStep2" v-if="$store.state.token != ''">Step 2/2</h3>
  </span>
  <div id="register" class="text-center" v-if="$store.state.token == ''">
    <form v-on:submit.prevent="register">
      <h1>Create Account</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
      <p><router-link v-bind:to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
    </form>
  </div>

  
  <div id="customerRegister" class="text-center" v-else>
    <form v-on:submit.prevent="registerCustomer">
       <h1>Enter Customer Details for {{customer.customerId}} {{this.$store.state.user.username}}</h1>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="customerFirstName">First Name</label>
        <input type="text" id="customerFirstName" v-model="customer.customerFirstName" required />
      </div>
      <div class="form-input-group">
        <label for="customerLastName">Last Name</label>
        <input type="text" id="customerLastName" v-model="customer.customerLastName" required />
      </div>
      <div class="form-input-group">
        <label for="customerAddress">Address</label>
        <input type="text" id="customerAddress" v-model="customer.customerAddress" required />
      </div>
      <div class="form-input-group">
        <label for="customerCity">City</label>
        <input type="text" id="customerCity" v-model="customer.customerCity" required />
      </div>
      <div class="form-input-group">
        <label for="customerZipCode">Zip Code</label>
        <input type="number" id="customerZipCode" v-model="customer.customerZipCode" required />
      </div>
      <div class="form-input-group">
        <label for="customerState">State</label>
        <select id="customerState" placeholder="Select a State" v-model="customer.customerState" required >
	           <option value="AL">Alabama</option>
		          <option value="AK">Alaska</option>
		          <option value="AZ">Arizona</option>
		          <option value="AR">Arkansas</option>
		          <option value="CA">California</option>
		          <option value="CO">Colorado</option>
	          	<option value="CT">Connecticut</option>
		          <option value="DE">Delaware</option>
		          <option value="DC">District Of Columbia</option>
		          <option value="FL">Florida</option>
		          <option value="GA">Georgia</option>
		          <option value="HI">Hawaii</option>
		          <option value="ID">Idaho</option>
		          <option value="IL">Illinois</option>
		          <option value="IN">Indiana</option>
		          <option value="IA">Iowa</option>
		          <option value="KS">Kansas</option>
		          <option value="KY">Kentucky</option>
		          <option value="LA">Louisiana</option>
	          	<option value="ME">Maine</option>
		          <option value="MD">Maryland</option>
		          <option value="MA">Massachusetts</option>
		          <option value="MI">Michigan</option>
		          <option value="MN">Minnesota</option>
		          <option value="MS">Mississippi</option>
		          <option value="MO">Missouri</option>
		          <option value="MT">Montana</option>
		          <option value="NE">Nebraska</option>
	          	<option value="NV">Nevada</option>
		          <option value="NH">New Hampshire</option>
		          <option value="NJ">New Jersey</option>
		          <option value="NM">New Mexico</option>
	          	<option value="NY">New York</option>
		          <option value="NC">North Carolina</option>
		          <option value="ND">North Dakota</option>
		          <option value="OH">Ohio</option>
		          <option value="OK">Oklahoma</option>
		          <option value="OR">Oregon</option>
	          	<option value="PA">Pennsylvania</option>
	          	<option value="RI">Rhode Island</option>
	          	<option value="SC">South Carolina</option>
	          	<option value="SD">South Dakota</option>
	          	<option value="TN">Tennessee</option>
	          	<option value="TX">Texas</option>
		          <option value="UT">Utah</option>
		          <option value="VT">Vermont</option>
		          <option value="VA">Virginia</option>
	          	<option value="WA">Washington</option>
		          <option value="WV">West Virginia</option>
		          <option value="WI">Wisconsin</option>
		          <option value="WY">Wyoming</option>
         </select>
      </div>
      <div class="form-input-group">
        <label for="customerPhoneNumber">Phone Number</label>
        <input type="text" id="customerPhoneNumber" v-model="customer.phoneNumber" required />
      </div>
      <div class="form-input-group">
        <label for="customerEmailAddress">Email Address</label>
        <input type="email" id="customerEmailAddress" v-model="customer.customerEmail" required />
      </div>
      <button type="submit">Submit</button>
    </form>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import authService from '../services/AuthService';

export default {
  components: {
    NavOptions
  },
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      qp:{
                customerId: 3,
        customerFirstName: 'pi',
        customerLastName: 'za',
        customerAddress: '321 new rd.',
        customerCity: 'Newark',
        customerZipCode: 19701,
        customerState: 'DE',
        phoneNumber: '1111111112',
        customerEmail: 'pz@testmail.com'
      },
      customer: {
        customerId: '',
        customerFirstName: '',
        customerLastName: '',
        customerAddress: '',
        phoneNumber: '',
        customerCity: '',
        customerState: '',
        customerZipCode: '',
        customerEmail: '',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this customer.',
    };
  },
  methods: {
    register() {
      if (this.customer.customerPassword != this.customer.customerConfirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              
              authService.login(this.user).then((response) => {
              if (response.status == 200){
                this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                this.$store.commit("SET_USER", response.data.user);
            }
              })
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    registerCustomer() {
      console.log(this.$store.state.user);
        authService
          .registerCustomer(this.customer)
          .then((response) => {
            if (response.status == 201) {
              this.$store.commit("SET_CUSTOMER", response.data);
              /* this.$router.push({
                path: '/',
                query: { registration: 'success' },
              });*/
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
  created(){
    this.customer.customerId= this.$store.state.user.id;
  }
};
</script>

<style scoped>
#register {
  background-image: url('../assets/register5.png'); 
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-height: 100vh; 
  display: flex;
  justify-content: center;
  align-items: center;
}
#customerRegister{
  background-image: url('../assets/register5.png'); 
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-height: 100vh; 
  display: flex;
  justify-content: center;
  align-items: center;
}
.form-input-group {
  disply:flex;
  align-items:center;
  margin-bottom: 1rem;
}
.form-input-group input, .form-input-group select {
  width: 100%;  
  padding: 10px;
  margin: 0;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.form-input-group select {
  max-width: 100%;
}

label {
  margin-right: 0.5rem;
}
h1 {
  font-size: 2rem;
  margin-bottom: 20px;
  color: #808080; 
}
label {
  color: #808080;
}
button {
  background-color: red; 
  color: white; 
  padding: 10px 20px; 
  border: none; 
  border-radius: 4px; 
  font-size: 16px; 
  cursor: pointer; 
  transition: background-color 0.3s ease; 
}

button:hover {
  background-color: darkred; 
}
</style>
