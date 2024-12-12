<template>
     <div >
        <h1 v-on:click="specials()" class = "specials-message">Specials&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1><br>
        <div v-if="selectedSpecial" class="specialsMenu">
            <ul>
                <li v-for="product in uniqueProducts" :key="product.productId">
                  {{ product.productName }} 
                
                </li>
              </ul>
        </div>
    </div>
</template>

<script>


export default{
    props:{
        products:{
            type: Array,
            required: true
        }
    },
    data(){
    return{
        selectedSpecial:false

    };
    },computed: {
    uniqueProducts() {
      const seen = new Set();
      return this.products.filter((product) => {
        const isDuplicate = seen.has(product.productName);
        seen.add(product.productName);
        return !isDuplicate;
      });
     }
    },
    
    methods:
    {
    specials(){
        this.selectedSpecial=!this.selectedSpecial;
    }
    }
}
</script>


<style scoped>
.specials-message {
    font-family: sans-serif;
    display: flex;
    align-items: center;
    justify-content: space-between;
    text-align: center;
}
.specialsMenu li{
 list-style: none;
}
.text {
    font-family: sans-serif;
}
</style>