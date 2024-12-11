<template>
     <div class = "inventory-message">
            <h1>Options</h1>
            <div v-for="productOption in this.productOptions" v-bind:key="productOption.optionId">
                {{productOption.optionName}}
            </div>
    </div>
    
</template>

<script>


export default{
    props:{
        productOptions:{
            type: Array,
            required: true
        },
    },
}
</script>


<style scoped>
.inventory-message {
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