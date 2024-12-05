<template>
     <div class = "specials-message">
        <h1>Specials</h1>
            <div v-for="product in this.products" v-bind:key="product.productId">
                {{product.productName}}
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