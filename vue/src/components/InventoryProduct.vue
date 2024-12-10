<template>
     <div class = "inventory-message">
        <h1>Products</h1>
            <div v-for="product in this.products" v-bind:key="product.productId">
                {{product.productName}}
            </div>
            <h1>Options</h1>
            <div v-for="productOption in this.productOption" v-bind:key="productOption.optionId">
                {{productOption.optionName}}
            </div>
    </div>
    
</template>

<script>


export default{
    props:{
        products:{
            type: Array,
            required: true
        },
        productOptions:{
            type: Array,
            required: true
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

.text {
    font-family: sans-serif;
}
</style>