<template>
    <div id="main">    
        <div id="menu-options">
            <router-link v-bind:to="{ name: 'home' }" >
              <button class = "navBtn"> Home </button></router-link>

                <span v-if="$store.state.token != ''">
                    <router-link v-bind:to="{ name: 'logout' }">
                        <button class = "navBtn"> Logout </button></router-link>
                </span>
                <span v-else>
                    <router-link v-bind:to="{ name: 'login' }" @click="onLinkClicked('/login')">
                        <button class = "navBtn"> Login </button></router-link>
                </span>    
                <span v-if="$store.state.token === ''">
                    <router-link v-bind:to="{ name: 'customerRegister' }" @click="onLinkClicked('/customerRegister')">
                        <button class = "navBtn"> Register </button></router-link>
                </span>                            
           
            <button class="navBtn">Menu</button>

            <span v-if="$store.state.user.role === 'ROLE_ADMIN'" @click="onLinkClicked('/adminInventory')">
              <router-link v-bind:to="{ name: 'AdminInventory' }">
              <button class="navBtn">Inventory</button></router-link>
            </span>

            <span v-if="$store.state.user.role === 'ROLE_ADMIN'" @click="onLinkClicked('/adminOrder')">
              <router-link v-bind:to="{ name: 'AdminOrder' }">
              <button class="navBtn">Check Orders</button></router-link>
            </span>
                <span >
                    <router-link v-bind:to="{ name: 'menu' }">
                        <button class = "navBtn"> Menu </button></router-link>
                </span>                            
        
            <router-link v-bind:to="{ name: 'aboutUs' }">
              <button class="navBtn">About Us</button></router-link>

              <router-link v-bind:to="{ name: 'Specials' }">
              <button class="navBtn">Specials</button></router-link>


            <!--
                For Account route...

                <span v-if="$store.state.token === ''">
                    <router-link v-bind:to="{ name: 'account' }">
                        <button> Account </button></router-link>
                </span>   

            -->

            <!-- If the user is not signed this should prompt them to sign in or register -->
            <router-link v-bind:to="{ name: 'customerOrder' }" @click="onLinkClicked('/customerOrder')">
              <button class="navBtn orderBtn">Start Order</button>
            </router-link>
            
        <img v-bind:src="this.logoUrl" class="logo"/></div>
    </div>
</template>

<script>


export default {

    methods: {
      onLinkClicked(redirectPath) {
        if (!this.isAuthenticated) {
          // If not authenticated, redirect to login with a query to redirect after login
          this.$router.push({ name: 'login', query: { redirect: redirectPath } });
        }
      }
    },
    computed: {
        logoUrl(){
            return `../src/assets/PizzaIcon.png`;            
        }
    }
};
</script>


<style scoped>
/* General Layout */
#main {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background-color: #e63946; /* A vibrant red background */
  height: 50%;
  padding: 20px;
  box-sizing: border-box;
}

#menu-options {
  display: flex;
  flex-wrap: nowrap; /* Prevent wrapping to new lines */
  justify-content: space-between; /* Even spacing between buttons */
  align-items: center; /* Align buttons vertically */
  gap: 10px; /* Optional: small space between buttons */
  background-color: #f1faee; /* Light background for contrast */
  padding: 20px;
  border-radius: 15px;
  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.1);
  margin-bottom: 0.2rem;
}

/* Buttons */
.navBtn {
  flex: 1; /* Makes all buttons take equal space */
  width: 120px; /* Ensures buttons don’t grow too large */
  height: 50px; /* Enforce consistent height */
  text-align: center;
  font-size: 1.2rem;
  font-weight: bold;
  color: #fff;
  background-color: #457b9d; /* Deep blue */
  padding: 12px 0; /* Adjust padding for consistency */
  border: none;
  border-radius: 8px;
  cursor: pointer;
  transition: background-color 0.3s, transform 0.2s, box-shadow 0.2s;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

.navBtn:hover {
  background-color: #1d3557; /* Darker blue for hover */
  transform: translateY(-3px);
  box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.3);
}

/* Order Button (Highlight) */
.orderBtn {
  background-color: #ffcc00; /* Bright yellow */
  color: #333;
}

.orderBtn:hover {
  background-color: #ffb900; /* Slightly darker yellow */
}

/* Logo */
.logo {
  max-width: 30px;
  border-radius: 10px;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
}

/* Responsive Design */
@media (max-width: 768px) {
  #menu-options {
    flex-direction: column;
    gap: 1rem;
  }

  .navBtn {
    font-size: 1rem;
    padding: 10px 20px;
  }

  .logo {
    max-width: 10px;
  }
}
</style>
