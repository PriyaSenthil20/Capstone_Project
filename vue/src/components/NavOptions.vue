<template>
    <div id="main">    
        <div id="menu-options">
            <router-link v-bind:to="{ name: 'home' }"><button> Home </button></router-link>
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
                <button> Logout </button></router-link>
            
            <button> Menu </button>
            <button> About Us </button>
            <button> Specials </button>
            <button> Sign In </button>
            <button> Account </button>
            <button> Start Order </button>
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
    grid-area: main;
    border: white;
    border-style: groove;
    border-radius: 5px;
    height: 60vh;
    background-size: cover;
    background-repeat: no-repeat;
}


#game-options {
    display: flex;
    justify-content: space-between;
}

#monster-image {
    margin-top: auto;
}

</style>