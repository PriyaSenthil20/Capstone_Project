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
           
            <button> Menu </button>
            <button> About Us </button>
            <button> Specials </button>

            <!--
                For Account route...

                <span v-if="$store.state.token === ''">
                    <router-link v-bind:to="{ name: 'account' }">
                        <button> Account </button></router-link>
                </span>   

            -->

            <!-- If the user is not signed this should prompt them to sign in or register -->
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
}



#game-options {
    display: flex;
    justify-content: space-between;
}

#monster-image {
    margin-top: auto;
}

</style>