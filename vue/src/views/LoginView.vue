<template>
  <div class="home">
    <nav>
      <nav-options/>
    </nav>
  </div>
  <div id="login">
    <form id="loginform" v-on:submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link v-bind:to="{ name: 'customerRegister' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import NavOptions from '../components/NavOptions.vue';
import authService from "../services/AuthService";

export default {
  components: {
    NavOptions
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
#login {
  background-image: url('../assets/login1.png'); 
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  min-height: 100vh; 
  display: flex;
  justify-content: center;
  align-items: center;
}
#loginform{
  background-color: rgba(124, 146, 181, 0.1);
  
  
  
}
.form-input-group {
  margin-bottom: 1rem;
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