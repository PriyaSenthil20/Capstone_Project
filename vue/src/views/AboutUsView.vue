<template>
    <div>
        <nav-options />
        </div>

      <div class="customerComponent">
      <about-us />
      </div>
   
</template>

<script>


import NavOptions from "../components/NavOptions.vue";
import AboutUs from "../components/AboutUs.vue";

export default {

 
components: {
    AboutUs,
    NavOptions
}

}

</script>

