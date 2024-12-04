<template>
  <div class="home">
    <nav>
      <nav-options/>
    </nav>
  </div>
  <div id="register" class="text-center">
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

  
  <div id="customerRegister" class="text-center">
    <form v-on:submit.prevent="register">
       <h1>Enter Customer Details for {{this.$store.state.user.username}}</h1>
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
        <input type="text" id="customerPhoneNumber" v-model="customer.customerPhoneNumber" required />
      </div>
      <div class="form-input-group">
        <label for="customerEmailAddress">Email Address</label>
        <input type="email" id="customerEmailAddress" v-model="customer.customerEmailAddress" required />
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
      customer: {
        customerId: this.$store.state.user.id,
        customerFirstName: '',
        customerLastName: '',
        customerAddress: '',
        customerCity: '',
        customerZipCode: '',
        customerState: '',
        customerPhoneNumber: '',
        customerEmailAddress: '',
//        customerUsername: '', 
 //       customerPassword: '',
 //       customerConfirmPassword: '',
 //       customerRole: 'user', 
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
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
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
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}
</style>
