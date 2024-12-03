<template>
    <div id="main">    
        <div id="menu-options">
            <router-link v-bind:to="{ name: 'home' }"><button> Home </button></router-link>

                <span v-if="$store.state.token != ''">
                    <router-link v-bind:to="{ name: 'logout' }">
                        <button class = "navBtn"> Logout </button></router-link>
                </span>
                <span v-else>
                    <router-link v-bind:to="{ name: 'login' }">
                        <button class = "navBtn"> Login </button></router-link>
                </span>    
                <span v-if="$store.state.token === ''">
                    <router-link v-bind:to="{ name: 'register' }">
                        <button class = "navBtn"> Register </button></router-link>
                </span>                            
           
            <button class="navBtn">Menu</button>
            <button class="navBtn">About Us</button>
            <button class="navBtn">Specials</button>

            <!--
                For Account route...

                <span v-if="$store.state.token === ''">
                    <router-link v-bind:to="{ name: 'account' }">
                        <button> Account </button></router-link>
                </span>   

            -->

            <!-- If the user is not signed this should prompt them to sign in or register -->
            <button class="navBtn orderBtn">Start Order</button>
            <img v-bind:src="this.logoUrl"/>
        </div>
    </div>
</template>

<script>


export default {
    data(){
       
    },
    methods: {
        
    },
    computed: {
        logoUrl(){
            return `../src/assets/PizzaIcon.png`;            
        }
    },
    created(){
       
    }
};
</script>


<style scoped>

#main {
  display: flex;
  flex-direction: column;
  align-items: center;
  grid-area: main;
  border-radius: 5px;
  padding: 1rem;
  background-color: #f4f4f4;
}

#menu-options {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1.5rem;
  margin-bottom: 1rem;
  padding: 1.5rem;
  background-color: #e63946;
  border-radius: 15px;
  box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
}

.navBtn {
  padding: 1.2rem 2.5rem;
  font-size: 1.5rem;
  font-weight: bold;
  color: white;
  background-color: #ff6347;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.2);
  transition: background-color 0.3s, transform 0.2s;
}

.navBtn:hover {
  background-color: #ff4500;
  transform: translateY(-3px);
}

.orderBtn {
  background-color: #ffd700;
  color: #333;
}

.orderBtn:hover {
  background-color: #ffcc00;
}

.logo {
  max-width: 120px;
  margin-top: 1.5rem;
}


</style>